package android.example.ebankingmobile.ui.fragments.home

import android.example.ebankingmobile.R
import android.example.ebankingmobile.databinding.FragmentHomeBinding
import android.example.ebankingmobile.retrofit.api.UserInformationsApi
import android.example.ebankingmobile.retrofit.model.User
import android.example.ebankingmobile.retrofit.session.SessionManager
import android.example.ebankingmobile.retrofit.ws.AccessTokenService
import android.example.ebankingmobile.retrofit.ws.UserInformationService
import android.example.ebankingmobile.utils.FrontUtils
import android.example.ebankingmobile.utils.consts.Consts
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var sessionManager: SessionManager
    private lateinit var username: TextView
    private lateinit var amount: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        sessionManager = SessionManager(requireContext())
        username = binding.userName
        amount = binding.amountLabel
        setHasOptionsMenu(true)
        getUserInformationFromDb()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        goToTransfertFragment()
        goToHistoryPayments()
    }

    private fun getUserInformationFromDb() {
        val userInformationService = UserInformationService()
        val userInformationApi =
            userInformationService.retrofit.create(UserInformationsApi::class.java)

        userInformationApi.getUserInformation(
            Consts.BEARER + sessionManager.fetchAuthToken(),
            sessionManager.fetchId()
        ).enqueue(
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
                            username.text = responseBody.Name
                            amount.text = responseBody.Amount__c
                        }
                    }
                }
            }
        )

    }

    private fun goToTransfertFragment() {
        binding.faireUnTransfertButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_selectBeneficiaire)
        }
    }

    private fun goToHistoryPayments() {
        val accessTokenService =
            AccessTokenService()
//        val matchesApi = loginService.retrofit.create(MatchesApi::class.java)
        binding.historyPaymentsButton.setOnClickListener {
//            matchesApi.matchCode.enqueue(object : Callback, retrofit2.Callback<String> {
//                override fun onFailure(call: Call<String>, t: Throwable) {
//                    Log.i("error", call.toString())
//                }
//
//                override fun onResponse(call: Call<String>, response: Response<String>) {
//                    response.body()?.let { it1 -> Log.i("success", it1) }
//                }
//
//            })
            findNavController().navigate(R.id.action_homeFragment_to_historyTransactionsFragment)
        }
    }

    private fun logOutButton() {
        FrontUtils.removingInformationFromSharedPreferences(requireContext())
        findNavController().navigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.logMenu -> {
                logOutButton()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}