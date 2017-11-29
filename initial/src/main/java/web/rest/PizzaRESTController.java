package web.rest;

import businessdomain.Pizza;
import businessdomain.PizzaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import pizzaservice.PizzaService;
import web.rest.asm.PizzaResourceAssembler;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class PizzaRESTController {

    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private PizzaResourceAssembler pizzaResourceAssembler;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<PizzaResource> find() {
        PizzaResource res = pizzaResourceAssembler.toResource(new Pizza());
        res.add(linkTo(methodOn(PizzaRESTController.class).findByType(null)).withRel("type"));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @RequestMapping(value = "/pizza/{pizzaID}", method =
            RequestMethod.GET)
    public ResponseEntity<PizzaResource> find(@PathVariable("pizzaID") Long pizzaID) {
        Pizza pizza = pizzaService.find(pizzaID);
        if (pizza == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pizzaResourceAssembler.toResource(pizza), HttpStatus.FOUND);
    }

    @RequestMapping(value = "/pizza/pizzas", method = RequestMethod.GET)
    public ResponseEntity<List<PizzaResource>> findAll() {
        List<Pizza> pizzas = pizzaService.findAll();
        if (pizzas == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<PizzaResource> resources = pizzas.stream().map
                (pizza -> pizzaResourceAssembler.toResource(pizza)).collect(Collectors.toList());
        return new ResponseEntity<>(resources, HttpStatus.FOUND);
    }

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    @Secured("ROLE_USER")
    public String[] hello() {
        return new String[]{"Hello from REST"};
    }

    @RequestMapping(value = "/pizza/pizzas/type/{type}", method = RequestMethod.GET)
    public ResponseEntity<List<PizzaResource>> findByType(@PathVariable
                                                          PizzaType type) {
        List<Pizza> pizzas = pizzaService.findByType(type);
        if (pizzas == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<PizzaResource> resources = pizzas.stream().map
                (pizza -> {
                    PizzaResource res = pizzaResourceAssembler.toResource(pizza);
                    Link link = linkTo(methodOn(PizzaRESTController.class)
                            .findByType(pizza.getType())).withRel("type");
                    res.add(link);
                    return res;
                }).collect(Collectors.toList());
        return new ResponseEntity<>(resources, HttpStatus.FOUND);
    }

    @RequestMapping(value = "/pizza", method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody Pizza pizza,
                                       UriComponentsBuilder builder) {
        System.out.println(pizza);
        Pizza p = pizzaService.save(pizza);

        HttpHeaders headers = new HttpHeaders();

        headers.setLocation(
                builder.path("/pizza/{pizzaID}")
                        .buildAndExpand(p.getPizzaId()).toUri()
        );

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
}
