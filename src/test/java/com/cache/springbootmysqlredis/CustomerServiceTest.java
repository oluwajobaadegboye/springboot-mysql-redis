package com.cache.springbootmysqlredis;

import com.cache.springbootmysqlredis.model.Customer;
import com.cache.springbootmysqlredis.model.dto.Response;
import com.cache.springbootmysqlredis.model.dto.ResponseType;
import com.cache.springbootmysqlredis.repository.CustomerRepository;
import com.cache.springbootmysqlredis.service.CustomerService;
import com.cache.springbootmysqlredis.service.impl.CustomerServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDate;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository repository;

    @InjectMocks
    private CustomerService service = new CustomerServiceImpl(repository);

    Customer customer = null;

    @Before
    public void setUp() {
        customer = new Customer();
        customer.setAddress("55 East Wabash");
        customer.setDateOfBirth(LocalDate.now().minusYears(18));
        customer.setFirstName("Joba");
        customer.setLastName("Adegboye");
        customer.setPhone("6415050734");
        customer.setEmail("jobaadegboye@gmail.com");
    }

    @Test
    public void findById() {
        long id = 1;
        Customer mockedCustomer = new Customer("MockedFirstName", "HiMock", "6418191033", "joba@gmail.com", "55 W Wabash", LocalDate.now().minusYears(17));
        when(repository.findById(id)).thenReturn(mockedCustomer);
        Response<Customer> cust = service.findById(id);
        Assert.assertEquals(mockedCustomer.getFirstName(),cust.getData().getFirstName());
    }

    @Test
    public void findByEmail() {
        Response<Customer> customerByEmailResp = service.findByEmail(customer.getEmail());
        Assert.assertNotNull(customerByEmailResp.getData());
        Assert.assertTrue(customerByEmailResp.getResponseCode().equals(ResponseType.SUCCESSFUL.getResponseCode()));
    }

    @Test
    public void saveCustomer() {
        Response<Customer> response = service.saveCustomer(this.customer);
        Assert.assertTrue(response.getResponseCode().equals(ResponseType.SUCCESSFUL.getResponseCode()) || response.getResponseCode().equals(ResponseType.CUSTOMER_EXIST.getResponseCode()));
    }

}
