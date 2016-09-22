package org.fao.amis.policy.dto.search;

public class CplSearch {

    private int countryCode;
    private int policyDomainCode;
    private int policyMeasureCode;
    private int commodityClassCode;

    public CplSearch() {
    }

    ;

    public CplSearch(int policyMeasureCode, int policyDomainCode, int countryCode, int commodityClassCode) {
        this.countryCode = countryCode;
        this.policyDomainCode = policyDomainCode;
        this.policyMeasureCode = policyMeasureCode;
        this.commodityClassCode = commodityClassCode;
    }

    public int getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(int countryCode) {
        this.countryCode = countryCode;
    }

    public int getPolicyDomainCode() {
        return policyDomainCode;
    }

    public void setPolicyDomainCode(int policyDomainCode) {
        this.policyDomainCode = policyDomainCode;
    }

    public int getPolicyMeasureCode() {
        return policyMeasureCode;
    }

    public void setPolicyMeasureCode(int policyMeasureCode) {
        this.policyMeasureCode = policyMeasureCode;
    }

    public int getCommodityClassCode() {
        return commodityClassCode;
    }

    public void setCommodityClassCode(int commodityClassCode) {
        this.commodityClassCode = commodityClassCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        //if (o == 0 || getClass() != o.getClass()) return false;

        CplSearch that = (CplSearch) o;

        if (commodityClassCode != that.commodityClassCode) return false;
        if (countryCode != that.countryCode) return false;
        if (policyDomainCode != that.policyDomainCode) return false;
        if (policyMeasureCode != that.policyMeasureCode) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = countryCode;
        result = 31 * result + policyDomainCode;
        result = 31 * result + policyMeasureCode;
        result = 31 * result + commodityClassCode;
        return result;
    }

    @Override
    public String toString() {
        return "SearchCplBean{" +
                "countryCode=" + countryCode +
                ", policyDomainCode=" + policyDomainCode +
                ", policyMeasureCode=" + policyMeasureCode +
                ", commodityClassCode=" + commodityClassCode +
                '}';
    }

    public int getQuery() {
        int result = 0;


        // only Country
        if ((this.getCommodityClassCode() == 0) && (this.getPolicyDomainCode() == 0)
                && (this.getPolicyMeasureCode() == 0) && (this.getCountryCode() != 0))
            result = 7;

        // only CommodityClass Code
        if ((this.getCommodityClassCode() != 0) && (this.getPolicyDomainCode() == 0)
                && (this.getPolicyMeasureCode() == 0) && (this.getCountryCode() == 0))
            result = 6;

        // everything is false
        if ((this.getCommodityClassCode() == 0) && (this.getPolicyDomainCode() == 0)
                && (this.getPolicyMeasureCode() == 0) && (this.getCountryCode() == 0))
            result = 5;

        // nothing is false
        if ((this.getCommodityClassCode() != 0) && (this.getPolicyDomainCode() != 0)
                && (this.getPolicyMeasureCode() != 0) && (this.getCountryCode() != 0))
            result = 4;

            // Measure_Code is 0
        else if ((this.getCommodityClassCode() != 0) && (this.getPolicyDomainCode() != 0)
                && (this.getPolicyMeasureCode() == 0) && (this.getCountryCode() != 0))
            result = 3;

            // domain Code is false
        else if ((this.getCommodityClassCode() != 0) && (this.getPolicyDomainCode() == 0)
                && (this.getPolicyMeasureCode() != 0) && (this.getCountryCode() != 0))
            result = 2;

            // domain Code AND measure Code are 0
        else if ((this.getCommodityClassCode() != 0) && (this.getPolicyDomainCode() == 0)
                && (this.getPolicyMeasureCode() == 0) && (this.getCountryCode() != 0))
            result = 1;

        return result;

    }
}

  