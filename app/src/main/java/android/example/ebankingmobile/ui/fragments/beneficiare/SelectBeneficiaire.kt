package android.example.ebankingmobile.ui.fragments.beneficiare

import android.example.ebankingmobile.R
import android.example.ebankingmobile.databinding.FragmentSelectBeneficiaireBinding
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController

class SelectBeneficiaire : Fragment() {
    private lateinit var binding : FragmentSelectBeneficiaireBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater , R.layout.fragment_select_beneficiaire,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        goToSelectMontantFragment()
    }

    private fun goToSelectMontantFragment() {
        binding.btnDone.setOnClickListener {
            findNavController().navigate(R.id.action_selectBeneficiaire_to_selectMontantFragment)
        }
    }




}