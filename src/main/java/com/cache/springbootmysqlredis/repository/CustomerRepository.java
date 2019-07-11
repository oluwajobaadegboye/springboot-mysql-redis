package com.cache.springbootmysqlredis.repository;

import com.cache.springbootmysqlredis.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    @Query("select distinct c from Customer c where c.firstName like '%name%' OR c.lastName like '%name%'")
    public List<Customer> findByFirstNameOrLastName(String name);
    public Customer findById(long id);
    public Customer findByEmail(String email);
    public List<Customer> findAll();
    public boolean existsByEmail(String email);
}
