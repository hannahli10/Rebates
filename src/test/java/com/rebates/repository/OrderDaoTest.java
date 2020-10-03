package com.rebates.repository;


import com.rebates.dao.OrderDao;
import com.rebates.dao.ProviderDao;
import com.rebates.dao.RebateDao;
import com.rebates.init.AppInitializer;
import com.rebates.model.Order;
import com.rebates.model.Provider;
import com.rebates.model.Rebate;
import org.junit.After;
import org.junit.Before;
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
public class OrderDaoTest {
    private Logger logger = LoggerFactory.getLogger(OrderDaoImpl.class);
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private RebateDao rebateDao;
    @Autowired
    private ProviderDao providerDao;

    private Order o1;
    private Rebate r1;
    private Provider p1;
    private BigDecimal rebateValue = new BigDecimal("0.1");
    private BigDecimal amount= new BigDecimal("200");

    @Before
    public void setUp() {

        p1 = new Provider();
        p1.setName("testUlta");
        providerDao.save(p1);

        r1 = new Rebate();
        r1.setProvider(p1);
        r1.setName("topcashback");
        r1.setLink("www.topcashback.com");
        r1.setValue(rebateValue);
        r1.setRebateType("Cashback");
        rebateDao.save(r1);

        o1 = new Order();
        o1.setRebate(r1);
        o1.setPurchaseTime(LocalDateTime.of(
                        2020, 5, 1,
                        12, 30, 0
                ));
        o1.setOrderNumber("SF999999");
        o1.setAmount(amount);
        orderDao.save(o1);



    }

    @After
    public void tearDown() {
        orderDao.delete(o1);
        rebateDao.delete(r1);
        providerDao.delete(p1);
    }

    @Test
    public void saveOrderHibernateTest() {
        Order order = getOrderForTest(LocalDateTime.of(2020, 10, 1, 12, 30, 0),
                "SF000001",amount);
        Order orderSaved = orderDao.save(order);
        assertNotNull("A saved order should have a ID with NULL value",orderSaved.getId());
        assertEquals("The name value should be the same.",order.getOrderNumber(),order.getOrderNumber());
        logger.info("Order = {}",orderSaved);
        orderDao.delete(order);
    }

    private Order getOrderForTest(LocalDateTime purchaseTime,String orderNumber,BigDecimal amount) {
        Order order = new Order();
        order.setPurchaseTime(purchaseTime);
        order.setOrderNumber(orderNumber);
        order.setAmount(amount);
        return order;
    }

    @Test
    public void getOrdersTest(){
        List<Order> ordersList = orderDao.getOrders();
        assertEquals("Order should be have 4 records ",4,ordersList.size());


    }
    @Test
    public void listOrderTest(){
        List<Order> orders = orderDao.getOrders();
        logger.info(orders.toString());

    }

    @Test
    public void getOrderByIdTest(){
        Order retrieveOrder= orderDao.getOrderById(o1.getId());
        assertEquals("id should be the same",retrieveOrder .getId(),o1.getId());
        assertEquals("order_number should be the same",retrieveOrder .getOrderNumber(),o1.getOrderNumber());
    }


}
