package com.service;

import com.businessdomain.Address;
import com.businessdomain.Customer;
import com.businessdomain.LoyaltyCard;
import com.businessdomain.Pizza;
import com.dto.CustomerDto;
import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.domain.Specifications;
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
import org.thymeleaf.util.StringUtils;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

import static com.repository.specifications.CustomerSpecification.customerByEmail;
import static com.repository.specifications.CustomerSpecification.customerByName;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
    public static final String USER_NAME = "userName";
    public static final String USER = "user";

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

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

    public CustomerServiceImpl(CustomerRepository customerRepo) {
        this.customerRepo = customerRepo;
    }

    public CustomerServiceImpl() {
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
    public List<Customer> findByNameAndEmail(String name, String email) {
        return customerRepo.findAll(
                Specifications.where(customerByName(name)).and(customerByEmail(email))
        );
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
        Customer customer = new Customer();
        customer.setName(name);
        customer.setAddress(address);
        customer.setLoyaltyCard(loyaltyCard);
        return save(customer);
    }
    //@NotNull before method parameter is not working. Please do not put it like this.
    // validator check`s only model field/properties/type validation annotations
    @Override
    public void calculateCustomerDto(@NotNull CustomerDto customerDto) {
        Set<ConstraintViolation<CustomerDto>> constraintViolations = validator.validate(customerDto);
        if (constraintViolations.size() > 0) {
            List<String> messages = constraintViolations.stream()
                    .map(violation -> violation.getPropertyPath() + ", " +  violation.getMessage()).collect(toList());
            throw new IllegalArgumentException("customerDto not valid: \n" + messages);
        }
    }

    @Override
    public void remove(Customer customer) {
        Assert.notNull(customer, "Customer should not be null");
        customerRepo.delete(customer);
    }

    @Override
    public String uploadFile(MultipartFile file) {
        return null;
    }


    public void setCustomerRepository(CustomerRepository customerRepo) {
        this.customerRepo = customerRepo;
    }
}
