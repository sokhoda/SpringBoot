package web.app.controllers;

import businessdomain.DomainHelper;
import businessdomain.Pizza;
import exceptions.PizzaPriceException;
import exceptions.PizzaTypeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pizzaservice.PizzaService;
import web.app.dto.PizzaList;
import web.infrastructure.Routes;

import javax.inject.Inject;
import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
public class PizzaController {

    @Inject
    public PizzaService pizzaService;

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
    public String edit(@RequestParam Long pizzaId) {
        return Routes.PIZZA_EDIT_PAGE;
    }

    @RequestMapping(value = Routes.PIZZA_ADD_NEW, method = RequestMethod.POST)
    public String addNew(@ModelAttribute @Validated Pizza pizza, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("!!!!!ERRORS:");
            return Routes.PIZZA_EDIT_PAGE;
        }
        pizzaService.save(pizza);
        return Routes.REDIRECT_PIZZA_LIST_PAGE;
    }

    @RequestMapping(Routes.PIZZA_TYPE_EXCEPTION)
    public void pizzaTypeException() {
        throw new PizzaTypeException("Pizza type not found");
    }

    @RequestMapping(Routes.PIZZA_PRICE_EXCEPTION)
    public void pizzaPriceException() {
        throw new PizzaPriceException("Pizza price not valid");
    }

    @RequestMapping(Routes.PIZZA_PIZZALIST_UPLOAD_IOEXCEPTION)
    public void pizzaCheckedException() throws IOException {
        throw new IOException();
    }

    @RequestMapping(value = Routes.PIZZA_PIZZALIST_UPLOAD, method = RequestMethod.POST)
    public String uploadFiles(@RequestParam("nname") String name,
                              @RequestParam("ffile") MultipartFile file,
                              Model model) {

        model.addAttribute("resultMessage", pizzaService.uploadFile(file));
        return Routes.UTILS_UPLOAD_PIZZA_LIST_UPLOAD_PAGE;
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

    @RequestMapping(Routes.PIZZA_LIST)
    public String getAllPizzas(ModelMap model) {
        List<Pizza> pizzalist = pizzaService.findAll();
        model.addAttribute(DomainHelper.PIZZALIST, pizzalist);
        model.addAttribute(DomainHelper.PIZZALIST_FOR_XML_MAPPING, new PizzaList(pizzalist));
        return Routes.PIZZA_LIST_PAGE;
    }

//    @PostFilter("filterObject.type != T (businessdomain.PizzaType).MEAT")
//    @ModelAttribute(DomainHelper.PIZZALIST)
//    public List<Pizza> getPizzas() {
//        List<Pizza> pizzalist = pizzaService.findAll();
//        return pizzalist;
//    }
}
