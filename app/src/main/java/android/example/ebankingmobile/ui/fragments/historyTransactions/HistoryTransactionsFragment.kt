package android.example.ebankingmobile.ui.fragments.historyTransactions

import android.example.ebankingmobile.R
import android.example.ebankingmobile.databinding.FragmentHistoryTransactionsBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class HistoryTransactionsFragment : Fragment() {
    private lateinit var binding : FragmentHistoryTransactionsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_history_transactions,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        goBackToHome()
    }

    private fun goBackToHome() {
        binding.goBackToProfileButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }

}