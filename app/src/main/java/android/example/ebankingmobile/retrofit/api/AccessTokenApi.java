package android.example.ebankingmobile.retrofit.api;

import android.example.ebankingmobile.retrofit.model.Beneficiaire;
import android.example.ebankingmobile.retrofit.model.LoginResponse;
import android.example.ebankingmobile.retrofit.model.User;
import android.example.ebankingmobile.utils.consts.Consts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface AccessTokenApi {
    @POST("services/oauth2/token?grant_type=password&client_id=3MVG9DREgiBqN9WlbAq75M8p0jSUIC16oHIgRFgDmco1Ye0OIkrZHl5.cZqRyHkhxcdzCVa4hFBS7VjYh0IXZ&client_secret=BE6188FC9BE8F88632A2EA0A541639DAFFC29F990C4E939A717DCCE2F204A3F2&username=aymen.boussalah01@gmail.com&password=EBANKING22VnSaZyXdWtLWb2opQ9btlXGcE")
    Call<LoginResponse> getAccessToken();



    @POST("services/data/v49.0/sobjects/Beneficiaire__c/")
    @FormUrlEncoded
    Call<Beneficiaire> postBeneficiare(@Body Beneficiaire beneficiaire);

    @GET("services/apexrest/Beneficiaire/?AccId=a038d000006xKNuAAM")
    @Headers(Consts.REQUEST_WITH_HEADER_AUTHORIZATION + Consts.JWT)
    Call<List<Beneficiaire>> getListBeneficiaires();
}
