package global.govstack.mocksris.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BeneficiaryRequest {
    @JsonProperty("RequestID")
    private String requestId;
    @JsonProperty("SourceBBID")
    private String sourceBBID;
    @JsonProperty("Beneficiaries")
    private List<Beneficiary> beneficiaries;

    public BeneficiaryRequest(String requestId, String sourceBBID, List<Beneficiary> beneficiaries) {
        this.requestId = requestId;
        this.sourceBBID = sourceBBID;
        this.beneficiaries = beneficiaries;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getSourceBBID() {
        return sourceBBID;
    }

    public void setSourceBBID(String sourceBBID) {
        this.sourceBBID = sourceBBID;
    }

    public List<Beneficiary> getBeneficiaries() {
        return beneficiaries;
    }

    public void setBeneficiaries(List<Beneficiary> beneficiaries) {
        this.beneficiaries = beneficiaries;
    }
}
