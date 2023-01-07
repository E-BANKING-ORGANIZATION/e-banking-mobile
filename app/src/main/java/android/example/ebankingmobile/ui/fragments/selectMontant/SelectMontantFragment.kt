package android.example.ebankingmobile.ui.fragments.selectMontant

import android.annotation.SuppressLint
import android.content.Context
import android.example.ebankingmobile.R
import android.example.ebankingmobile.databinding.FragmentSelectMontantBinding
import android.example.ebankingmobile.retrofit.api.OtpApi
import android.example.ebankingmobile.retrofit.api.TransactionApi
import android.example.ebankingmobile.retrofit.model.OtpRequest
import android.example.ebankingmobile.retrofit.model.TransactionRequest
import android.example.ebankingmobile.retrofit.session.SessionManager
import android.example.ebankingmobile.retrofit.ws.BeneficiaireService
import android.example.ebankingmobile.retrofit.ws.OtpService
import android.example.ebankingmobile.utils.Errors
import android.example.ebankingmobile.utils.FrontUtils
import android.example.ebankingmobile.utils.TransactionUtils
import android.example.ebankingmobile.utils.consts.Consts
import android.example.ebankingmobile.utils.consts.ModalMessages
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback


class SelectMontantFragment : Fragment(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: FragmentSelectMontantBinding
    private lateinit var arrayAdapter: ArrayAdapter<String>
    private lateinit var listLinesDisplayed: ArrayList<String>
    private lateinit var listView: ListView
    private lateinit var checkBox: CheckBox
    private lateinit var montantInput: EditText
    private lateinit var constraintLayout: ConstraintLayout
    private lateinit var spinner: Spinner
    private lateinit var listNotifications: List<String>
    private var currentSpinnerItem: String = ""
    private lateinit var listFeesDisplayed: HashMap<String, Double>
    private lateinit var finalAmount: TextView
    private lateinit var sessionManager: SessionManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_select_montant, container, false)
        listFeesDisplayed = HashMap(3)

        listFeesDisplayed[Consts.AMOUNT] = 0.0
        listFeesDisplayed[Consts.NOTIFICATION_TRANSFER_FEES] = 0.0
        listFeesDisplayed[Consts.CHARGE_FEES] = 0.0

        listLinesDisplayed = java.util.ArrayList()

        listLinesDisplayed.add(Consts.AMOUNT + listFeesDisplayed[Consts.AMOUNT])
        listLinesDisplayed.add(Consts.NOTIFICATION_TRANSFER_FEES + listFeesDisplayed[Consts.NOTIFICATION_TRANSFER_FEES])
        listLinesDisplayed.add(Consts.CHARGE_FEES + listFeesDisplayed[Consts.CHARGE_FEES])

        listView = binding.listFeesOfTransfer
        arrayAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, listLinesDisplayed)
        listView.adapter = arrayAdapter
        checkBox = binding.notificationTransferCheckBox

        listNotifications = listOf(
            Consts.FIRST_NOTIFICATION_CHOICE,
            Consts.SECOND_NOTIFICATION_CHOICE,
            Consts.THIRD_NOTIFICATION_CHOICE
        )

        montantInput = binding.montantInput
        constraintLayout = binding.wholeLayoutContainer
        spinner = binding.notifChoiceList

        sessionManager = SessionManager(requireContext())

        finalAmount = binding.valueFinalAmountAfterFees


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        disablingFocusWhenUserClicksOtherThanKeyboard()
        getAmountInputFromUser()
        configurationSpinnerWithNotificationChoices()
        notificationTransferCheckBoxLogic()
        goToOtpFragment()
    }

    private fun configurationSpinnerWithNotificationChoices() {
        val adapter: ArrayAdapter<CharSequence> = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            listNotifications as List<CharSequence>
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = this
    }

    private fun doTransaction() {
        val beneficiareService = BeneficiaireService()
        val transactionApi =
            beneficiareService.retrofit.create(TransactionApi::class.java)

        val transactionRequest = TransactionRequest()
        transactionRequest.bl = checkBox.isChecked
        transactionRequest.amount = montantInput.text.toString().toInt()
        transactionRequest.id = sessionManager.fetchId()
        transactionRequest.BenefId = "a048d000008IwrQAAS"

        transactionApi.doTransaction(
            Consts.BEARER + sessionManager.fetchAuthToken(),
            TransactionRequest()
        ).enqueue(
            object : Callback, retrofit2.Callback<Void> {

                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.code() >= 400) {
                        Log.i("error in the", "please try again !")
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Log.i("server", "that was error in the server !")
                }

            }
        )


    }

    @SuppressLint("ClickableViewAccessibility")
    private fun disablingFocusWhenUserClicksOtherThanKeyboard() {
        constraintLayout.setOnTouchListener { _, _ ->
            requireView().clearFocus()
            hideSoftKeyboard()
            true
        }
        montantInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                montantInput.clearFocus()
                val imm =
                    requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(requireView().windowToken, 0)
            }
            false
        }
    }

    private fun hideSoftKeyboard() {
        val windowToken = view?.rootView?.windowToken
        windowToken?.let {
            val imm =
                requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it, 0)
        }
    }

    private fun getAmountInputFromUser() {
        montantInput.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                Toast.makeText(requireContext(), "writing", Toast.LENGTH_SHORT).show()
            } else {
                if (montantInput.text.toString() != "") {
                    updateNotificationAdapterItem(0, montantInput.text.toString().toLong())
                    updateNotificationAdapterItem(2, montantInput.text.toString().toLong())
                }
            }
        }
    }

    private fun updateNotificationAdapterItem(index: Int, amount: Long) {
        var newAmountAfterChargeFees = 0.0
        var newString = ""
        if (index == 2) {
            newAmountAfterChargeFees =
                TransactionUtils.returnChargeFees(amount, currentSpinnerItem)!!
            newString =
                TransactionUtils.returnFinalLineInListTransactionFees(
                    newAmountAfterChargeFees,
                    index
                )
            listFeesDisplayed[Consts.CHARGE_FEES] = newAmountAfterChargeFees
        } else {
            newString =
                TransactionUtils.returnFinalLineInListTransactionFees(
                    amount.toDouble(),
                    index
                )
            if (index == 0) {
                listFeesDisplayed[Consts.AMOUNT] = amount.toDouble()
            } else {

                listFeesDisplayed[Consts.NOTIFICATION_TRANSFER_FEES] = amount.toDouble()
            }

        }

        listLinesDisplayed[index] = newString
        arrayAdapter.notifyDataSetChanged()

        displayNewAmountWithFees()
    }

    private fun notificationTransferCheckBoxLogic() {
        binding.notificationTransferCheckBox.setOnClickListener {
            if (checkBox.isChecked) {
                if (montantInput.text.toString() == "") {
                    checkBox.isChecked = false
                    FrontUtils.showToast(requireContext(), "please type an amount before")
                } else {
                    updateNotificationAdapterItem(1, Consts.TRANSFER_NOTIFICATION_FEES.toLong())
                }
            } else if (!checkBox.isChecked) {
                updateNotificationAdapterItem(1, 0)
            }
        }
    }


    private fun displayNewAmountWithFees(): Double {
        var newAmount = 0.0
        for (item in listFeesDisplayed) {
            newAmount += item.value
        }
        Log.i("final amout of values ", newAmount.toString())
        finalAmount.text = newAmount.toString()
        return newAmount
    }

    private fun goToOtpFragment() {
        binding.btnDone.setOnClickListener {
            if (montantInput.text.toString() != "") {
                val otpService =
                    OtpService()
                val sendOtp = otpService.retrofit.create(OtpApi::class.java)
                val otpRequest = OtpRequest(
                    Consts.API_KEY,
                    Consts.API_SECRET,
                    sessionManager.fetchPhone(),
                    Consts.BRAND
                )
                if (finalAmount.text.toString().toDouble() > sessionManager.fetchAmount()!!.toDouble()) {
                    FrontUtils.showToast(requireContext(),Errors.AMOUNT_NOT_ENOUGH)
                } else {
                    doTransaction()
                    findNavController().navigate(R.id.action_selectMontantFragment_to_enterOtpFragment)
                }

//                sendOtp.sendOtpToUser(otpRequest).enqueue(
//                    object : Callback, retrofit2.Callback<OtpResponse> {
//                        override fun onResponse(
//                            call: Call<OtpResponse>,
//                            response: Response<OtpResponse>
//                        ) {
//                            println("this is the response $response")
//                            //sessionManager.saveRequestIdForTemporaryOtp(response.body()!!.request_id)
//                        }
//
//                        override fun onFailure(call: Call<OtpResponse>, t: Throwable) {
//                        }
//
//                    }
//                )
            } else {
                FrontUtils.showToast(requireContext(), ModalMessages.ERROR_FIELD_AMOUNT_EMPTY)
            }
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        currentSpinnerItem = p0?.selectedItem.toString()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }


}