package web.app;

import businessdomain.DomainHelper;
import businessdomain.Pizza;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import service.PizzaService;
import web.app.Routes;

import javax.inject.Inject;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class PizzaController {

    @Autowired
    public PizzaService pizzaService;

    @RequestMapping(Routes.PIZZA_LIST)
    public String getAllPizzas(ModelMap model) {
        List<Pizza> pizzalist = pizzaService.findAll();
        model.addAttribute(DomainHelper.PIZZALIST, pizzalist);
//        model.addAttribute(DomainHelper.PIZZALIST_FOR_XML_MAPPING, pizzalist);
//        return Routes.PIZZA_LIST_PAGE;
       return Routes.PIZZA_LIST_PAGE;
    }

    @RequestMapping("/")
    public String welcome(Map<String, Object> model) {
        model.put("message", "welcome!");
        return "welcome";
    }

    @RequestMapping(value = "/pizza/{pizzaId}", method = RequestMethod.GET)
    public String findById(@PathVariable("pizzaId") Long pizzaId, ModelMap model) {
        Pizza pizza = pizzaService.find(pizzaId);
        if (pizza != null) {
            model.addAttribute(DomainHelper.PIZZA, pizza);
        }
        return Routes.PIZZA_EDIT_PAGE;
    }

    @RequestMapping(Routes.PIZZA_CREATE)
    public String create(Model model) {
        model.addAttribute(DomainHelper.PIZZA, new Pizza());
        return Routes.PIZZA_EDIT_PAGE;
    }

    @RequestMapping(Routes.PIZZA_EDIT)
    public String edit(@RequestParam Long pizzaId, ModelMap model) {
        Pizza pizza = pizzaService.find(pizzaId);
        if (pizza != null) {
            model.addAttribute(DomainHelper.PIZZA, pizza);
        }
        return Routes.PIZZA_EDIT_PAGE;
    }

    @RequestMapping(value = Routes.PIZZA_ADD_NEW, method = RequestMethod.POST)
    public String addNew(@ModelAttribute @Validated Pizza pizza, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
//            log.error("!!!!!ERRORS:");
            return Routes.PIZZA_EDIT_PAGE;
        }
        pizzaService.save(pizza);
        return Routes.REDIRECT_PIZZA_LIST_PAGE;
    }

    @RequestMapping(Routes.PIZZA_PIZZALIST_UPLOAD_IOEXCEPTION)
    public void pizzaCheckedException() throws IOException {
        throw new IOException();
    }

    @RequestMapping(Routes.PIZZA_PIZZALIST_UPLOAD)
    public String showMultipart() {
        return Routes.UTILS_UPLOAD_PIZZA_LIST_UPLOAD_PAGE;
    }

    @RequestMapping(value = Routes.PIZZA_REMOVE, method = RequestMethod.POST)
    public String remove(@ModelAttribute Pizza pizza) {
        pizzaService.remove(pizza);
        return Routes.REDIRECT_PIZZA_LIST_PAGE;
    }

}
