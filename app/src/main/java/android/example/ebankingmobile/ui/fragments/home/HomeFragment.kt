package android.example.ebankingmobile.ui.fragments.home

import android.example.ebankingmobile.R
import android.example.ebankingmobile.databinding.FragmentHomeBinding
import android.example.ebankingmobile.retrofit.MatchesApi
import android.example.ebankingmobile.retrofit.RetrofitService
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        goToTransfertFragment()
        goToHistoryPayments()
    }

    private fun goToTransfertFragment() {
        binding.faireUnTransfertButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_selectBeneficiaire)
        }
    }

    private fun goToHistoryPayments() {
        val retrofitService = RetrofitService()
        val matchesApi = retrofitService.retrofit.create(MatchesApi::class.java)
        binding.historyPaymentsButton.setOnClickListener {
            matchesApi.matchCode.enqueue(object : Callback, retrofit2.Callback<String> {
                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.i("error", call.toString())
                }

                override fun onResponse(call: Call<String>, response: Response<String>) {
                    response.body()?.let { it1 -> Log.i("success", it1) }
                }

            })
            findNavController().navigate(R.id.action_homeFragment_to_historyTransactionsFragment)
        }
    }

}