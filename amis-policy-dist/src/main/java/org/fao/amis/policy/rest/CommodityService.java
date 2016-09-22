package org.fao.amis.policy.rest;

import org.fao.amis.policy.dao.impl.CommodityDb;
import org.fao.amis.policy.dto.full.Commodity;
import org.fao.amis.policy.dto.search.CommoditySearch;
import org.fao.amis.policy.rest.spi.CommoditySpi;

import javax.ws.rs.*;
import javax.inject.Inject;
import java.util.List;

@Path("commodities")
public class CommodityService implements CommoditySpi {

    @Inject
    private CommodityDb dao;

    public List<Commodity> getCommodities() throws Exception {
        return dao.retrieveAll();
    }

    public Commodity getCommodity(String id) throws Exception {
        return dao.retrieve(Integer.parseInt(id));
    }

    public Commodity postCommodity(Commodity commodity) throws Exception {
        return dao.insert(commodity);
    }

    public Commodity putCommodity(Commodity commodity) throws Exception {
        return dao.updateRecordToTable(commodity);
    }

    public Commodity deleteCommodity(String id) throws Exception {
        return dao.delete(Integer.parseInt(id));
    }

    public List<Commodity> search(CommoditySearch searchCommodityBean) {
        return dao.search(searchCommodityBean);
    }

}