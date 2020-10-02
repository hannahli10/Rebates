package com.rebates.repository;

import com.rebates.dao.ProviderDao;
import com.rebates.dao.RebateDao;
import com.rebates.init.AppInitializer;
import com.rebates.model.Provider;
import com.rebates.model.Rebate;
import com.rebates.model.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppInitializer.class)
public class RebateDaoTest {
    private Logger logger = LoggerFactory.getLogger(RebateDaoImpl.class);
    @Autowired
    private ProviderDao providerDao;
    private RebateDao rebateDao;
    private String testRebate = "Topcashback";
    private Rebate r1;
    private Provider p1;
    private BigDecimal rebateValue = new BigDecimal("0.1");

//    private Rebate testRebate;
//    private String rebateName;
//    private String rebateLink;
//    private String rebateType;
//    private BigDecimal rebateValue;
//
//    private Rebate rebate1;
//    private Rebate rebate2;
//    private Transaction transaction1;
//    private Transaction transaction2;

    @Before
    public void setUp() {
        p1 = new Provider();
        p1.setName("Test-Ulta");
        providerDao.save(p1);

        r1 = new Rebate();
        r1.setName(testRebate);
        r1.setLink("Topcashback");
        r1.setValue(rebateValue);
        r1.setRebateType("Cashback");
        rebateDao.save(r1,p1);
//
    }

    @After
    public void tearDown() {
        rebateDao.delete(r1);
        providerDao.delete(p1);
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
        Rebate retrieveRebate = rebateDao.getRebateById(r1.getId());
        assertEquals("id should be the same",retrieveRebate .getId(),r1.getId());
        assertEquals("name should be the same",retrieveRebate .getName(),r1.getName());
    }


}
