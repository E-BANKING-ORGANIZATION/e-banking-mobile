package android.example.ebankingmobile.ui.fragments.beneficiare

import android.example.ebankingmobile.R
import android.example.ebankingmobile.databinding.FragmentSelectBeneficiaireBinding
import android.example.ebankingmobile.retrofit.api.BeneficiareApi
import android.example.ebankingmobile.retrofit.api.TransactionApi
import android.example.ebankingmobile.retrofit.model.Beneficiaire
import android.example.ebankingmobile.retrofit.model.TransactionRequest
import android.example.ebankingmobile.retrofit.session.SessionManager
import android.example.ebankingmobile.retrofit.ws.BeneficiaireService
import android.example.ebankingmobile.utils.FrontUtils
import android.example.ebankingmobile.utils.consts.Consts
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class SelectBeneficiaire : Fragment(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: FragmentSelectBeneficiaireBinding
    private lateinit var listBeneficiare: List<Beneficiaire>
    private lateinit var spinner: Spinner
    private lateinit var sessionManager: SessionManager
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
        sessionManager = SessionManager(requireContext())
        spinner = binding.listBeneficiare
        listBeneficiare = arrayListOf()
        //getListBeneficiare()
        // listBeneficiare = listOf("JAOUA", "YESSINE", "ZIKO")
        //configurationSpinnerWithBeneficiaresInOurDatabase()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        goToSelectMontantFragment()
        getListBeneficiare()
        popUpModal()
    }

    private fun configurationSpinnerWithBeneficiaresInOurDatabase(listBeneficiares: List<Beneficiaire>) {

        val listNamesBeneficiaire = ArrayList<String>()

        for (i in listBeneficiares.indices) {
            listNamesBeneficiaire.add(listBeneficiares[i].Name)
        }

        val adapter: ArrayAdapter<CharSequence> = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            listNamesBeneficiaire as List<CharSequence>
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

    private fun getListBeneficiare() {
        val beneficiareService = BeneficiaireService()
        val beneficiaireApi =
            beneficiareService.retrofit.create(BeneficiareApi::class.java)
        beneficiaireApi.getListBeneficiaires(
            Consts.BEARER + sessionManager.fetchAuthToken(),
            sessionManager.fetchId()
        ).enqueue(
            object : Callback, retrofit2.Callback<List<Beneficiaire>> {
                override fun onFailure(call: Call<List<Beneficiaire>>, t: Throwable) {
                    FrontUtils.showToast(
                        requireContext(),
                        "Error in the server !"
                    )
                }

                override fun onResponse(
                    call: Call<List<Beneficiaire>>,
                    response: Response<List<Beneficiaire>>
                ) {
                    if (response.code() >= 400) {
                        FrontUtils.showToast(
                            requireContext(),
                            "Error in the credentials ! Please try again !"
                        )
                    } else {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            configurationSpinnerWithBeneficiaresInOurDatabase(responseBody)
                        }
                    }
                }
            }
        )
    }


}