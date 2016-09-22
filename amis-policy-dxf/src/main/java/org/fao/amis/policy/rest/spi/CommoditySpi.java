package org.fao.amis.policy.rest.spi;

import org.fao.amis.policy.dto.full.Commodity;
import org.fao.amis.policy.dto.search.CommoditySearch;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface CommoditySpi {

    @GET
    List<Commodity> getCommodities() throws Exception;

    @GET
    @Path("{id}")
    Commodity getCommodity(@PathParam("id") String id) throws Exception;

    @POST
    Commodity postCommodity(Commodity commodity) throws Exception;

    @PUT
    Commodity putCommodity(Commodity commodity) throws Exception;

    @DELETE
    @Path("{id}")
    Commodity deleteCommodity(@PathParam("id") String id) throws Exception;

    @POST
    @Path("/search")
    List<Commodity> search(CommoditySearch searchCommodityBean) throws Exception;

}