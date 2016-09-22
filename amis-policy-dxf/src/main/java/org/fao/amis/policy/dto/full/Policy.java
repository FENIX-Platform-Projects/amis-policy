package org.fao.amis.policy.dto.full;

import java.util.Date;

public class Policy {

    private int metadataId;
    private int policyId;
    private int cplId;
    private double commodityId;
    private String policyElement;
    private Date startDate;
    private Date endDate;
    private String units;
    private String value;
    private String valueText;
    private String valueType;
    private String exemptions;
    private String locationCondition;
    private String notes;
    private String link;
    private String source;
    private String titleOfNotice;
    private String legalBasisName;
    private Date dateOfPublication;
    private String imposedEndDate;
    private String secondGenerationSpecific;
    private String benchmarkTax;
    private String benchmarkProduct;
    private String taxRateBiofuel;
    private String taxRateBenchmark;
    private String startDateTax;
    private String benchmarkLink;
    private String originalDataset;
    private double typeOfChangeCode;
    private String typeOfChangeName;
    private String measureDescr;
    private String productOriginalHs;
    private String productOriginalName;
    private String implementationProcedure;
    private String xsYeartype;
    private String linkPdf;
    private String benchmarkLinkPdf;

    public Policy(int metadataId, int policyId, int cplId, double commodityId, String policyElement, Date startDate, Date endDate, String units, String value, String valueText, String valueType, String exemptions, String locationCondition, String notes, String link, String source, String titleOfNotice, String legalBasisName, Date dateOfPublication, String imposedEndDate, String secondGenerationSpecific, String benchmarkTax, String benchmarkProduct, String taxRateBiofuel, String taxRateBenchmark, String startDateTax, String benchmarkLink, double typeOfChangeCode, String typeOfChangeName, String measureDescr, String productOriginalHs, String productOriginalName, String implementationProcedure, String xsYeartype, String linkPdf, String benchmarkLinkPdf) {
        this.metadataId = metadataId;
        this.policyId = policyId;
        this.cplId = cplId;
        this.commodityId = commodityId;
        this.policyElement = policyElement;
        this.startDate = startDate;
        this.endDate = endDate;
        this.units = units;
        this.value = value;
        this.valueText = valueText;
        this.valueType = valueType;
        this.exemptions = exemptions;
        this.locationCondition = locationCondition;
        this.notes = notes;
        this.link = link;
        this.source = source;
        this.titleOfNotice = titleOfNotice;
        this.legalBasisName = legalBasisName;
        this.dateOfPublication = dateOfPublication;
        this.imposedEndDate = imposedEndDate;
        this.secondGenerationSpecific = secondGenerationSpecific;
        this.benchmarkTax = benchmarkTax;
        this.benchmarkProduct = benchmarkProduct;
        this.taxRateBiofuel = taxRateBiofuel;
        this.taxRateBenchmark = taxRateBenchmark;
        this.startDateTax = startDateTax;
        this.benchmarkLink = benchmarkLink;
        this.originalDataset = originalDataset;
        this.typeOfChangeCode = typeOfChangeCode;
        this.typeOfChangeName = typeOfChangeName;
        this.measureDescr = measureDescr;
        this.productOriginalHs = productOriginalHs;
        this.productOriginalName = productOriginalName;
        this.implementationProcedure = implementationProcedure;
        this.xsYeartype = xsYeartype;
        this.linkPdf = linkPdf;
        this.benchmarkLinkPdf = benchmarkLinkPdf;
    }

