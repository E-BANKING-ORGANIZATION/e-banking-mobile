package android.example.ebankingmobile.ui.fragments

import android.example.ebankingmobile.R
import android.example.ebankingmobile.databinding.FragmentHomeBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
        binding.historyPaymentsButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_historyTransactionsFragment)
        }
    }

}