package com.cache.springbootmysqlredis.controller;

import com.cache.springbootmysqlredis.model.Customer;
import com.cache.springbootmysqlredis.model.dto.Response;
import com.cache.springbootmysqlredis.model.dto.ResponseType;
import com.cache.springbootmysqlredis.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @GetMapping("/{id}")
    public ResponseEntity<Response> findById(@PathVariable long id) {
        Response<Customer> response = service.findById(id);
        ResponseEntity<Response> responseEntity = toResponseEntity(response);
        return responseEntity;
    }

    @GetMapping("/byName/{name}")
    public ResponseEntity<Response> findByName(@PathVariable String firstNameOrLastName) {
        Response<Customer> response = service.findByName(firstNameOrLastName);
        ResponseEntity<Response> responseEntity = toResponseEntity(response);
        return responseEntity;
    }

    @GetMapping
    public ResponseEntity<Response> findAllCustomer() {
        Response<Customer> response = service.findAllCustomer();
        ResponseEntity<Response> responseEntity = toResponseEntity(response);
        return responseEntity;
    }

    @PostMapping
    public ResponseEntity<Response> saveCustomer(@RequestBody Customer customer) {
        Response<Customer> response = service.saveCustomer(customer);
        ResponseEntity<Response> responseEntity = toResponseEntity(response);
        return responseEntity;
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<Response> updateCustomer(@RequestBody Customer customer, @PathVariable long customerId) {
        Response<Customer> response = service.updateCustomer(customer, customerId);
        ResponseEntity<Response> responseEntity = toResponseEntity(response);
        return responseEntity;
    }

    @GetMapping("/byEmail/{email}")
    public ResponseEntity<Response> findByEmail(@PathVariable String email) {
        Response<Customer> response = service.findByEmail(email);
        ResponseEntity<Response> responseEntity = toResponseEntity(response);
        return responseEntity;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteCustomer(@PathVariable long id) {
        service.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private ResponseEntity<Response> toResponseEntity(Response<Customer> response) {
        ResponseEntity<Response> responseEntity = null;
        if (ResponseType.CUSTOMER_DOES_NOT_FOUND.getResponseCode().equals(response.getResponseCode())) {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        if (ResponseType.CUSTOMER_EXIST.getResponseCode().equals(response.getResponseCode())) {
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        } else if (ResponseType.SUCCESSFUL.getResponseCode().equals(response.getResponseCode())) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
