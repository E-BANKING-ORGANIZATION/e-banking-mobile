package android.example.ebankingmobile.ui.fragments.beneficiare

import android.app.Dialog
import android.example.ebankingmobile.R
import android.example.ebankingmobile.databinding.FragmentAddBeneficiareBinding
import android.example.ebankingmobile.databinding.FragmentSelectBeneficiaireBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class SelectBeneficiaire : Fragment() {
    private lateinit var binding: FragmentSelectBeneficiaireBinding
    private lateinit var bindingAddBeneficiarePopUpModal: FragmentAddBeneficiareBinding
    private lateinit var dialog: Dialog
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val inflater = LayoutInflater.from(requireActivity().applicationContext)
        //val view = inflater.inflate(R.layout.fragment_add_beneficiare, null)

//        bindingAddBeneficiarePopUpModal = FragmentAddBeneficiareBinding.bind(view)
//        dialog = Dialog(requireContext())
//        dialog.setContentView(bindingAddBeneficiarePopUpModal.root)
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_select_beneficiaire,
            container,
            false
        )

        //popUpModal()
        return binding.root
    }

    private fun popUpModal() {
        val fab: View = binding.btnAdd
        fab.setOnClickListener {
            showDialogAddBeneficiare()
        }
    }

    private fun showDialogAddBeneficiare() {
        dialog.show()
        bindingAddBeneficiarePopUpModal.formButtonSave.setOnClickListener {
            dialog.dismiss()
        }
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