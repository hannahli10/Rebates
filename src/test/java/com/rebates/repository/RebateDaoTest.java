package com.rebates.repository;

import com.rebates.dao.RebateDao;
import com.rebates.model.Provider;
import com.rebates.model.Rebate;
import com.rebates.model.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RebateDaoTest {
    private Logger logger = LoggerFactory.getLogger(RebateDaoImpl.class);
    private static RebateDao rebateDao;

    private Rebate testRebate;
    private String rebateName;
    private String rebateLink;
    private String rebateType;
    private BigDecimal rebateValue;

    private Rebate rebate1;
    private Rebate rebate2;
    private Transaction transaction1;
    private Transaction transaction2;
    @BeforeClass
    public static void setupOnce(){
        rebateDao = new RebateDaoImpl();
    }

    @Before
    public void setUp() {
//        providerDao =new ProviderDaoImpl();
        rebateName = "Topcashback";
        rebateLink = "www.topcashback";
        rebateType = "Cashback";
        rebateValue = BigDecimal.valueOf(0.1);

        testRebate = new Rebate();
        testRebate.setName(rebateName);
        testRebate.setLink(rebateLink);
        testRebate.setRebateType(rebateType);
        testRebate.setValue(rebateValue);

        testRebate = rebateDao.save(testRebate);
    }

    @After
    public void tearDown() {
        rebateDao.delete(testRebate);
    }

    @Test
    public void saveRebateHibernateTest() {
        Rebate rebate = getRebateForTest("Mr Rebate","www.mrrebate.com","cashback",rebateValue);
//        ProviderDao providerDao = new ProviderDaoImpl();
        Rebate rebateSaved = rebateDao.save(rebate);
        assertNotNull("A saved rebate should have a ID with NULL value",rebateSaved.getId());
        assertEquals("The name value should be the same.",rebate.getName(),rebateSaved.getName());
        logger.info("Provider = {}",rebateSaved);
        rebateDao.delete(rebate);
    }

    private Rebate getRebateForTest(String name,String link,String rebateType,BigDecimal rebateValue) {
        Rebate rebate = new Rebate();
        rebate.setName(name);
        rebate.setLink(link);
        rebate.setRebateType(rebateType);
        rebate.setValue(rebateValue);
        return rebate;
    }

    @Test
    public void getRebetesTest(){
        List<Rebate> rebatesList = rebateDao.getRebates();
        assertEquals("Rebate should be have 4 records ",4,rebatesList.size());


    }
    @Test
    public void listRebateTest(){
        List<Rebate> rebates = rebateDao.getRebates();
        logger.info(rebates.toString());

    }

    @Test
    public void getRebateByIdTest(){
        Rebate retrieveRebate = rebateDao.getRebateById(testRebate.getId());
        assertEquals("id should be the same",retrieveRebate .getId(),testRebate.getId());
        assertEquals("name should be the same",retrieveRebate .getName(),testRebate.getName());
    }


}
