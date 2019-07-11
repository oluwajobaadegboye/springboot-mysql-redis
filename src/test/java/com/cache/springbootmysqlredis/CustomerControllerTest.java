package com.cache.springbootmysqlredis;

import com.cache.springbootmysqlredis.model.Customer;
import com.cache.springbootmysqlredis.model.dto.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootMysqlRedisApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    static final String BASE_URL = "/api/v1/customers";

    @Before
    public void setUp() {

    }

    @Test
    public void findById() {
        String url = String.format("%s/%s", BASE_URL, 1);
        ResponseEntity<Response> entity = restTemplate.getForEntity(url, Response.class);
        Assert.assertNotNull(entity.getBody());
    }

    @Test
    public void findByName() {
        String url = String.format("%s/byName/%s", BASE_URL, "Joba");
        ResponseEntity<Response> entity = restTemplate.getForEntity(url, Response.class);
        Assert.assertNotNull(entity.getBody());
    }

    @Test
    public void findAllCustomer() {
        ResponseEntity<Response> entity = restTemplate.getForEntity(BASE_URL, Response.class);
        Assert.assertNotNull(entity.getBody());
        Assert.assertTrue(entity.getBody().getList().size() > 0 );
    }

    @Test
    public void saveCustomer() {
        Customer customer = new Customer();
        customer.setAddress("55 East Wabash");
        customer.setDateOfBirth(LocalDate.now().minusYears(18));
        customer.setFirstName("Joba");
        customer.setLastName("Adegboye");
        customer.setPhone("6415050734");
        customer.setEmail("jobaadegboye@gmail.com");

        ResponseEntity<Response> responseEntity = restTemplate.postForEntity(BASE_URL, customer, Response.class);
    }

//    @Test
//    public void updateCustomer() {
//
//    }

    @Test
    public void findByEmail() {
        String url = String.format("%s/byEmail/%s", BASE_URL, "jobaadegboye@gmail.com");
        ResponseEntity<Response> entity = restTemplate.getForEntity(url, Response.class);
        Assert.assertNotNull(entity.getBody());
//        Assert.assertEquals(((Customer)entity.getBody().getData()).getEmail(),"jobaadegboye@gmail.com");
    }

//    @Test
//    public void deleteCustomer() {
//
//    }

}
