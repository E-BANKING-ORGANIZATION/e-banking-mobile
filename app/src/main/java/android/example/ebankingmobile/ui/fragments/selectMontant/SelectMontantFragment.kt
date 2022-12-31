package android.example.ebankingmobile.ui.fragments.selectMontant

import android.annotation.SuppressLint
import android.content.Context
import android.example.ebankingmobile.R
import android.example.ebankingmobile.databinding.FragmentSelectMontantBinding
import android.example.ebankingmobile.utils.TransactionUtils
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
    private lateinit var editText: EditText
    private lateinit var constraintLayout: ConstraintLayout
    private lateinit var spinner: Spinner
    private lateinit var listNotifications: List<String>
    private var currentSpinnerItem: String = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_select_montant, container, false)
        listStrings = java.util.ArrayList()
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

        editText = binding.montantInput
        constraintLayout = binding.wholeLayoutContainer
        spinner = binding.notifChoiceList


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        disablingFocusWhenUserClicksOtherThanKeyboard()
        displayNewAmountWithFees()
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
        editText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                editText.clearFocus()
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
        editText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                Toast.makeText(requireContext(), "writing", Toast.LENGTH_SHORT).show()
            } else {
                addItemToAdapterItems(editText.text.toString().toLong(), 1)
            }
        }
    }

    private fun addItemToAdapterItems(fee: Long, typeFee: Int) {
        // this line adds the data of your EditText and puts in your array
        listStrings.add(TransactionUtils.returnFinalLineInListTransactionFees(fee, typeFee))
        // next thing you have to do is check if your adapter has changed
        arrayAdapter.notifyDataSetChanged()
    }

    private fun notificationTransferCheckBoxLogic() {
        binding.notificationTransferCheckBox.setOnClickListener {
            if (checkBox.isChecked) {
                Log.i("check box is clicked !", "haha")
                addItemToAdapterItems(5000, 2)
            }
        }
    }

    private fun displayNewAmountWithFees() {
        val newAmount: Long = TransactionUtils.calculateFinalTransferAmountWithFees(500, 1)
        binding.valueFinalAmountAfterFees.text = newAmount.toString()
    }

    private fun goToOtpFragment() {
        binding.btnDone.setOnClickListener {
            findNavController().navigate(R.id.action_selectMontantFragment_to_enterOtpFragment)
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        currentSpinnerItem = p0?.selectedItem.toString()
        Log.i("item selected ", "$p2 $p3")
        Log.i(
            "item selected ",
            TransactionUtils.calculateNotificationFee(currentSpinnerItem, 15).toString()
        )

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

}