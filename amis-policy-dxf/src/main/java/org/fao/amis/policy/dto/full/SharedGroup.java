package org.fao.amis.policy.dto.full;

public class SharedGroup {

    private int commodityId;
    private int idSingle;
    private String originalHsVersion;
    private String originalHsCode;
    private String originalHsSuffix;


    public SharedGroup(int commodityId, int idSingle, String originalHsVersion, String originalHsCode,
                       String originalHsSuffix) {
        this.commodityId = commodityId;
        this.idSingle = idSingle;
        this.originalHsVersion = originalHsVersion;
        this.originalHsCode = originalHsCode;
        this.originalHsSuffix = originalHsSuffix;
    }


    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public int getIdSingle() {
        return idSingle;
    }

    public void setIdSingle(int idSingle) {
        this.idSingle = idSingle;
    }

    public String getOriginalHsVersion() {
        return originalHsVersion;
    }

    public void setOriginalHsVersion(String originalHsVersion) {
        this.originalHsVersion = originalHsVersion;
    }

    public String getOriginalHsCode() {
        return originalHsCode;
    }

    public void setOriginalHsCode(String originalHsCode) {
        this.originalHsCode = originalHsCode;
    }

    public String getOriginalHsSuffix() {
        return originalHsSuffix;
    }

    public void setOriginalHsSuffix(String originalHsSuffix) {
        this.originalHsSuffix = originalHsSuffix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SharedGroup that = (SharedGroup) o;

        if (commodityId != that.commodityId) return false;
        if (idSingle != that.idSingle) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = commodityId;
        result = 31 * result + idSingle;
        return result;
    }

    @Override
    public String toString() {
        return "SharedGroup{" +
                "commodityId=" + commodityId +
                ", idSingle=" + idSingle +
                ", originalHsVersion='" + originalHsVersion + '\'' +
                ", originalHsCode='" + originalHsCode + '\'' +
                ", originalHsSuffix='" + originalHsSuffix + '\'' +
                '}';
    }
}
