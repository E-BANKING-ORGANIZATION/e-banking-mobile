package android.example.ebankingmobile.retrofit.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Beneficiaire {
    public String Name;
    public String Prenom;
    public String Phone__c;
    public String RelatedClient__c;
    public String Id;
    public String GSM;
}
