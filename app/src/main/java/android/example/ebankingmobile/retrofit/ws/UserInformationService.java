package android.example.ebankingmobile.retrofit.ws;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserInformationService {
    private Retrofit retrofit;
    private static final String URI = "https://ebanking-dev-ed.trailblaze.my.salesforce.com";

    public UserInformationService() {
        initializeRetrofit();
    }

    private void initializeRetrofit() {

        retrofit = new Retrofit.Builder()
                .baseUrl(URI)
//                .baseUrl(URL + TWO_POINTS + PORT)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
