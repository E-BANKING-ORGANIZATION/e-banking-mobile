package android.example.ebankingmobile.retrofit.api;

import android.example.ebankingmobile.retrofit.model.LoginResponse;

import retrofit2.Call;
import retrofit2.http.POST;

public interface AccessTokenApi {
    @POST("services/oauth2/token?grant_type=password&client_id=3MVG9DREgiBqN9WlbAq75M8p0jSUIC16oHIgRFgDmco1Ye0OIkrZHl5.cZqRyHkhxcdzCVa4hFBS7VjYh0IXZ&client_secret=BE6188FC9BE8F88632A2EA0A541639DAFFC29F990C4E939A717DCCE2F204A3F2&username=aymen.boussalah01@gmail.com&password=EBANKING22VnSaZyXdWtLWb2opQ9btlXGcE")
    Call<LoginResponse> getAccessToken();
}
