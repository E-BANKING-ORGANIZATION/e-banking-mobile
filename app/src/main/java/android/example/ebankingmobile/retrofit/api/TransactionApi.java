package android.example.ebankingmobile.retrofit.api;

import android.example.ebankingmobile.retrofit.model.TransactionRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface TransactionApi {
    @POST("services/apexrest/Transaction/")
    @Headers({"Content-Type: application/json"})
    Call<Void> doTransaction(@Header("Authorization") String authorization,
                       @Body TransactionRequest transactionRequest
    );
}
