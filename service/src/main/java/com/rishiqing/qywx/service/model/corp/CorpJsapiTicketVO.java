package com.rishiqing.qywx.service.model.corp;

public class CorpJsapiTicketVO {
    private Long id;

    private String suiteKey;
    private String corpId;
    private String corpJsapiTicket;
    private Long expiresIn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSuiteKey() {
        return suiteKey;
    }

    public void setSuiteKey(String suiteKey) {
        this.suiteKey = suiteKey;
    }

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public String getCorpJsapiTicket() {
        return corpJsapiTicket;
    }

    public void setCorpJsapiTicket(String corpJsapiTicket) {
        this.corpJsapiTicket = corpJsapiTicket;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    @Override
    public String toString() {
        return "CorpJsapiTicketVO{" +
                "id=" + id +
                ", suiteKey='" + suiteKey + '\'' +
                ", corpId='" + corpId + '\'' +
                ", corpJsapiTicket='" + corpJsapiTicket + '\'' +
                ", expiresIn=" + expiresIn +
                '}';
    }
}
