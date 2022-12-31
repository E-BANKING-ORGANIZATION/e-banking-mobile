package android.example.ebankingmobile.retrofit;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private Retrofit retrofit;
    private static final String URL = "http://192.168.100.164";
    private static final String TWO_POINTS = ":";
    private static final String PORT = "8083";
    private static final String URI = "https://curious-unicorn-ea3t6y-dev-ed.trailblaze.my.salesforce.com/services/apexrest/Beneficiaire/?AccId=a038d000006xKNuAAM";

    public RetrofitService() {
        initializeRetrofit();
    }

    private void initializeRetrofit() {

        retrofit = new Retrofit.Builder()
                .baseUrl("https://login.salesforce.com")
//                .baseUrl(URL + TWO_POINTS + PORT)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
