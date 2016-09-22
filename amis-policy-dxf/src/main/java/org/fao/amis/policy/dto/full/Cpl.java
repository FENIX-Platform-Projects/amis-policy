package org.fao.amis.policy.dto.full;

public class Cpl {


    private int cplId;
    private String cplCode;
    private int commodityId;
    private int countryCode;
    private String countryName;
    private int subnationalCode;
    private String subnationalName;
    private int commodityDomainCode;
    private String commodityDomainName;
    private int commodityClassCode;
    private String commodityClassName;
    private int policyDomainCode;
    private String policyDomainName;
    private int policyTypeCode;
    private String policyTypeName;
    private int policyMeasureCode;
    private String policyMeasureName;
    private int conditionCode;
    private String condition;
    private int individualPolicyCode;
    private String individualPolicyName;

    public Cpl(int cplId, String cplCode, int commodityId, int countryCode, String countryName, int subnationalCode, String subnationalName, int commodityDomainCode, String commodityDomainName, int commodityClassCode, String commodityClassName, int policyDomainCode, String policyDomainName, int policyTypeCode, String policyTypeName, int policyMeasureCode, String policyMeasureName, int conditionCode, String condition, int individualPolicyCode, String individualPolicyName) {

        this.cplId = cplId;
        this.cplCode = cplCode;
        this.commodityId = commodityId;
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.subnationalCode = subnationalCode;
        this.subnationalName = subnationalName;
        this.commodityDomainCode = commodityDomainCode;
        this.commodityDomainName = commodityDomainName;
        this.commodityClassCode = commodityClassCode;
        this.commodityClassName = commodityClassName;
        this.policyDomainCode = policyDomainCode;
        this.policyDomainName = policyDomainName;
        this.policyTypeCode = policyTypeCode;
        this.policyTypeName = policyTypeName;
        this.policyMeasureCode = policyMeasureCode;
        this.policyMeasureName = policyMeasureName;
        this.conditionCode = conditionCode;
        this.condition = condition;
        this.individualPolicyCode = individualPolicyCode;
        this.individualPolicyName = individualPolicyName;
    }

    // Without cpl_id
    public Cpl(String cplCode, int commodityId, int countryCode, String countryName, int subnationalCode, String subnationalName, int commodityDomainCode, String commodityDomainName, int commodityClassCode, String commodityClassName, int policyDomainCode, String policyDomainName, int policyTypeCode, String policyTypeName, int policyMeasureCode, String policyMeasureName, int conditionCode, String condition, int individualPolicyCode, String individualPolicyName) {

        this.cplCode = cplCode;
        this.commodityId = commodityId;
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.subnationalCode = subnationalCode;
        this.subnationalName = subnationalName;
        this.commodityDomainCode = commodityDomainCode;
        this.commodityDomainName = commodityDomainName;
        this.commodityClassCode = commodityClassCode;
        this.commodityClassName = commodityClassName;
        this.policyDomainCode = policyDomainCode;
        this.policyDomainName = policyDomainName;
        this.policyTypeCode = policyTypeCode;
        this.policyTypeName = policyTypeName;
        this.policyMeasureCode = policyMeasureCode;
        this.policyMeasureName = policyMeasureName;
        this.conditionCode = conditionCode;
        this.condition = condition;
        this.individualPolicyCode = individualPolicyCode;
        this.individualPolicyName = individualPolicyName;
    }


    public int getPolicyMeasureCode() {
        return policyMeasureCode;
    }

    public void setPolicyMeasureCode(int policyMeasureCode) {
        this.policyMeasureCode = policyMeasureCode;
    }

    public int getCplId() {
        return cplId;
    }

    public void setCplId(int cplId) {
        this.cplId = cplId;
    }

    public String getCplCode() {
        return cplCode;
    }

