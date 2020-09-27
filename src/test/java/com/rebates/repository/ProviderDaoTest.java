package com.rebates.repository;

import com.rebates.dao.ProviderDao;
import com.rebates.model.Provider;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ProviderDaoTest {
    private Logger logger = LoggerFactory.getLogger(ProviderDaoTest.class);

    private static ProviderDao providerDao;

    private Provider testProvider;
    private String providerName;
//    private Provider p1;
//    private Provider p2;

    @BeforeClass
    public static void setupOnce(){providerDao = new ProviderDaoImpl();}

    @Before
    public void setUp() {
//        providerDao =new ProviderDaoImpl();
        providerName = "Test-Ulta";
        testProvider = new Provider();
        testProvider.setName(providerName);
        testProvider = providerDao.save(testProvider);
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
