package android.example.ebankingmobile.retrofit.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OtpRequest {
    public String api_key;


    public String api_secret;
    public String number;
    public String brand;
    public OtpRequest(String api_key, String api_secret, String number, String brand) {
        this.api_key = api_key;
        this.api_secret = api_secret;
        this.number = number;
        this.brand = brand;
    }
}
