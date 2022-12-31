package android.example.ebankingmobile.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

public interface MatchesApi {
    @GET("services/apexrest/Beneficiaire/?AccId=a038d000006xKNuAAM")
    Call<String> getMatchCode();
//    Call<String> getMatchCode(@Body Client);
}
