package android.example.ebankingmobile.retrofit.api;

import android.example.ebankingmobile.retrofit.model.Beneficiaire;
import android.example.ebankingmobile.retrofit.model.PostBeneficiareResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface BeneficiareApi {
    @POST("services/data/v49.0/sobjects/Beneficiaire__c/")
    @Headers({"Content-Type: application/json"})
    Call<PostBeneficiareResponse> postBeneficiare(
            @Header("Authorization") String authorization,
            @Body Beneficiaire beneficiaire);

    @GET("services/apexrest/Beneficiaire/")
    Call<List<Beneficiaire>> getListBeneficiaires(
            @Header("Authorization") String authorization,
            @Query("AccId") String accId);
}
