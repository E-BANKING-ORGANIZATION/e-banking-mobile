package android.example.ebankingmobile.ui.fragments.enterOtp

import android.app.AlertDialog
import android.example.ebankingmobile.R
import android.example.ebankingmobile.databinding.FragmentEnterOtpBinding
import android.example.ebankingmobile.retrofit.api.OtpApi
import android.example.ebankingmobile.retrofit.model.OtpVerificationRequest
import android.example.ebankingmobile.retrofit.model.OtpVerificationResponse
import android.example.ebankingmobile.retrofit.session.SessionManager
import android.example.ebankingmobile.utils.FrontUtils
import android.example.ebankingmobile.utils.consts.Consts
import android.example.ebankingmobile.utils.consts.ModalMessages
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback


class EnterOtpFragment : Fragment() {
    private lateinit var binding: FragmentEnterOtpBinding
    private lateinit var otpInput: EditText
    private lateinit var sessionManager: SessionManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_enter_otp, container, false)
        otpInput = binding.otpInput
        sessionManager = SessionManager(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        goToFinalisation()
        getOtpInputFromUser()
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
            verifyOtp()
        }
    }

    private fun verifyOtp() {
        if (otpInput.text.toString() != "") {
            val otpService =
                android.example.ebankingmobile.retrofit.ws.OtpService()
            val verifOtpService = otpService.retrofit.create(OtpApi::class.java)
            val otpVerificationRequest = OtpVerificationRequest()


            otpVerificationRequest.api_key = Consts.API_KEY
            otpVerificationRequest.api_secret = Consts.API_SECRET
            otpVerificationRequest.request_id = sessionManager.fetchRequestId()
            otpVerificationRequest.code = otpInput.text.toString()

            verifOtpService.verifyOtp(
                otpVerificationRequest
            ).enqueue(
                object : Callback, retrofit2.Callback<OtpVerificationResponse> {
                    override fun onResponse(
                        call: Call<OtpVerificationResponse>,
                        response: Response<OtpVerificationResponse>
                    ) {
                        val body = response.body()
                        if (body!!.status.toInt() == 0) {
                            FrontUtils.showToast(requireContext(), ModalMessages.OTP_SUCCESS)
                            findNavController().navigate(R.id.action_enterOtpFragment_to_finaliserTransactionFragment)
                        } else {
                            val builder = AlertDialog.Builder(requireContext())
                            //set title for alert dialog
                            builder.setTitle("Error in the OTP !")
                            //set message for alert dialog
                            builder.setIcon(android.R.drawable.ic_dialog_alert)
                            //performing negative action
                            builder.setNegativeButton("Cancel") { _, _ ->

                            }

                            //performing positive action
                            builder.setPositiveButton("Try again !") { _, _ ->

                            }

                            // Create the AlertDialog
                            val alertDialog: AlertDialog = builder.create()
                            // Set other dialog properties
                            alertDialog.setCancelable(false)
                            alertDialog.show()
                            FrontUtils.showToast(requireContext(), ModalMessages.OTP_FAILURE)
                        }
                    }

                    override fun onFailure(call: Call<OtpVerificationResponse>, t: Throwable) {
                    }
                }

            )
        } else {
            val builder = AlertDialog.Builder(requireContext())
            //set title for alert dialog
            builder.setTitle("Input is Empty")
            //set message for alert dialog
            builder.setIcon(android.R.drawable.ic_dialog_alert)
            //performing negative action
            builder.setNegativeButton("Cancel") { _, _ ->

            }
            // Create the AlertDialog
            val alertDialog: AlertDialog = builder.create()
            // Set other dialog properties
            alertDialog.setCancelable(false)
            alertDialog.show()
        }
    }

}