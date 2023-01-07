package android.example.ebankingmobile.ui.fragments.login

import android.example.ebankingmobile.R
import android.example.ebankingmobile.databinding.FragmentLoginBinding
import android.example.ebankingmobile.retrofit.api.AccessTokenApi
import android.example.ebankingmobile.retrofit.api.UserInformationsApi
import android.example.ebankingmobile.retrofit.model.LoginResponse
import android.example.ebankingmobile.retrofit.model.User
import android.example.ebankingmobile.retrofit.session.SessionManager
import android.example.ebankingmobile.retrofit.ws.AccessTokenService
import android.example.ebankingmobile.retrofit.ws.UserInformationService
import android.example.ebankingmobile.utils.FrontUtils
import android.example.ebankingmobile.utils.consts.Consts
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var userNameEditText: EditText
    private lateinit var passwordEditText: TextInputLayout
    private lateinit var sessionManager: SessionManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        userNameEditText = binding.userNameInput
        passwordEditText = binding.passwordInput
        sessionManager = SessionManager(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        goToHomePage()
        checkUserAlreadyLoggedIn()
    }

    private fun checkUserAlreadyLoggedIn() {
        if (FrontUtils.checkUserLoggedIn(requireContext())) {
            //Utils.startActivity(requireContext(), MainActivity::class.java)
            requireView().findNavController()
                .navigate(R.id.action_loginFragment_to_homeFragment)
        }
    }


    private fun goToHomePage() {
        val accessTokenService =
            AccessTokenService()
        val accessTokenApi = accessTokenService.retrofit.create(AccessTokenApi::class.java)
        val userInformationService = UserInformationService()
        val userInformationApi =
            userInformationService.retrofit.create(UserInformationsApi::class.java)

        binding.goToHomeActivity.setOnClickListener {
            val userNameText = userNameEditText.text.toString()
            val passwordText = passwordEditText.editText?.text.toString()
            if (!FrontUtils.checkInputsEmptyOrNot(userNameText, passwordText)) {
                FrontUtils.showToast(requireContext(), "you have to check your inputs !")
            } else {
                accessTokenApi.accessToken.enqueue(
                    object : Callback, retrofit2.Callback<LoginResponse> {
                        override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                            Log.i("error", call.toString())
                        }

                        override fun onResponse(
                            call: Call<LoginResponse>,
                            response: Response<LoginResponse>
                        ) {
                            val loginResponse = response.body()

                            if (loginResponse != null) {
                                if (loginResponse.access_token != null) {
                                    sessionManager.saveAuthToken(loginResponse.access_token)
                                } else {
                                    // FrontUtils.showToast(requireContext(), "error of token !")
                                }
                            }
                        }

                    }
                )
                userInformationApi.getUserInformation(
                    Consts.BEARER + sessionManager.fetchAuthToken(),
                    userNameText,
                    passwordText
                )
                    .enqueue(
                        object : Callback, retrofit2.Callback<User> {
                            override fun onFailure(call: Call<User>, t: Throwable) {
                                FrontUtils.showToast(
                                    requireContext(),
                                    "Error in the server !"
                                )
                            }

                            override fun onResponse(
                                call: Call<User>,
                                response: Response<User>
                            ) {
                                if (response.code() >= 400) {
                                    FrontUtils.showToast(
                                        requireContext(),
                                        "Error in the credentials ! Please try again !"
                                    )
                                } else {
                                    val responseBody = response.body()
                                    if (responseBody != null) {
                                        //sessionManager.saveUsername(userNameText)
                                        sessionManager.saveId(responseBody.Id)
                                        sessionManager.savePhone(responseBody.Phone__c)
                                        sessionManager.saveAmount(responseBody.Amount__c)
                                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                                    }
                                }
                            }
                        }
                    )
            }
        }
    }

}