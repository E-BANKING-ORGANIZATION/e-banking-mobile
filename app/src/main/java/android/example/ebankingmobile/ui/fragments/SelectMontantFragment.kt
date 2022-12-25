package android.example.ebankingmobile.ui.fragments

import android.example.ebankingmobile.R
import android.example.ebankingmobile.databinding.FragmentSelectMontantBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController


class SelectMontantFragment : Fragment() {
    private lateinit var binding: FragmentSelectMontantBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_select_montant, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        goToOtpFragment()
    }

    private fun goToOtpFragment() {
        binding.btnDone.setOnClickListener {
            findNavController().navigate(R.id.action_selectMontantFragment_to_enterOtpFragment)
        }
    }

}