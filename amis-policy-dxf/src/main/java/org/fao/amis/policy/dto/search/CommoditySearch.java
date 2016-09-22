package org.fao.amis.policy.dto.search;

public class CommoditySearch {

    private int hsCode;
    private int commodityClassCode;

    public CommoditySearch() {
    }

    public CommoditySearch(int hsCode, int commodityClassCode) {
        this.hsCode = hsCode;
        this.commodityClassCode = commodityClassCode;
    }

    public int getCommodityClassCode() {
        return commodityClassCode;
    }

    public void setCommodityClassCode(int commodityClassCode) {
        this.commodityClassCode = commodityClassCode;
    }

    public int getHsCode() {
        return hsCode;
    }

    public void setHsCode(int hsCode) {
        this.hsCode = hsCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommoditySearch that = (CommoditySearch) o;

        if (commodityClassCode != that.commodityClassCode) return false;
        if (hsCode != that.hsCode) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = hsCode;
        result = 31 * result + commodityClassCode;
        return result;
    }

    @Override
    public String toString() {
        return "CommoditySpi{" +
                "hsCode=" + hsCode +
                ", commodityClassCode=" + commodityClassCode +
                '}';
    }

    public int getQuery() {
        int result = 0;

        // nothing is false
        if ((this.getCommodityClassCode() != 0) && (this.getHsCode() != 0))
            result = 3;

            // HScode is null
        else if ((this.getCommodityClassCode() != 0) && (this.getHsCode() == 0))
            result = 2;

            // commodityClassCOde is false
        else if ((this.getCommodityClassCode() == 0) && (this.getHsCode() != 0))
            result = 1;
        ;


        return result;

    }
}