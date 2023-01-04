package android.example.ebankingmobile.retrofit.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponse {
    public String access_token;
    public String instance_url;
    public String id;
    public String token_type;
    private String issued_at;
    private String signature;
    public Integer statusCode;
}
