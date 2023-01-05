package android.example.ebankingmobile.ui.fragments.selectMontant

import android.annotation.SuppressLint
import android.content.Context
import android.example.ebankingmobile.R
import android.example.ebankingmobile.databinding.FragmentSelectMontantBinding
import android.example.ebankingmobile.utils.FrontUtils
import android.example.ebankingmobile.utils.TransactionUtils
import android.example.ebankingmobile.utils.consts.Consts
import android.example.ebankingmobile.utils.consts.NotificationNames
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


class SelectMontantFragment : Fragment(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: FragmentSelectMontantBinding
    private lateinit var arrayAdapter: ArrayAdapter<String>
    private lateinit var listStrings: ArrayList<String>
    private lateinit var listView: ListView
    private lateinit var checkBox: CheckBox
    private lateinit var montantInput: EditText
    private lateinit var constraintLayout: ConstraintLayout
    private lateinit var spinner: Spinner
    private lateinit var listNotifications: List<String>
    private var currentSpinnerItem: String = ""
    private lateinit var listFeesDisplayed: HashMap<String, Long>
    private lateinit var finalAmount: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_select_montant, container, false)
        listFeesDisplayed = HashMap(3)

        listFeesDisplayed[Consts.AMOUNT] = 0
        listFeesDisplayed[Consts.NOTIFICATION_TRANSFER_FEES] = 0
        listFeesDisplayed[Consts.CHARGE_FEES] = 0

        listStrings = java.util.ArrayList()

        listStrings.add(Consts.AMOUNT + listFeesDisplayed[Consts.AMOUNT])
        listStrings.add(Consts.NOTIFICATION_TRANSFER_FEES + listFeesDisplayed[Consts.NOTIFICATION_TRANSFER_FEES])
        listStrings.add(Consts.CHARGE_FEES + listFeesDisplayed[Consts.CHARGE_FEES])

        listView = binding.listFeesOfTransfer
        arrayAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, listStrings)
        listView.adapter = arrayAdapter
        checkBox = binding.notificationTransferCheckBox

        listNotifications = listOf(
            NotificationNames.NOTHING,
            NotificationNames.FIRST_NOTIFICATION_CHOICE,
            NotificationNames.SECOND_NOTIFICATION_CHOICE
        )

        montantInput = binding.montantInput
        constraintLayout = binding.wholeLayoutContainer
        spinner = binding.notifChoiceList

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
                }
            }
        }
    }

    private fun updateNotificationAdapterItem(index: Int, amount: Long) {
        val newString = TransactionUtils.returnFinalLineInListTransactionFees(amount, index)
        listStrings[index] = newString
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
                    updateNotificationAdapterItem(1, 5000)
                }
            } else if (!checkBox.isChecked) {
                updateNotificationAdapterItem(1, 0)
            }
        }
    }


    private fun displayNewAmountWithFees() {
        var newAmount: Long = 0
        for (item in listFeesDisplayed) {
            Log.i("hahaha",item.value.toString())
        }
        Log.i("final amout of values ", newAmount.toString())
        finalAmount.text = newAmount.toString()
    }

    private fun goToOtpFragment() {
        binding.btnDone.setOnClickListener {
            findNavController().navigate(R.id.action_selectMontantFragment_to_enterOtpFragment)
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        currentSpinnerItem = p0?.selectedItem.toString()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

}