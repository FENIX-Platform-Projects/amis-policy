package org.fao.oecd.policy.download.dto;

public class CountryPolicyTypeMonth {
    public Integer month;
    public String policyType;
    public String country;

    public CountryPolicyTypeMonth() {
    }

    public CountryPolicyTypeMonth(String country, String policyType, Integer month) {
        this.month = month;
        this.policyType = policyType;
        this.country = country;
    }
}
