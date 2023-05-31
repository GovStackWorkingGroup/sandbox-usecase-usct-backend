package global.govstack.mocksris.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Beneficiary {
    @JsonProperty("PayeeFunctionalID")
    private String payeeFunctionalID;
    @JsonProperty("PaymentModality")
    private String paymentModality;
    @JsonProperty("FinancialAddress")
    private String financialAddress;

    public Beneficiary(String payeeFunctionalID, String paymentModality, String financialAddress) {
        this.payeeFunctionalID = payeeFunctionalID;
        this.paymentModality = paymentModality;
        this.financialAddress = financialAddress;
    }

    public String getPayeeFunctionalID() {
        return payeeFunctionalID;
    }

    public void setPayeeFunctionalID(String payeeFunctionalID) {
        this.payeeFunctionalID = payeeFunctionalID;
    }

    public String getPaymentModality() {
        return paymentModality;
    }

    public void setPaymentModality(String paymentModality) {
        this.paymentModality = paymentModality;
    }

    public String getFinancialAddress() {
        return financialAddress;
    }

    public void setFinancialAddress(String financialAddress) {
        this.financialAddress = financialAddress;
    }
}
