package org.fao.amis.policy.rest.spi;

import org.fao.amis.policy.dto.full.SharedGroup;
import org.fao.amis.policy.dto.search.SharedGroupSearch;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface SharedGroupSpi {

    @GET
    List<SharedGroup> getSharedGroups() throws Exception;

    @GET
    @Path("{id}")
    SharedGroup getSharedGroup(@PathParam("id") String id) throws Exception;

    @POST
    SharedGroup postSharedGroup(SharedGroup group) throws Exception;

    @PUT
    SharedGroup putSharedGroup(SharedGroup group) throws Exception;

    @DELETE
    @Path("{id}")
    SharedGroup deleteSharedGroup(@PathParam("id") String id) throws Exception;

    @POST
    @Path("/search")
    List<SharedGroup> getSharedGroups(SharedGroupSearch searchSharedGroupBean) throws Exception;

}