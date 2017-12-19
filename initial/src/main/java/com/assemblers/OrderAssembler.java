package com.assemblers;

import com.businessdomain.Orders;
import com.dto.OrderDto;
import com.google.common.collect.Lists;
import com.mapper.OrderMapper;
import com.resource.OrderResource;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderAssembler extends JaxRsResourceAssemblerSupport<Orders, OrderDto> {

    private OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);

    public OrderAssembler() {
        super(OrderResource.class, OrderDto.class);
    }

    @Override
    public OrderDto toResource(Orders entity) {
        OrderDto result = orderMapper.orderEntityToOrder(entity);
        OrderDto dto = createResourceWithId(entity.getOrderId(), entity);
        result.add(dto.getLinks());
        return result;
    }

    @Override
    public List<OrderDto> toResources(Iterable<? extends Orders> entities) {
        List<OrderDto> orderDtos = Lists.newArrayList();
        entities.forEach(entitity -> orderDtos.add(toResource(entitity)));
        return orderDtos;
    }
}
