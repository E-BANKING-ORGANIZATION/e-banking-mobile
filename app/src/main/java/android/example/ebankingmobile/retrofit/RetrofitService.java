package android.example.ebankingmobile.retrofit;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private Retrofit retrofit;
    private static final String URL = "http://192.168.100.164";
    private static final String TWO_POINTS = ":";
    private static final String PORT = "8083";

    public RetrofitService() {
        initializeRetrofit();
    }

    private void initializeRetrofit() {

        retrofit = new Retrofit.Builder()
                .baseUrl(URL + TWO_POINTS + PORT)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
