package com.ahmet.repository;

import com.ahmet.repository.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {

public  Optional<Customer> findByEmailAndPassword(String email,String password);
    public Boolean existsByEmail(String email);

    @Query("select  c.balance from Customer  c where c.id= ?1 ")
    public Optional<Double> getCustomerByBalance(Long customerId);
}
