package org.fao.amis.policy.rest;

import org.fao.amis.policy.dao.impl.SharedGroupDb;
import org.fao.amis.policy.dto.full.SharedGroup;
import org.fao.amis.policy.dto.search.SharedGroupSearch;
import org.fao.amis.policy.rest.spi.SharedGroupSpi;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

@Path("sharedgroups")
public class SharedGroupService implements SharedGroupSpi {

    @Inject
    SharedGroupDb dao;

    public List<SharedGroup> getSharedGroups() throws Exception {
        return dao.retrieveAll();
    }

    public SharedGroup getSharedGroup(String id) throws Exception {
        return dao.retrieve(Integer.parseInt(id));
    }

    public SharedGroup postSharedGroup(SharedGroup group) throws Exception {
        return dao.insert(group);
    }

    public SharedGroup putSharedGroup(SharedGroup group) throws Exception {
        return dao.updateRecordToTable(group);
    }

    public SharedGroup deleteSharedGroup(String id) throws Exception {
        return dao.delete(Integer.parseInt(id));
    }

    public List<SharedGroup> getSharedGroups(SharedGroupSearch searchSharedGroupBean) {
        return dao.search(searchSharedGroupBean);
    }

}