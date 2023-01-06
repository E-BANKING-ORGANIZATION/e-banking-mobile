package android.example.ebankingmobile.retrofit.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OtpResponse {
    public String request_id;
    public String status;
}
