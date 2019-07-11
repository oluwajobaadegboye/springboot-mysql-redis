package com.cache.springbootmysqlredis.service;

import com.cache.springbootmysqlredis.model.Customer;
import com.cache.springbootmysqlredis.model.dto.Response;


public interface CustomerService {
    public Response<Customer> findById(long id);

    public Response<Customer> findByName(String firstNameOrLastName);

    public Response<Customer> findAllCustomer();

    public Response<Customer> saveCustomer(Customer customer);

    public Response<Customer> updateCustomer(Customer customer, long customerId);

    public Response<Customer> findByEmail(String email);

    public Response<Customer> deleteCustomer(long id);
}
