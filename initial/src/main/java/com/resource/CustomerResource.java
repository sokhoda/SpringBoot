package com.resource;

import com.assemblers.CustomerAssembler;
import com.businessdomain.Customer;
import com.businessdomain.DomainHelper;
import com.dto.CustomerDto;
import com.service.CustomerService;
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
@Path(RestRoutes.CUSTOMERS)
@Api(value = "Customer resource", produces = MediaType.APPLICATION_JSON)
@Slf4j
public class CustomerResource {

    @Inject
    private CustomerAssembler customerAssembler;
    @Inject
    private CustomerService customerService;

    @POST
    @Path(RestRoutes.CREATE)
    public Response addCustomer(Customer entity) {
        Customer customer = customerService.save(entity);
        return Response.ok(customer).status(Response.Status.CREATED).build();
    }

    @GET
    public Response getAllCustomers() {
        log.debug("get all customers");
        List<Customer> customerList = customerService.findAll();
        List<CustomerDto> customerDtoList = customerAssembler.toResources(customerList);
        return Response.ok(customerDtoList).build();
    }

    @GET
    @Path("/{" + DomainHelper.CUSTOMER_ID + "}")
    public Response getById(@PathParam(DomainHelper.CUSTOMER_ID) Long customerId) {
        Customer customer = customerService.find(customerId);
        CustomerDto customerDto = customerAssembler.toResource(customer);
        return Response.ok(customerDto).build();
    }
}
