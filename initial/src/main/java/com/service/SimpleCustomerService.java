package com.service;

import com.businessdomain.Address;
import com.businessdomain.Customer;
import com.businessdomain.LoyaltyCard;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.repository.CustomerRepository;
import com.validators.javax.OrderedCustomerCheck;
import com.app.Routes;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service("customerService")
public class SimpleCustomerService implements CustomerService {
    public static final String USER_NAME = "userName";
    public static final String USER = "user";
//    @Inject
//    CustomParser<CustomerDto> customerListParser;

    @Inject
    @Qualifier("customerRepository")
    private CustomerRepository customerRepo;
//    @Inject
//    private CustomerCreatedEventPublisher customerCreatedEventPublisher;
    @Inject
    private CustomerValidationService customerValidationService;

//    @Value("#{properties['customer.created.message']}")
//    private String customerCreatedMessage;

    public SimpleCustomerService(CustomerRepository customerRepo) {
        this.customerRepo = customerRepo;
    }

    public SimpleCustomerService() {
    }

    @Override
    public List<Customer> findAll() {
        return customerRepo.findAll();
    }

    @Transactional
    @Override
    public Customer save(Customer customer) {
        return customerRepo.save(customer);
    }

    @Override
    public ModelAndView updateUserInSession(ModelAndView modelAndView, HttpSession session) {
        String userName = (String) modelAndView.getModelMap().get(USER_NAME);
        List<Customer> customers    = findByName(userName);
        customers.stream()
                .findFirst()
                .ifPresent(customer -> session.setAttribute(USER, customer));
        return modelAndView;
    }

//    @Override
//    public String uploadFile(MultipartFile file) {
//        Assert.notNull(file, "File should not be null");
//        try {
//            InputStream inputStream = new ByteArrayInputStream(file.getBytes());
//            List<CustomerDto> customerDtos = customerListParser.parse(inputStream);
//
//            customerDtos.stream().map(CustomerDtoConverter::toCustomerEntity).forEach(this::save);
//        } catch (IOException ex) {
//            throw new RuntimeException(String.format(CustomParser.FAIL_TO_UPLOAD_FILE, file.getOriginalFilename()), ex);
//        }
//        return String.format(CustomParser.FILE_UPLOADED_SUCCESSFULLY, file.getOriginalFilename());
//    }

    @Override
    public String addNewCustomer(Customer customer, BindingResult bindingResult, SessionStatus sessionStatus) {
        boolean isCustomerNotValid = customerValidationService.isNotValid(customer, bindingResult, OrderedCustomerCheck.class);
        if (isCustomerNotValid) {
            return Routes.CUSTOMER_EDIT_PAGE;
        }
        sessionStatus.setComplete();
        Customer newCustomer = save(customer);
//        CustomerCreatedEvent customerCreatedEvent = new CustomerCreatedEvent(this, newCustomer, customerCreatedMessage);
//        customerCreatedEventPublisher.doPublishEvent(customerCreatedEvent);
        return Routes.REDIRECT_CUSTOMER_LIST_PAGE;
    }

    @Override
    public Customer find(Long id) {
        return customerRepo.findOne(id);
    }

    @Override
    public List<Customer> findByName(String name) {
        return customerRepo.findByName(name);
    }

    @Override
    public List<Customer> findByLoyaltyCard(LoyaltyCard loyaltyCard) {
        return customerRepo.findByLoyaltyCard(loyaltyCard);
    }

    @Override
    public void delete(Customer customer) {
        customerRepo.delete(customer);
    }

    @Transactional
    @Override
    public Customer placeNewCustomer(String name, Address address, LoyaltyCard loyaltyCard) {
        Customer customer = createNewCustomer();
        customer.setName(name);
        customer.setAddress(address);
        customer.setLoyaltyCard(loyaltyCard);
        return save(customer);
    }

    @Override
    public void remove(Customer customer) {
        Assert.notNull(customer, "Customer should not be null");
        customerRepo.delete(customer);
    }

    Customer createNewCustomer() {
        throw new IllegalStateException("Customer createNewCustomer method " +
                "not overridden");
    }

    @Override
    public String uploadFile(MultipartFile file) {
        return null;
    }


    public void setCustomerRepository(CustomerRepository customerRepo) {
        this.customerRepo = customerRepo;
    }
}
