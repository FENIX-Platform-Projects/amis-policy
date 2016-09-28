package org.fao.amis.policy.rest;

import org.fao.amis.policy.dao.impl.CplDao;
import org.fao.amis.policy.dto.full.Cpl;
import org.fao.amis.policy.dto.search.CplSearch;
import org.fao.amis.policy.rest.spi.CplSpi;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

@Path("cpls")
public class CplService implements CplSpi {

    @Inject
    private CplDao dao;

    @Override
    public List<Cpl> getCpls() throws Exception {
        return dao.retrieveAll();
    }

    @Override
    public Cpl getCpl(String id) throws Exception {
        return dao.retrieve(Integer.parseInt(id));
    }

    @Override
    public Cpl postCPL(Cpl cpl) throws Exception {
        return dao.insert(cpl);
    }

    @Override
    public Cpl putSharedGroup(Cpl cpl) throws Exception {
        return dao.updateRecordToTable(cpl);
    }

    @Override
    public Cpl deleteSharedGroup(String id) throws Exception {
        return dao.delete(Integer.parseInt(id));
    }

    @Override
    public List<Cpl> searchCpl(CplSearch sb) {
        return dao.search(sb);
    }

}