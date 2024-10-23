package com.sepa.back_end.entities;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    Long id;

    @Getter @Setter
    String nameCompany;

    @Getter @Setter
    String ibanCompany;

    @Getter @Setter
    String bicCompany;

    @Getter @Setter
    String nameClient;

    @Getter @Setter
    String ibanClient;

    @Getter @Setter
    String bicClient;

    @Getter @Setter
    String amount;

    @Getter @Setter
    String dateV1;

    @Getter @Setter
    String dateV2;

    @Override
    public String toString() {
        return "id=" + id +
                ", name company=" + nameCompany +
                ", iban company=" + ibanCompany +
                ", bic company=" + bicCompany +
                ", name client=" + nameClient +
                ", iban client=" + ibanClient +
                ", bic client=" + bicClient +
                ", cantidad=" + amount +'€' +
                ", fecha='" + dateV1 +' '+ dateV2 + '\'';
    }
}
