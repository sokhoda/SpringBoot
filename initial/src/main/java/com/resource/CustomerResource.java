package com.resource;

import com.assemblers.CustomerAssembler;
import com.businessdomain.Customer;
import com.businessdomain.DomainHelper;
import com.dto.CustomerDto;
import com.service.CustomerService;
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
public class CustomerResource {

    @Inject
    private CustomerAssembler customerAssembler;
    @Inject
    private CustomerService customerService;

    @GET
    public Response getAllCustomers() {
        List<Customer> customerList  = customerService.findAll();
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