    // Without Policy_ID
    public Policy(int metadataId, int cplId, double commodityId, String policyElement, Date startDate, Date endDate, String units, String value, String valueText, String valueType, String exemptions, String locationCondition, String notes, String link, String source, String titleOfNotice, String legalBasisName, Date dateOfPublication, String imposedEndDate, String secondGenerationSpecific, String benchmarkTax, String benchmarkProduct, String taxRateBiofuel, String taxRateBenchmark, String startDateTax, String benchmarkLink, double typeOfChangeCode, String typeOfChangeName, String measureDescr, String productOriginalHs, String productOriginalName, String implementationProcedure, String xsYeartype, String linkPdf, String benchmarkLinkPdf) {
        this.metadataId = metadataId;
        this.cplId = cplId;
        this.commodityId = commodityId;
        this.policyElement = policyElement;
        this.startDate = startDate;
        this.endDate = endDate;
        this.units = units;
        this.value = value;
        this.valueText = valueText;
        this.valueType = valueType;
        this.exemptions = exemptions;
        this.locationCondition = locationCondition;
        this.notes = notes;
        this.link = link;
        this.source = source;
        this.titleOfNotice = titleOfNotice;
        this.legalBasisName = legalBasisName;
        this.dateOfPublication = dateOfPublication;
        this.imposedEndDate = imposedEndDate;
        this.secondGenerationSpecific = secondGenerationSpecific;
        this.benchmarkTax = benchmarkTax;
        this.benchmarkProduct = benchmarkProduct;
        this.taxRateBiofuel = taxRateBiofuel;
        this.taxRateBenchmark = taxRateBenchmark;
        this.startDateTax = startDateTax;
        this.benchmarkLink = benchmarkLink;
        this.originalDataset = originalDataset;
        this.typeOfChangeCode = typeOfChangeCode;
        this.typeOfChangeName = typeOfChangeName;
        this.measureDescr = measureDescr;
        this.productOriginalHs = productOriginalHs;
        this.productOriginalName = productOriginalName;
        this.implementationProcedure = implementationProcedure;
        this.xsYeartype = xsYeartype;
        this.linkPdf = linkPdf;
        this.benchmarkLinkPdf = benchmarkLinkPdf;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getMetadataId() {
        return metadataId;
    }

    public void setMetadataId(int metadataId) {
        this.metadataId = metadataId;
    }

    public int getPolicyId() {
        return policyId;
    }

    public void setPolicyId(int policyId) {
        this.policyId = policyId;
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

    public String getPolicyElement() {
        return policyElement;
    }

    public void setPolicyElement(String policyElement) {
        this.policyElement = policyElement;
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

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValueText() {
        return valueText;
    }

    public void setValueText(String valueText) {
        this.valueText = valueText;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public String getExemptions() {
        return exemptions;
    }

    public void setExemptions(String exemptions) {
        this.exemptions = exemptions;
    }

    public String getLocationCondition() {
        return locationCondition;
    }

    public void setLocationCondition(String locationCondition) {
        this.locationCondition = locationCondition;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitleOfNotice() {
        return titleOfNotice;
    }

    public void setTitleOfNotice(String titleOfNotice) {
        this.titleOfNotice = titleOfNotice;
    }

    public String getLegalBasisName() {
        return legalBasisName;
    }

    public void setLegalBasisName(String legalBasisName) {
        this.legalBasisName = legalBasisName;
    }

    public Date getDateOfPublication() {
        return dateOfPublication;
    }

    public void setDateOfPublication(Date dateOfPublication) {
        this.dateOfPublication = dateOfPublication;
    }

    public String getImposedEndDate() {
        return imposedEndDate;
    }

    public void setImposedEndDate(String imposedEndDate) {
        this.imposedEndDate = imposedEndDate;
    }

    public String getSecondGenerationSpecific() {
        return secondGenerationSpecific;
    }

    public void setSecondGenerationSpecific(String secondGenerationSpecific) {
        this.secondGenerationSpecific = secondGenerationSpecific;
    }

    public String getBenchmarkTax() {
        return benchmarkTax;
    }

    public void setBenchmarkTax(String benchmarkTax) {
        this.benchmarkTax = benchmarkTax;
    }

    public String getBenchmarkProduct() {
        return benchmarkProduct;
    }

    public void setBenchmarkProduct(String benchmarkProduct) {
        this.benchmarkProduct = benchmarkProduct;
    }

    public String getTaxRateBiofuel() {
        return taxRateBiofuel;
    }

    public void setTaxRateBiofuel(String taxRateBiofuel) {
        this.taxRateBiofuel = taxRateBiofuel;
    }

    public String getTaxRateBenchmark() {
        return taxRateBenchmark;
    }

    public void setTaxRateBenchmark(String taxRateBenchmark) {
        this.taxRateBenchmark = taxRateBenchmark;
    }

    public String getStartDateTax() {
        return startDateTax;
    }

    public void setStartDateTax(String startDateTax) {
        this.startDateTax = startDateTax;
    }

    public String getBenchmarkLink() {
        return benchmarkLink;
    }

    public void setBenchmarkLink(String benchmarkLink) {
        this.benchmarkLink = benchmarkLink;
    }

    public String getOriginalDataset() {
        return originalDataset;
    }

    public void setOriginalDataset(String originalDataset) {
        this.originalDataset = originalDataset;
    }

    public double getTypeOfChangeCode() {
        return typeOfChangeCode;
    }

    public void setTypeOfChangeCode(double typeOfChangeCode) {
        this.typeOfChangeCode = typeOfChangeCode;
    }

    public String getTypeOfChangeName() {
        return typeOfChangeName;
    }

    public void setTypeOfChangeName(String typeOfChangeName) {
        this.typeOfChangeName = typeOfChangeName;
    }

    public String getMeasureDescr() {
        return measureDescr;
    }

    public void setMeasureDescr(String measureDescr) {
        this.measureDescr = measureDescr;
    }

    public String getProductOriginalHs() {
        return productOriginalHs;
    }

    public void setProductOriginalHs(String productOriginalHs) {
        this.productOriginalHs = productOriginalHs;
    }

    public String getProductOriginalName() {
        return productOriginalName;
    }

    public void setProductOriginalName(String productOriginalName) {
        this.productOriginalName = productOriginalName;
    }

    public String getImplementationProcedure() {
        return implementationProcedure;
    }

    public void setImplementationProcedure(String implementationProcedure) {
        this.implementationProcedure = implementationProcedure;
    }

    public String getXsYeartype() {
        return xsYeartype;
    }

    public void setXsYeartype(String xsYeartype) {
        this.xsYeartype = xsYeartype;
    }

    public String getLinkPdf() {
        return linkPdf;
    }

    public void setLinkPdf(String linkPdf) {
        this.linkPdf = linkPdf;
    }

    public String getBenchmarkLinkPdf() {
        return benchmarkLinkPdf;
    }


    public void setBenchmarkLinkPdf(String benchmarkLinkPdf) {
        this.benchmarkLinkPdf = benchmarkLinkPdf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Policy policy = (Policy) o;

        if (Double.compare(policy.commodityId, commodityId) != 0) return false;
        if (cplId != policy.cplId) return false;
        if (policyId != policy.policyId) return false;
        if (Double.compare(policy.typeOfChangeCode, typeOfChangeCode) != 0) return false;
        if (benchmarkLink != null ? !benchmarkLink.equals(policy.benchmarkLink) : policy.benchmarkLink != null)
            return false;
        if (benchmarkLinkPdf != null ? !benchmarkLinkPdf.equals(policy.benchmarkLinkPdf) : policy.benchmarkLinkPdf != null)
            return false;
        if (endDate != null ? !endDate.equals(policy.endDate) : policy.endDate != null) return false;
        if (implementationProcedure != null ? !implementationProcedure.equals(policy.implementationProcedure) : policy.implementationProcedure != null)
            return false;
        if (linkPdf != null ? !linkPdf.equals(policy.linkPdf) : policy.linkPdf != null) return false;
        if (measureDescr != null ? !measureDescr.equals(policy.measureDescr) : policy.measureDescr != null)
            return false;
        if (originalDataset != null ? !originalDataset.equals(policy.originalDataset) : policy.originalDataset != null)
            return false;
        if (!policyElement.equals(policy.policyElement)) return false;
        if (productOriginalHs != null ? !productOriginalHs.equals(policy.productOriginalHs) : policy.productOriginalHs != null)
            return false;
        if (productOriginalName != null ? !productOriginalName.equals(policy.productOriginalName) : policy.productOriginalName != null)
            return false;
        if (startDate != null ? !startDate.equals(policy.startDate) : policy.startDate != null) return false;
        if (startDateTax != null ? !startDateTax.equals(policy.startDateTax) : policy.startDateTax != null)
            return false;
        if (taxRateBenchmark != null ? !taxRateBenchmark.equals(policy.taxRateBenchmark) : policy.taxRateBenchmark != null)
            return false;
        if (typeOfChangeName != null ? !typeOfChangeName.equals(policy.typeOfChangeName) : policy.typeOfChangeName != null)
            return false;
        if (xsYeartype != null ? !xsYeartype.equals(policy.xsYeartype) : policy.xsYeartype != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = policyId;
        result = 31 * result + cplId;
        temp = Double.doubleToLongBits(commodityId);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + policyElement.hashCode();
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Policy{" +
                "metadataId=" + metadataId +
                ", policyId=" + policyId +
                ", cplId=" + cplId +
                ", commodityId=" + commodityId +
                ", policyElement='" + policyElement + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", units='" + units + '\'' +
                ", value='" + value + '\'' +
                ", valueText='" + valueText + '\'' +
                ", valueType='" + valueType + '\'' +
                ", exemptions='" + exemptions + '\'' +
                ", locationCondition='" + locationCondition + '\'' +
                ", notes='" + notes + '\'' +
                ", link='" + link + '\'' +
                ", source='" + source + '\'' +
                ", titleOfNotice='" + titleOfNotice + '\'' +
                ", legalBasisName='" + legalBasisName + '\'' +
                ", dateOfPublication=" + dateOfPublication +
                ", imposedEndDate='" + imposedEndDate + '\'' +
                ", secondGenerationSpecific='" + secondGenerationSpecific + '\'' +
                ", benchmarkTax='" + benchmarkTax + '\'' +
                ", benchmarkProduct='" + benchmarkProduct + '\'' +
                ", taxRateBiofuel='" + taxRateBiofuel + '\'' +
                ", taxRateBenchmark='" + taxRateBenchmark + '\'' +
                ", startDateTax='" + startDateTax + '\'' +
                ", benchmarkLink='" + benchmarkLink + '\'' +
                ", originalDataset='" + originalDataset + '\'' +
                ", typeOfChangeCode=" + typeOfChangeCode +
                ", typeOfChangeName='" + typeOfChangeName + '\'' +
                ", measureDescr='" + measureDescr + '\'' +
                ", productOriginalHs='" + productOriginalHs + '\'' +
                ", productOriginalName='" + productOriginalName + '\'' +
                ", implementationProcedure='" + implementationProcedure + '\'' +
                ", xsYeartype='" + xsYeartype + '\'' +
                ", linkPdf='" + linkPdf + '\'' +
                ", benchmarkLinkPdf='" + benchmarkLinkPdf + '\'' +
                '}';
    }

}
