package android.example.ebankingmobile.ui.fragments.beneficiare

import android.example.ebankingmobile.R
import android.example.ebankingmobile.databinding.FragmentAddBeneficiareBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment

class AddBeneficiareDialogFragment : DialogFragment() {
    private lateinit var bindingAddBeneficiarePopUpModal: FragmentAddBeneficiareBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val inflater = LayoutInflater.from(requireContext())
        val view = inflater.inflate(R.layout.fragment_add_beneficiare, null)
        bindingAddBeneficiarePopUpModal = FragmentAddBeneficiareBinding.bind(view)
        return bindingAddBeneficiarePopUpModal.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bindingAddBeneficiarePopUpModal.formButtonSave.setOnClickListener {
            this.dismiss()
        }
        // dialog logic !
    }

    companion object {
        const val TAG = "PurchaseConfirmationDialog"
    }
}