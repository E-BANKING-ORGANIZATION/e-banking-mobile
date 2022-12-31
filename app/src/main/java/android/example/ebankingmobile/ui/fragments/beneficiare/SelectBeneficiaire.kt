package android.example.ebankingmobile.ui.fragments.beneficiare

import android.example.ebankingmobile.R
import android.example.ebankingmobile.databinding.FragmentSelectBeneficiaireBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class SelectBeneficiaire : Fragment(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: FragmentSelectBeneficiaireBinding
    private lateinit var listBeneficiare: List<String>
    private lateinit var spinner: Spinner
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_select_beneficiaire,
            container,
            false
        )
        spinner = binding.listBeneficiare
        listBeneficiare = listOf("JAOUA", "YESSINE", "ZIKO")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        goToSelectMontantFragment()
        popUpModal()
        configurationSpinnerWithBeneficiaresInOurDatabase()
    }

    private fun configurationSpinnerWithBeneficiaresInOurDatabase() {
        val adapter: ArrayAdapter<CharSequence> = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            listBeneficiare as List<CharSequence>
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = this
    }

    private fun popUpModal() {
        val fab: View = binding.btnAdd
        fab.setOnClickListener {
            showDialogAddBeneficiare()
        }
    }

    private fun showDialogAddBeneficiare() {
        AddBeneficiareDialogFragment().show(
            childFragmentManager, AddBeneficiareDialogFragment.TAG
        )
    }


    private fun goToSelectMontantFragment() {
        binding.btnDone.setOnClickListener {
            findNavController().navigate(R.id.action_selectBeneficiaire_to_selectMontantFragment)
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }


}