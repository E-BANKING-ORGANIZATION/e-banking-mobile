package android.example.ebankingmobile.ui.fragments

import android.example.ebankingmobile.R
import android.example.ebankingmobile.databinding.FragmentEnterOtpBinding
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController


class EnterOtpFragment : Fragment() {
    private lateinit var binding : FragmentEnterOtpBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_enter_otp,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        goToFinalisation()
    }

    private fun goToFinalisation() {
        binding.btnDone.setOnClickListener {
            findNavController().navigate(R.id.action_enterOtpFragment_to_finaliserTransactionFragment)
        }
    }

}