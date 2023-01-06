package android.example.ebankingmobile.retrofit.api;

import android.example.ebankingmobile.retrofit.model.OtpRequest;
import android.example.ebankingmobile.retrofit.model.OtpResponse;
import android.example.ebankingmobile.retrofit.model.OtpVerificationRequest;
import android.example.ebankingmobile.retrofit.model.OtpVerificationResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface OtpApi {
    @POST("verify/json")
    @Headers({"Content-Type: application/json"})
    Call<OtpResponse> sendOtpToUser(
            @Body OtpRequest otpRequest);

    @POST("verify/check/json")
    @Headers({"Content-Type: application/json"})
    Call<OtpVerificationResponse> verifyOtp(
            @Body OtpVerificationRequest otpVerificationRequest);

}
