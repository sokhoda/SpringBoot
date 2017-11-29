package web.rest.asm;

import businessdomain.Pizza;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;
import web.rest.PizzaRESTController;
import web.rest.PizzaResource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class PizzaResourceAssembler extends ResourceAssemblerSupport<Pizza, PizzaResource>{

    public PizzaResourceAssembler() {
        super(PizzaRESTController.class, PizzaResource.class);
    }

    @Override
    public PizzaResource toResource(Pizza pizza) {
        PizzaResource res = new PizzaResource();
        res.setPizzaId(pizza.getPizzaId());
        res.setName(pizza.getName());
        res.setPrice(pizza.getPrice());
        res.setType(pizza.getType());

        Link linkAll = linkTo(methodOn(PizzaRESTController.class).findAll()).withRel("pizzas");
        Link link = linkTo(methodOn(PizzaRESTController.class).find(pizza.getPizzaId())).withSelfRel();
        res.add(linkAll, link);

        return res;
    }
}
