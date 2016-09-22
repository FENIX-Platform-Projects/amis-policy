package org.fao.amis.policy.rest.spi;

import org.fao.amis.policy.dto.full.Cpl;
import org.fao.amis.policy.dto.search.CplSearch;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface CplSpi {


    @GET
    List<Cpl> getCpls() throws Exception;

    @GET
    @Path("{id}")
    Cpl getCpl(@PathParam("id") String id) throws Exception;

    @POST
    Cpl postCPL(Cpl cpl) throws Exception;

    @PUT
    Cpl putSharedGroup(Cpl cpl) throws Exception;

    @DELETE
    @Path("{id}")
    Cpl deleteSharedGroup(@PathParam("id") String id) throws Exception;

    @POST
    @Path("/search")
    List<Cpl> searchCpl(CplSearch sb) throws Exception;

}