package web.app.controllers;

import businessdomain.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import pizzaservice.CustomerService;
import web.infrastructure.Routes;

import javax.inject.Inject;
import java.util.List;

@Controller
public class CustomerController {
    private static final String CUSTOMER = "customer";
    private static final String CUSTOMERLIST = "customerlist";

    @Inject
    private CustomerService customerService;

    @RequestMapping(Routes.CUSTOMER_CREATE)
    public String create(Model model) {
        model.addAttribute(CUSTOMER, new Customer());
        return Routes.CUSTOMER_EDIT_PAGE;
    }

    @RequestMapping(Routes.CUSTOMER_EDIT)
    public String customerEdit(@RequestParam Long customerId, Model model) {
        return Routes.CUSTOMER_EDIT_PAGE;
    }

    @RequestMapping(value = Routes.CUSTOMER_ADD_NEW, method = RequestMethod.POST)
    public String addNew(@ModelAttribute Customer customer, BindingResult bindingResult, SessionStatus sessionStatus) {
        return customerService.addNewCustomer(customer, bindingResult, sessionStatus);
    }

    @RequestMapping(value = Routes.CUSTOMER_CUSTOMERLIST_UPLOAD, method = RequestMethod.POST)
    public String uploadFiles(@RequestParam("nname") String name,
                              @RequestParam("ffile") MultipartFile file,
                              Model model) {
        model.addAttribute("resultMessage", customerService.uploadFile(file));
        return Routes.UTILS_UPLOAD_CUSTOMER_LIST_PAGE;
    }

    @RequestMapping(Routes.CUSTOMER_CUSTOMERLIST_UPLOAD)
    public String showMultipart() {
        return Routes.UTILS_UPLOAD_CUSTOMER_LIST_PAGE;
    }

    @RequestMapping(value = Routes.CUSTOMER_REMOVE, method = RequestMethod.POST)
    public String remove(@ModelAttribute Customer customer) {
        customerService.remove(customer);
        return Routes.REDIRECT_CUSTOMER_LIST_PAGE;
    }

    @RequestMapping(value = Routes.CUSTOMER_LIST)
    public String getAllCustomers() {
        return Routes.CUSTOMER_LIST_PAGE;
    }

//    @PostFilter("filterObject.type != T (domain.CustomerType).MEAT")
//    @PostFilter("filterObject.customerId > 5")
    @ModelAttribute(CUSTOMERLIST)
    public List<Customer> getCustomers() {
        return customerService.findAll();
    }
}
