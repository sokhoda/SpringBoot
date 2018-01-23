package com.service;

import com.businessdomain.Address;
import com.businessdomain.Customer;
import com.businessdomain.LoyaltyCard;
import com.dto.CustomerDto;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface CustomerService {
    List<Customer> findAll();

    Customer save(Customer customer);

    Customer find(Long id);

    List<Customer> findByName(String name);

    List<Customer> findByNameAndEmail(String name, String email);

    List<Customer> findByLoyaltyCard(LoyaltyCard loyaltyCard);

    void delete(Customer customer);

    Customer placeNewCustomer(String name, Address address, LoyaltyCard
            loyaltyCard);

    void calculateCustomerDto(CustomerDto customerDto);

    void remove(Customer customer);

    ModelAndView updateUserInSession(ModelAndView modelAndView, HttpSession session);

    String uploadFile(MultipartFile file);

    String addNewCustomer(Customer customer, BindingResult bindingResult, SessionStatus sessionStatus);
}
