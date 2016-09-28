package org.fao.amis.policy.rest;

import org.fao.amis.policy.dao.impl.SelectorsDao;
import org.fao.amis.policy.rest.spi.SelectorsSpi;
import org.fao.amis.policy.services.SelectorsLogic;
import org.fao.fenix.commons.msd.dto.templates.ResponseBeanFactory;
import org.fao.fenix.commons.msd.dto.templates.codeList.Code;
import org.fao.fenix.commons.msd.dto.templates.export.metadata.Period;
import org.fao.fenix.commons.msd.dto.type.DataType;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.Collection;

@Path("selectors")
public class SelectorsService implements SelectorsSpi {

    @Inject SelectorsDao dao;
    @Inject SelectorsLogic selectorsLogic;

    @Override
    public Period getPolicyPeriod() throws Exception {
        return ResponseBeanFactory.getInstance(Period.class, selectorsLogic.getPolicyTimePeriod(DataType.month));
    }

    @Override
    public Collection<Code> getPolicyTypes(String commodityDomain) throws Exception {
        return ResponseBeanFactory.getInstances(Code.class, selectorsLogic.getPolicyTypes(commodityDomain));
    }

    @Override
    public Collection<Code> getPolicyMeasures(String commodityDomain, String policyDomain, String policyType) throws Exception {
        return ResponseBeanFactory.getInstances(Code.class, selectorsLogic.getPolicyMeasures(commodityDomain,policyDomain,policyType));
    }

    @Override
    public Collection<Code> getCountries(String policyMeasure, String policyType) throws Exception {
        return ResponseBeanFactory.getInstances(Code.class, selectorsLogic.getCountries(policyMeasure,policyType));
    }

    @Override
    public Collection<Code> getCommodityClasses(String commodityDomain) throws Exception {
        return ResponseBeanFactory.getInstances(Code.class, selectorsLogic.getCommodityClasses(commodityDomain));
    }




}