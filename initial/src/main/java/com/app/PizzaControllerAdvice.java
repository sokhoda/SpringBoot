package com.app;

import com.businessdomain.DomainHelper;
import com.businessdomain.Pizza;
import com.service.PizzaService;
import com.validators.PizzaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@ControllerAdvice(assignableTypes = {PizzaController.class})
public class PizzaControllerAdvice {
    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private PizzaValidator pizzaValidator;



//    @ModelAttribute
//    public Pizza pizzaMeth(@RequestParam(name = "pizzaId", required = false)
//                                   Long pizzaId){
//        if (pizzaId != null){
//            return pizzaService.find(pizzaId);
//        } else {
//            return new Pizza();
//        }
//    }

    @ModelAttribute(name = DomainHelper.PIZZA)
    public Pizza pizzaMeth(@RequestParam(name = "pizzaId", required = false) Pizza pizza){
        System.out.println("ControllerAdvice: " + pizza);

        return pizza;
    }

    @InitBinder
    public void pizzaBinder(WebDataBinder binder){
        binder.addCustomFormatter(new DateFormatter("dd-MM-yyyy"));
        binder.addValidators(pizzaValidator);
    }
}
