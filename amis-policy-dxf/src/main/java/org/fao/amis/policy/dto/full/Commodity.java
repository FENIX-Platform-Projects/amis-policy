package org.fao.amis.policy.dto.full;

public class Commodity {

    private int commodityId;
    private String countryName;
    private String hsCode;
    private String hsSuffix;
    private String hsVersion;
    private String targetCode;
    private String description;
    private String shortDescription;
    private String commodityClassName;
    private int commodityClassCode;
    private String sharedGroupCode;

    public Commodity(int commodityId, String countryName, String hsCode, String hsSuffix, String hsVersion, String targetCode, String description, String shortDescription, String commodityClassName, int commodityClassCode, String sharedGroupCode) {
        this.commodityId = commodityId;
        this.countryName = countryName;
        this.hsCode = hsCode;
        this.hsSuffix = hsSuffix;
        this.hsVersion = hsVersion;
        this.targetCode = targetCode;
        this.description = description;
        this.shortDescription = shortDescription;
        this.commodityClassName = commodityClassName;
        this.commodityClassCode = commodityClassCode;
        this.sharedGroupCode = sharedGroupCode;
    }

    // Without ID
    public Commodity(String countryName, String hsCode, String hsSuffix, String hsVersion, String targetCode, String description, String shortDescription, String commodityClassName, int commodityClassCode, String sharedGroupCode) {
        this.countryName = countryName;
        this.hsCode = hsCode;
        this.hsSuffix = hsSuffix;
        this.hsVersion = hsVersion;
        this.targetCode = targetCode;
        this.description = description;
        this.shortDescription = shortDescription;
        this.commodityClassName = commodityClassName;
        this.commodityClassCode = commodityClassCode;
        this.sharedGroupCode = sharedGroupCode;
    }

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getHsCode() {
        return hsCode;
    }

    public void setHsCode(String hsCode) {
        this.hsCode = hsCode;
    }

    public String getHsSuffix() {
        return hsSuffix;
    }

    public void setHsSuffix(String hsSuffix) {
        this.hsSuffix = hsSuffix;
    }

    public String getHsVersion() {
        return hsVersion;
    }

    public void setHsVersion(String hsVersion) {
        this.hsVersion = hsVersion;
    }

    public String getTargetCode() {
        return targetCode;
    }

    public void setTargetCode(String targetCode) {
        this.targetCode = targetCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getCommodityClassName() {
        return commodityClassName;
    }

    public void setCommodityClassName(String commodityClassName) {
        this.commodityClassName = commodityClassName;
    }

    public int getCommodityClassCode() {
        return commodityClassCode;
    }

    public void setCommodityClassCode(int commodityClassCode) {
        this.commodityClassCode = commodityClassCode;
    }

    public String getSharedGroupCode() {
        return sharedGroupCode;
    }

    public void setSharedGroupCode(String sharedGroupCode) {
        this.sharedGroupCode = sharedGroupCode;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Commodity commodity = (Commodity) o;

        if (commodityClassCode != commodity.commodityClassCode) return false;
        if (!hsCode.equals(commodity.hsCode)) return false;
        if (!commodityClassName.equals(commodity.commodityClassName)) return false;
        if (!countryName.equals(commodity.countryName)) return false;
        if (description != null ? !description.equals(commodity.description) : commodity.description != null)
            return false;
        if (hsSuffix != null ? !hsSuffix.equals(commodity.hsSuffix) : commodity.hsSuffix != null) return false;
        if (hsVersion != null ? !hsVersion.equals(commodity.hsVersion) : commodity.hsVersion != null) return false;
        if (shortDescription != null ? !shortDescription.equals(commodity.shortDescription) : commodity.shortDescription != null)
            return false;
        if (targetCode != null ? !targetCode.equals(commodity.targetCode) : commodity.targetCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = countryName.hashCode();
        result = 31 * result + (hsCode != null ? hsCode.hashCode() : 0);
        result = 31 * result + (hsSuffix != null ? hsSuffix.hashCode() : 0);
        result = 31 * result + (hsVersion != null ? hsVersion.hashCode() : 0);
        result = 31 * result + (targetCode != null ? targetCode.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (shortDescription != null ? shortDescription.hashCode() : 0);
        result = 31 * result + commodityClassName.hashCode();
        result = 31 * result + commodityClassCode;
        return result;
    }

    @Override
    public String toString() {
        return "CommoditySpi{" +
                "commodityId=" + commodityId +
                ", countryName='" + countryName + '\'' +
                ", hsCode=" + hsCode +
                ", hsSuffix='" + hsSuffix + '\'' +
                ", hsVersion='" + hsVersion + '\'' +
                ", targetCode='" + targetCode + '\'' +
                ", description='" + description + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", commodityClassName='" + commodityClassName + '\'' +
                ", commodityClassCode=" + commodityClassCode +
                ", sharedGroupCode='" + sharedGroupCode + '\'' +
                '}';
    }
}
