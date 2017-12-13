package com.assemblers;

import com.businessdomain.Pizza;
import com.dto.PizzaDto;
import com.google.common.collect.Lists;
import com.mapper.PizzaMapper;
import com.resource.PizzaResource;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PizzaAssembler extends JaxRsResourceAssemblerSupport<Pizza, PizzaDto> {

    private PizzaMapper pizzaMapper = Mappers.getMapper(PizzaMapper.class);

    public PizzaAssembler() {
        super(PizzaResource.class, PizzaDto.class);
    }

    @Override
    public PizzaDto toResource(Pizza entity) {
        PizzaDto result =  pizzaMapper.pizzaEntityToPizza(entity);
        PizzaDto dto = createResourceWithId(entity.getPizzaId(), entity);
        result.add(dto.getLinks());
        return result;
    }

    @Override
    public List<PizzaDto> toResources(Iterable<? extends Pizza> entities) {
        List<PizzaDto> pizzaDtos = Lists.newArrayList();
        entities.forEach(entitity -> pizzaDtos.add(toResource(entitity)));
        return pizzaDtos;
    }
}
