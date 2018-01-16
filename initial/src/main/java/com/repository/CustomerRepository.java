package com.repository;

import com.businessdomain.Customer;
import com.businessdomain.LoyaltyCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {

    List<Customer> findByName(String name);

    List<Customer> findByLoyaltyCard(LoyaltyCard loyaltyCard);
}
