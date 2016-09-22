package org.fao.amis.policy.rest.spi;

import org.fao.amis.policy.dto.full.Policy;
import org.fao.amis.policy.dto.search.PolicySearch;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface PolicySpi {

    @GET
    List<Policy> getPolicies() throws Exception;

    @GET
    @Path("{id}")
    Policy getPolicy(@PathParam("id") String id) throws Exception;

    @POST
    Policy postPolicy(Policy policy) throws Exception;

    @PUT
    Policy putPolicy(Policy policy) throws Exception;

    @DELETE
    @Path("{id}")
    Policy deletePolicy(@PathParam("id") String id) throws Exception;

    @POST
    @Path("/search")
    List<Policy> searchPolicies(PolicySearch searchPolicyBean) throws Exception;

}