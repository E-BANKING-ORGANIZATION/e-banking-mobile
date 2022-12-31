package android.example.ebankingmobile.ui.fragments.login

import android.example.ebankingmobile.R
import android.example.ebankingmobile.databinding.FragmentLoginBinding
import android.example.ebankingmobile.retrofit.ClientApi
import android.example.ebankingmobile.retrofit.RetrofitService
import android.example.ebankingmobile.retrofit.model.ResponseLogin
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var userNameEditText: EditText
    private lateinit var passwordEditText: TextInputLayout
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        userNameEditText = binding.userNameInput
        passwordEditText = binding.passwordInput
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        goToHomePage()
        signIn()
    }

    private fun signIn() {

    }

    private fun goToHomePage() {
        val retrofitService = RetrofitService()
        val clientApi = retrofitService.retrofit.create(ClientApi::class.java)
        binding.goToHomeActivity.setOnClickListener {

            clientApi.loginUser().enqueue(
                object : Callback, retrofit2.Callback<ResponseLogin> {
                    override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                        Log.i("error", call.toString())
                    }
                    override fun onResponse(
                        call: Call<ResponseLogin>,
                        response: Response<ResponseLogin>
                    ) {
                        response.body()?.let { it1 -> Log.i("success", it1.access_token) }
                    }

                }
            )
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }
    }

}