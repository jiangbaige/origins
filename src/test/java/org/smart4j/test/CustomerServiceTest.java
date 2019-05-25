package org.smart4j.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.smart4j.test.aop.helper.DatabaseHelper;
import org.smart4j.test.aop.model.Customer;
import org.smart4j.test.aop.service.CustomerService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerServiceTest {

    private final CustomerService customerService;


    public CustomerServiceTest() {
        customerService = new CustomerService();
    }

    @Before
    public void init() throws Exception {
        DatabaseHelper.executeSqlFile("sql/customer_init.sql");
    }

    @Test
    public void getCustomerListTest() throws Exception {
        System.out.println("CustomerServiceTest.test.getCustomerListTest...");
        List<Customer> customerList = customerService.getCustomerList();
        Assert.assertEquals(2,customerList.size());
    }

    @Test
    public void getCustomerTest()  throws Exception {
        long id = 1;
        Customer customer = customerService.getCustomer(id);
        Assert.assertNotNull(customer);
    }

    @Test
    public void createCustomerTest()  throws Exception {
        Map<String, Object> filedMap = new HashMap<String,Object>();
        filedMap.put("name","customer100");
        filedMap.put("contact","John");
        filedMap.put("telephone","13512345678");
        boolean result = customerService.createCustomer(filedMap);
        Assert.assertTrue(result);
    }
    @Test
    public void updateCustomerTest()  throws Exception {
        long id=1;
        Map<String, Object> filedMap = new HashMap<String,Object>();
        filedMap.put("contact","Eric");
        boolean result = customerService.updateCustomer(id,filedMap);
        Assert.assertTrue(result);
    }
    @Test
    public void deleteCustomerTest()  throws Exception {
        long id=1;
        boolean result = customerService.deleteCustomer(id);
        Assert.assertTrue(result);
    }

}
