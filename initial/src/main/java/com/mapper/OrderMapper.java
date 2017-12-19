package com.mapper;

import com.businessdomain.Orders;
import com.businessdomain.Pizza;
import com.dto.OrderDto;
import org.mapstruct.Mapper;

@Mapper
public interface OrderMapper {
    Orders orderToOrderEntity(OrderDto orderDto);

    OrderDto orderEntityToOrder(Orders order);
}
