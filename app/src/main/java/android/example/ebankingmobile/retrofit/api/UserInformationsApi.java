package android.example.ebankingmobile.retrofit.api;

import android.example.ebankingmobile.retrofit.model.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserInformationsApi {
    @GET("services/apexrest/Login/")
    Call<User> getUserInformation(@Header("Authorization") String jwt,
                                  @Query("UserName") String username,
                                  @Query("Password") String password
    );

    @GET("services/data/v49.0/sobjects/Client__c/{id}")
    Call<User> getUserInformation(@Header("Authorization") String jwt,
                                  @Path("id") String id
    );


}