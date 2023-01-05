package android.example.ebankingmobile.ui.fragments.beneficiare

import android.example.ebankingmobile.R
import android.example.ebankingmobile.databinding.FragmentAddBeneficiareBinding
import android.example.ebankingmobile.retrofit.api.BeneficiareApi
import android.example.ebankingmobile.retrofit.model.Beneficiaire
import android.example.ebankingmobile.retrofit.model.PostBeneficiareResponse
import android.example.ebankingmobile.retrofit.session.SessionManager
import android.example.ebankingmobile.retrofit.ws.BeneficiaireService
import android.example.ebankingmobile.utils.consts.Consts
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback


class AddBeneficiareDialogFragment : DialogFragment() {
    private lateinit var bindingAddBeneficiarePopUpModal: FragmentAddBeneficiareBinding
    private lateinit var nomBeneficiare: TextInputEditText
    private lateinit var prenomBeneficiare: TextInputEditText
    private lateinit var telephoneBeneficiare: TextInputEditText
    private lateinit var sessionManager: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val inflater = LayoutInflater.from(requireContext())
        val view = inflater.inflate(R.layout.fragment_add_beneficiare, null)
        bindingAddBeneficiarePopUpModal = FragmentAddBeneficiareBinding.bind(view)
        sessionManager = SessionManager(requireContext())
        nomBeneficiare = bindingAddBeneficiarePopUpModal.formTextFieldName
        prenomBeneficiare = bindingAddBeneficiarePopUpModal.formTextFieldPrenom
        telephoneBeneficiare = bindingAddBeneficiarePopUpModal.formTextFieldGSM

        return bindingAddBeneficiarePopUpModal.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val beneficiareService = BeneficiaireService()
        val beneficiaireApi =
            beneficiareService.retrofit.create(BeneficiareApi::class.java)

        bindingAddBeneficiarePopUpModal.formButtonSave.setOnClickListener {

            val beneficiaire = Beneficiaire()

            beneficiaire.Name = nomBeneficiare.text.toString()
            //beneficiaire.Prenom = prenomBeneficiare.text.toString()
            beneficiaire.Phone__c = telephoneBeneficiare.text.toString()
            beneficiaire.RelatedClient__c = sessionManager.fetchId()

            beneficiaireApi.postBeneficiare(
                Consts.BEARER + sessionManager.fetchAuthToken(),
                beneficiaire
            ).enqueue(
                object : Callback, retrofit2.Callback<PostBeneficiareResponse> {
                    override fun onFailure(call: Call<PostBeneficiareResponse>, t: Throwable) {
                        Log.i("error in the server !", "server")
                    }

                    override fun onResponse(
                        call: Call<PostBeneficiareResponse>,
                        response: Response<PostBeneficiareResponse>
                    ) {
                        if (response.code() >= 400) {
                            Log.i("error in the server !", "server")
//                            FrontUtils.showToast(
//                                requireContext(),
//                                "Error in the credentials ! Please try again !"
//                            )
                        } else {
                            Log.i("hahahaha", "with success !")
//                            FrontUtils.showToast(
//                                requireContext(),
//                                "you have just enter the beneficiare !"
//                            )
                        }
                    }
                }
            )
            this.dismiss()
        }
        // dialog logic !
    }

    companion object {
        const val TAG = "PurchaseConfirmationDialog"
    }
}