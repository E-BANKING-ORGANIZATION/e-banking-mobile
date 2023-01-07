package android.example.ebankingmobile.retrofit.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TransactionRequest {
    public Integer amount;
    public String id;
    public boolean bl;
    public String picklist;
    public String BenefId;
}
