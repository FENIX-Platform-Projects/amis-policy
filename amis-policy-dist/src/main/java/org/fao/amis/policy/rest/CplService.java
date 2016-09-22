package org.fao.amis.policy.rest;

import org.fao.amis.policy.dao.impl.CplDb;
import org.fao.amis.policy.dto.full.Cpl;
import org.fao.amis.policy.dto.search.CplSearch;
import org.fao.amis.policy.rest.spi.CplSpi;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

@Path("cpls")
public class CplService implements CplSpi {

    @Inject
    private CplDb dao;

    public List<Cpl> getCpls() throws Exception {
        return dao.retrieveAll();
    }

    public Cpl getCpl(String id) throws Exception {
        return dao.retrieve(Integer.parseInt(id));
    }

    public Cpl postCPL(Cpl cpl) throws Exception {
        return dao.insert(cpl);
    }

    @PUT
    public Cpl putSharedGroup(Cpl cpl) throws Exception {
        return dao.updateRecordToTable(cpl);
    }

    public Cpl deleteSharedGroup(String id) throws Exception {
        return dao.delete(Integer.parseInt(id));
    }

    public List<Cpl> searchCpl(CplSearch sb) {
        return dao.search(sb);
    }

}