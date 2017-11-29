package pizzaservice;

import businessdomain.Address;
import businessdomain.Customer;
import businessdomain.LoyaltyCard;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface CustomerService {
    List<Customer> findAll();

    Customer save(Customer customer);

    Customer find(Long id);

    List<Customer> findByName(String name);

    List<Customer> findByLoyaltyCard(LoyaltyCard loyaltyCard);

    int delete(Customer customer);

    Customer placeNewCustomer(String name, Address address, LoyaltyCard
            loyaltyCard);

    void remove(Customer customer);

    ModelAndView updateUserInSession(ModelAndView modelAndView, HttpSession session);

    String uploadFile(MultipartFile file);

    String addNewCustomer(Customer customer, BindingResult bindingResult, SessionStatus sessionStatus);
}
