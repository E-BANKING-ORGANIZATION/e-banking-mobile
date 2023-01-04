package android.example.ebankingmobile.retrofit.api;

import android.example.ebankingmobile.retrofit.model.Beneficiaire;
import android.example.ebankingmobile.utils.consts.Consts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface BeneficiareApi {
    @POST("services/data/v49.0/sobjects/Beneficiaire__c/")
    @FormUrlEncoded
    Call<Beneficiaire> postBeneficiare(@Body Beneficiaire beneficiaire);

    @GET("services/apexrest/Beneficiaire/?AccId=a038d000006xKNuAAM")
    @Headers(Consts.REQUEST_WITH_HEADER_AUTHORIZATION + Consts.JWT)
    Call<List<Beneficiaire>> getListBeneficiaires();
}
