package android.example.ebankingmobile.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

public interface MatchesApi {
    @GET("/grounds/number-owner-grounds/")
    Call<String> getMatchCode();

//    Call<String> getMatchCode(@Body Client);
}
