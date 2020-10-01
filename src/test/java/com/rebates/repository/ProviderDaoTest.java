package com.rebates.repository;

import com.rebates.dao.ProviderDao;
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
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppInitializer.class)

public class ProviderDaoTest {
    private Logger logger = LoggerFactory.getLogger(ProviderDaoTest.class);
@Autowired
    private ProviderDao providerDao;

    private Provider testProvider;
    private String providerName;
    private Rebate rebate1;
    private Rebate rebate2;
    private Transaction transaction1;
    private Transaction transaction2;
//    private LocalDateTime transactionDateTime = new LocalDateTime(2020-05-01,12/30/00);

    public ProviderDaoTest() {
    }

    @BeforeClass
    public static void setupOnce(){
//        providerDao = new ProviderDaoImpl();
        }

    @Before
    public void setUp() {
//        providerDao =new ProviderDaoImpl();
        providerName = "Test-Ulta";
        testProvider = new Provider();
        testProvider.setName(providerName);

//        rebate1 = new Rebate();
//        rebate1.setName("Ibotta");
//        rebate1.setLink("www.ibotta.com");
//        rebate1.setRebateType("Cashback");
//        rebate1.setValue(BigDecimal.valueOf(0.04));
//
//        rebate2 = new Rebate();
//        rebate2.setName("Topcashback");
//        rebate2.setLink("www.topcashback.com");
//        rebate2.setRebateType("Cashback");
//        rebate2.setValue(BigDecimal.valueOf(0.08));
//
//        transaction1 = new Transaction();
//        transaction1.setOrderId("SF897655");
//        transaction1.setAmount(BigDecimal.valueOf(101));
////        transaction1.setPurchaseTime(transactionDateTime);
//
////        rebate1.getTransactions().add(transaction1);
////        transaction1.setRebate(rebate1);
//        rebate1.addTransaction(transaction1);
//
////        testProvider.getRebates().add(rebate1);
////        rebate1.setProvider(testProvider);
//        testProvider.addRebate(rebate1);
//
////        testProvider.getRebates().add(rebate2);
////        rebate2.setProvider(testProvider);
//        testProvider.addRebate(rebate2);
//
//        transaction2 = new Transaction();
//        transaction2.setOrderId("ID7886553");
//        transaction2.setAmount(BigDecimal.valueOf(40.66));
//
////        rebate2.getTransactions().add(transaction2);
////        transaction2.setRebate(rebate2);
//        rebate2.addTransaction(transaction2);
//
//

        testProvider= providerDao.save(testProvider);





    }

    @After
    public void tearDown() {
//        providerDao = null;
        providerDao.delete(testProvider);
    }

    @Test
    public void saveProviderHibernateTest() {
        Provider provider = getProviderForTest("Nina");
//        ProviderDao providerDao = new ProviderDaoImpl();
        Provider providerSaved = providerDao.save(provider);
        assertNotNull("A saved provider should have a ID with NULL value",providerSaved.getId());
        assertEquals("The name value should be the same.",provider.getName(),providerSaved.getName());
        logger.info("Provider = {}",providerSaved);
        providerDao.delete(provider);
    }

    private Provider getProviderForTest(String name) {
        Provider provider = new Provider();
        provider.setName(name);
        return provider;
    }

    @Test
    public void getProvidersTest(){
        List<Provider> providerList = providerDao.getProviders();
        assertEquals("Provider should be have 4 records ",4,providerList.size());


    }
    @Test
    public void listProviderTest(){
        List<Provider> providers = providerDao.getProviders();
        logger.info(providers.toString());

    }

    @Test
    public void getProviderByIdTest(){
        Provider retrieveProvider = providerDao.getProviderById(testProvider.getId());
        assertEquals("id should be the same",retrieveProvider.getId(),testProvider.getId());
        assertEquals("name should be the same",retrieveProvider.getName(),testProvider.getName());
    }



}
