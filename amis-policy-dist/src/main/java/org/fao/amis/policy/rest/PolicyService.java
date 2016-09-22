package org.fao.amis.policy.rest;

import org.fao.amis.policy.dao.impl.PolicyDb;
import org.fao.amis.policy.dto.full.Policy;
import org.fao.amis.policy.dto.search.PolicySearch;
import org.fao.amis.policy.rest.spi.PolicySpi;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

@Path("policies")
public class PolicyService implements PolicySpi {

    @Inject
    PolicyDb dao;

    public List<Policy> getPolicies() throws Exception {
        return dao.retrieveAll();
    }

    public Policy getPolicy(String id) throws Exception {
        return dao.retrieve(Integer.parseInt(id));
    }

    public Policy postPolicy(Policy policy) throws Exception {

        return dao.insert(policy);
    }

    public Policy putPolicy(Policy policy) throws Exception {

        return dao.updateRecordToTable(policy);
    }

    public Policy deletePolicy(String id) throws Exception {

        return dao.delete(Integer.parseInt(id));
    }

    public List<Policy> searchPolicies(PolicySearch searchPolicyBean) {
        return dao.search(searchPolicyBean);

    }

}