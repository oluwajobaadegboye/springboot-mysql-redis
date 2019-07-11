package com.cache.springbootmysqlredis.service.impl;

import com.cache.springbootmysqlredis.model.Customer;
import com.cache.springbootmysqlredis.model.dto.Response;
import com.cache.springbootmysqlredis.model.dto.ResponseType;
import com.cache.springbootmysqlredis.repository.CustomerRepository;
import com.cache.springbootmysqlredis.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository repository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    @Cacheable(value = "customerByIdCache", key = "#id", unless = "#result==null")
    public Response<Customer> findById(long id) {
        Customer customer = repository.findById(id);
        return customerToResponse(customer, null);
    }

    @Override
    @Cacheable(value = "customerByNameCache", key = "#firstNameOrLastName", unless = "#result==null")
    public Response<Customer> findByName(String firstNameOrLastName) {
        List<Customer> customerList = repository.findByFirstNameOrLastName(firstNameOrLastName);
        return customerToResponse(null, customerList);
    }

    @Override
    @Cacheable(value = "allCustomersCache", key = "#id", unless = "#result==null")
    public Response<Customer> findAllCustomer() {
        List<Customer> customerList = repository.findAll();
        return customerToResponse(null, customerList);
    }

    @Override
    @Caching(
            put= { @CachePut(value= "customerByIdCache", key= "#customer.id") },
            evict= { @CacheEvict(value= "allCustomersCache", allEntries= true) }
    )
    public Response<Customer> saveCustomer(Customer customer) {
        Response<Customer> response = new Response<>();
        if (repository.existsByEmail(customer.getEmail())) {
            response.setResponseCode(ResponseType.CUSTOMER_EXIST.getResponseCode());
            response.setResponseMessage(ResponseType.CUSTOMER_EXIST.getResponseMessage());
        } else {
            Customer customerResponse = repository.save(customer);
            response.setResponseCode(ResponseType.SUCCESSFUL.getResponseCode());
            response.setResponseMessage(ResponseType.SUCCESSFUL.getResponseMessage());
            response.setData(customerResponse);
        }
        return response;
    }

    @Override
    @Caching(
            put= { @CachePut(value= "customerByIdCache", key= "#id") },
            evict= { @CacheEvict(value= "allCustomersCache", allEntries= true) }
    )
    public Response<Customer> updateCustomer(Customer customer, long id) {
        Response<Customer> response = new Response<>();
        if (repository.existsById(id)) {
            repository.save(customer);
            response.setData(customer);
            response.setResponseCode(ResponseType.SUCCESSFUL.getResponseCode());
            response.setResponseMessage(ResponseType.SUCCESSFUL.getResponseMessage());
        } else {
            response.setResponseCode(ResponseType.CUSTOMER_DOES_NOT_FOUND.getResponseCode());
            response.setResponseMessage(ResponseType.CUSTOMER_DOES_NOT_FOUND.getResponseMessage());
        }
        return response;
    }

    @Override
    public Response<Customer> findByEmail(String email) {
        Customer customer = repository.findByEmail(email);
        return customerToResponse(customer, null);
    }

    @Override
    public Response<Customer> deleteCustomer(long id) {
        Response<Customer> response = new Response<>();
        repository.deleteById(id);
        response.setResponseCode(ResponseType.SUCCESSFUL.getResponseCode());
        response.setResponseMessage(ResponseType.SUCCESSFUL.getResponseMessage());
        return response;
    }

    private Response<Customer> customerToResponse(Customer customer, List<Customer> customerList) {
        Response<Customer> response = new Response<>();
        if (customer == null && customerList == null) {
            response.setResponseCode(ResponseType.CUSTOMER_DOES_NOT_FOUND.getResponseCode());
            response.setResponseMessage(ResponseType.CUSTOMER_DOES_NOT_FOUND.getResponseMessage());
        } else {
            response.setResponseCode(ResponseType.SUCCESSFUL.getResponseCode());
            response.setResponseMessage(ResponseType.SUCCESSFUL.getResponseMessage());
            response.setData(customer);
            response.setList(customerList);
        }
        return response;
    }

}
