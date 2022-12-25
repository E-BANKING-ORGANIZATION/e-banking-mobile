package android.example.ebankingmobile.ui.fragments

import android.example.ebankingmobile.R
import android.example.ebankingmobile.databinding.FragmentFinaliserTransactionBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController


class FinaliserTransactionFragment : Fragment() {

    private lateinit var binding : FragmentFinaliserTransactionBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_finaliser_transaction,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        goBackToProfile()
    }

    private fun goBackToProfile() {
        binding.btnDone.setOnClickListener {
            findNavController().navigate(R.id.action_finaliserTransactionFragment_to_homeFragment)
        }
    }

}