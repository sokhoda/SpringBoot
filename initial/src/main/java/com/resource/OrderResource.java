package com.resource;

import com.assemblers.OrderAssembler;
import com.businessdomain.DomainHelper;
import com.businessdomain.Orders;
import com.dto.OrderDto;
import com.service.OrderService;
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
@Path(RestRoutes.ORDERS)
@Api(value = "Order resource", produces = MediaType.APPLICATION_JSON)
@Slf4j
public class OrderResource {

    @Inject
    private OrderService orderService;
    @Inject
    private OrderAssembler orderAssembler;

    @POST
    @Path(RestRoutes.CREATE)
    public Response addOrder(Orders entity) {
        log.info("About to save new order");
        Orders order = orderService.save(entity);
        return Response.ok(order).status(Response.Status.CREATED).build();
    }

    @GET
    public Response getAllOrders() {
        log.info("Get all orders");
        List<Orders> orderList = orderService.findAll();
        List<OrderDto> orderDtos = orderAssembler.toResources(orderList);
        return Response.ok(orderDtos).build();
    }

    @GET
    @Path("/{" + DomainHelper.ORDER_ID + "}")
    public Response getById(@PathParam(DomainHelper.ORDER_ID) Long orderId) {
        Orders order = orderService.find(orderId);
        OrderDto orderDto = orderAssembler.toResource(order);
        return Response.ok(orderDto).build();
    }
}
