package android.example.ebankingmobile.ui.fragments.enterOtp

import android.app.AlertDialog
import android.example.ebankingmobile.R
import android.example.ebankingmobile.databinding.FragmentEnterOtpBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController


class EnterOtpFragment : Fragment() {
    private lateinit var binding: FragmentEnterOtpBinding
    private lateinit var otpInput: EditText
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_enter_otp, container, false)
        otpInput = binding.otpInput
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        goToFinalisation()
        getOtpInputFromUser()
        verifyOtp()
    }

    private fun getOtpInputFromUser() {
        otpInput.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                Toast.makeText(requireContext(), "writing", Toast.LENGTH_SHORT).show()
            } else {

            }
        }
    }

    private fun goToFinalisation() {
        binding.btnDone.setOnClickListener {
            findNavController().navigate(R.id.action_enterOtpFragment_to_finaliserTransactionFragment)
        }
    }

    private fun verifyOtp() {
        if (otpInput.text.toString() != "") {
            val code = otpInput.text.toString().toInt()
            if (code != 451) {
                if (otpInput.text.toString() != "") {
                    val builder = AlertDialog.Builder(requireContext())
                    //set title for alert dialog
                    builder.setTitle("jaaah")
                    //set message for alert dialog
                    builder.setMessage(R.string.Tags)
                    builder.setIcon(android.R.drawable.ic_dialog_alert)

                    //performing positive action
                    builder.setPositiveButton("Yes") { _, _ ->
                        Toast.makeText(requireContext(), "clicked yes", Toast.LENGTH_LONG).show()
                    }
                    //performing cancel action
                    builder.setNeutralButton("Cancel") { _, _ ->
                        Toast.makeText(
                            requireContext(),
                            "clicked cancel\n operation cancel",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    //performing negative action
                    builder.setNegativeButton("No") { _, _ ->
                        Toast.makeText(requireContext(), "clicked No", Toast.LENGTH_LONG).show()
                    }
                    // Create the AlertDialog
                    val alertDialog: AlertDialog = builder.create()
                    // Set other dialog properties
                    alertDialog.setCancelable(false)
                    alertDialog.show()

                }
            }
        }

    }

}