    public void setCplCode(String cplCode) {
        this.cplCode = cplCode;
    }

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public int getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(int countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getSubnationalCode() {
        return subnationalCode;
    }

    public void setSubnationalCode(int subnationalCode) {
        this.subnationalCode = subnationalCode;
    }

    public String getSubnationalName() {
        return subnationalName;
    }

    public void setSubnationalName(String subnationalName) {
        this.subnationalName = subnationalName;
    }

    public int getCommodityDomainCode() {
        return commodityDomainCode;
    }

    public void setCommodityDomainCode(int commodityDomainCode) {
        this.commodityDomainCode = commodityDomainCode;
    }

    public String getCommodityDomainName() {
        return commodityDomainName;
    }

    public void setCommodityDomainName(String commodityDomainName) {
        this.commodityDomainName = commodityDomainName;
    }

    public int getCommodityClassCode() {
        return commodityClassCode;
    }

    public void setCommodityClassCode(int commodityClassCode) {
        this.commodityClassCode = commodityClassCode;
    }

    public String getCommodityClassName() {
        return commodityClassName;
    }

    public void setCommodityClassName(String commodityClassName) {
        this.commodityClassName = commodityClassName;
    }

    public int getPolicyDomainCode() {
        return policyDomainCode;
    }

    public void setPolicyDomainCode(int policyDomainCode) {
        this.policyDomainCode = policyDomainCode;
    }

    public String getPolicyDomainName() {
        return policyDomainName;
    }

    public void setPolicyDomainName(String policyDomainName) {
        this.policyDomainName = policyDomainName;
    }

    public int getPolicyTypeCode() {
        return policyTypeCode;
    }

    public void setPolicyTypeCode(int policyTypeCode) {
        this.policyTypeCode = policyTypeCode;
    }

    public String getPolicyTypeName() {
        return policyTypeName;
    }

    public void setPolicyTypeName(String policyTypeName) {
        this.policyTypeName = policyTypeName;
    }

    public String getPolicyMeasureName() {
        return policyMeasureName;
    }

    public void setPolicyMeasureName(String policyMeasureName) {
        this.policyMeasureName = policyMeasureName;
    }

    public int getConditionCode() {
        return conditionCode;
    }

    public void setConditionCode(int conditionCode) {
        this.conditionCode = conditionCode;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public int getIndividualPolicyCode() {
        return individualPolicyCode;
    }

    public void setIndividualPolicyCode(int individualPolicyCode) {
        this.individualPolicyCode = individualPolicyCode;
    }

    public String getIndividualPolicyName() {
        return individualPolicyName;
    }

    public void setIndividualPolicyName(String individualPolicyName) {
        this.individualPolicyName = individualPolicyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cpl cpl = (Cpl) o;

        if (commodityClassCode != cpl.commodityClassCode) return false;
        if (commodityDomainCode != cpl.commodityDomainCode) return false;
        if (commodityId != cpl.commodityId) return false;
        if (countryCode != cpl.countryCode) return false;
        if (individualPolicyCode != cpl.individualPolicyCode) return false;
        if (policyDomainCode != cpl.policyDomainCode) return false;
        if (policyMeasureCode != cpl.policyMeasureCode) return false;
        if (policyTypeCode != cpl.policyTypeCode) return false;
        if (subnationalCode != cpl.subnationalCode) return false;
        if (!commodityClassName.equals(cpl.commodityClassName)) return false;
        if (!commodityDomainName.equals(cpl.commodityDomainName)) return false;
        if (!countryName.equals(cpl.countryName)) return false;
        if (individualPolicyName != null ? !individualPolicyName.equals(cpl.individualPolicyName) : cpl.individualPolicyName != null)
            return false;
        if (!policyDomainName.equals(cpl.policyDomainName)) return false;
        if (!policyMeasureName.equals(cpl.policyMeasureName)) return false;
        if (!policyTypeName.equals(cpl.policyTypeName)) return false;
        if (!subnationalName.equals(cpl.subnationalName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = commodityId;
        result = 31 * result + countryCode;
        result = 31 * result + countryName.hashCode();
        result = 31 * result + subnationalCode;
        result = 31 * result + subnationalName.hashCode();
        result = 31 * result + commodityDomainCode;
        result = 31 * result + commodityDomainName.hashCode();
        result = 31 * result + commodityClassCode;
        result = 31 * result + commodityClassName.hashCode();
        result = 31 * result + policyDomainCode;
        result = 31 * result + policyDomainName.hashCode();
        result = 31 * result + policyTypeCode;
        result = 31 * result + policyTypeName.hashCode();
        result = 31 * result + policyMeasureCode;
        result = 31 * result + policyMeasureName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Cpl{" +
                "cplId=" + cplId +
                ", cplCode='" + cplCode + '\'' +
                ", commodityId=" + commodityId +
                ", countryCode=" + countryCode +
                ", countryName='" + countryName + '\'' +
                ", subnationalCode=" + subnationalCode +
                ", subnationalName='" + subnationalName + '\'' +
                ", commodityDomainCode=" + commodityDomainCode +
                ", commodityDomainName='" + commodityDomainName + '\'' +
                ", commodityClassCode=" + commodityClassCode +
                ", commodityClassName='" + commodityClassName + '\'' +
                ", policyDomainCode=" + policyDomainCode +
                ", policyDomainName='" + policyDomainName + '\'' +
                ", policyTypeCode=" + policyTypeCode +
                ", policyTypeName='" + policyTypeName + '\'' +
                ", policyMeasureCode=" + policyMeasureCode +
                ", policyMeasureName='" + policyMeasureName + '\'' +
                ", conditionCode=" + conditionCode +
                ", condition='" + condition + '\'' +
                ", individualPolicyCode=" + individualPolicyCode +
                ", individualPolicyName='" + individualPolicyName + '\'' +
                '}';
    }
}
