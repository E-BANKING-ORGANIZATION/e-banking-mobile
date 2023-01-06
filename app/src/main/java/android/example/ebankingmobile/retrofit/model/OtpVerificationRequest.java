package android.example.ebankingmobile.retrofit.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OtpVerificationRequest {
    public String api_key;
    public String api_secret;
    public String request_id;
    public String code;
}
