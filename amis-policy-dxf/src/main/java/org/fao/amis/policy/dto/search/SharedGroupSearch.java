package org.fao.amis.policy.dto.search;

public class SharedGroupSearch {

    private int commodityId;

    public SharedGroupSearch() {
    }

    public SharedGroupSearch(int commodityId) {
        this.commodityId = commodityId;
    }

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SharedGroupSearch that = (SharedGroupSearch) o;

        if (commodityId != that.commodityId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return commodityId;
    }

    @Override
    public String toString() {
        return "SearchSharedGroupBean{" +
                "commodityId=" + commodityId +
                '}';
    }
}
