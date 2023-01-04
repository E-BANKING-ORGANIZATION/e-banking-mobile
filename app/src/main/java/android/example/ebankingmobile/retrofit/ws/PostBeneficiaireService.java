package android.example.ebankingmobile.retrofit.ws;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostBeneficiaireService {
    private Retrofit retrofit;
    private static final String URI = "https://curious-unicorn-ea3t6y-dev-ed.trailblaze.my.salesforce.com";

    public PostBeneficiaireService() {
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
