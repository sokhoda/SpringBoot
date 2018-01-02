package com.resource;

import com.assemblers.PizzaAssembler;
import com.businessdomain.DomainHelper;
import com.businessdomain.Pizza;
import com.dto.PizzaDto;
import com.service.PizzaService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Component
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path(RestRoutes.PIZZAS)
@Api(value = "Pizza resource", produces = MediaType.APPLICATION_JSON)
@Slf4j
public class PizzaResource {

    @Inject
    private PizzaAssembler pizzaAssembler;
    @Inject
    private PizzaService pizzaService ;

    @POST
    @Path(RestRoutes.CREATE)
    public Response addPizza(Pizza entity) {
        Pizza pizza = pizzaService.save(entity);
        return Response.ok(pizza).status(Response.Status.CREATED).build();
    }

    @GET
    public Response getAllPizzas() {
        log.info("Get all pizzas");
        List<Pizza> pizzaList  = pizzaService.findAll();
        List<PizzaDto> pizzaDtoList = pizzaAssembler.toResources(pizzaList);
        return Response.ok(pizzaDtoList).build();
    }

    @GET
    @Path("/{"+ DomainHelper.PIZZA_ID + "}")
    public Response getById(@PathParam(DomainHelper.PIZZA_ID) Long pizzaId) {
        Pizza pizza = pizzaService.find(pizzaId);
        PizzaDto pizzaDto = pizzaAssembler.toResource(pizza);
        return Response.ok(pizzaDto).build();
    }

}
