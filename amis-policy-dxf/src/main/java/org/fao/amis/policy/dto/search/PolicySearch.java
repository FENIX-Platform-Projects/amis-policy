package org.fao.amis.policy.dto.search;

import java.util.Date;

public class PolicySearch {

    private Date startDate;
    private Date endDate;
    private int cplId;
    private double commodityId;
    private int commodityClassCode;

    public PolicySearch() {
    }

    public PolicySearch(Date startDate, Date endDate, int cplId, double commodityId, int commodityClassCode) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.cplId = cplId;
        this.commodityId = commodityId;
        this.commodityClassCode = commodityClassCode;

    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getCplId() {
        return cplId;
    }

    public void setCplId(int cplId) {
        this.cplId = cplId;
    }

    public double getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(double commodityId) {
        this.commodityId = commodityId;
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
        if (o == null || getClass() != o.getClass()) return false;

        PolicySearch that = (PolicySearch) o;

        if (commodityClassCode != that.commodityClassCode) return false;
        if (Double.compare(that.commodityId, commodityId) != 0) return false;
        if (cplId != that.cplId) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = -22;
        long temp;
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + cplId;
        temp = Double.doubleToLongBits(commodityId);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + commodityClassCode;
        return result;
    }

    @Override
    public String toString() {
        return "SearchPolicyBean{" +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", cplId=" + cplId +
                ", commodityId=" + commodityId +
                ", commodityClassCode=" + commodityClassCode +
                '}';
    }

    public int getQuery() {
        int result = 0;


        // date parameters with cpl_id
        if ((this.getCplId() != 0) && (this.getStartDate() != null) && (this.getEndDate() != null)
                && (this.getCommodityId() == 0) && (this.getCommodityClassCode() != 0))
            result = 6;

            // date parameters with commodity
        else if ((this.getCplId() == 0) && (this.getStartDate() != null) && (this.getEndDate() != null)
                && (this.getCommodityId() != 0) && (this.getCommodityClassCode() != 0))
            result = 5;


            // date parameters
        else if ((this.getCplId() == 0) && (this.getStartDate() != null) && (this.getEndDate() != null)
                && (this.getCommodityId() == 0) && (this.getCommodityClassCode() != 0))
            result = 4;

            // date parameters without commodity_class
        else if ((this.getCplId() == 0) && (this.getStartDate() != null) && (this.getEndDate() != null)
                && (this.getCommodityId() == 0) && (this.getCommodityClassCode() == 0))
            result = 3;


            // only commodity_id
        else if ((this.getCplId() == 0) && (this.getStartDate() == null) && (this.getEndDate() == null)
                && (this.getCommodityId() != 0) && (this.getCommodityClassCode() == 0))
            result = 2;


            // only cpl
        else if ((this.getCplId() != 0) && (this.getStartDate() == null) && (this.getEndDate() == null)
                && (this.getCommodityId() == 0) && (this.getCommodityClassCode() == 0))
            result = 1;

        return result;

    }
}