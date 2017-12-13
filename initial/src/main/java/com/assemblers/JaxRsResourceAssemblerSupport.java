package com.assemblers;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.jaxrs.JaxRsLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.util.Assert;

public class JaxRsResourceAssemblerSupport<T, D extends ResourceSupport> extends ResourceAssemblerSupport<T, D> {

    private Class<?> controllerClass;

    public JaxRsResourceAssemblerSupport(Class<?> controllerClass, Class<D> resourceType) {
        super(controllerClass, resourceType);
        this.controllerClass = controllerClass;
    }

    public D toResource(T entity) {
        return null;
    }

    @Override
    protected D createResourceWithId(Object id, T entity, Object... parameters) {
        Assert.notNull(entity);
        Assert.notNull(id);
        D resource = instantiateResource(entity);
        resource.add(JaxRsLinkBuilder.linkTo(controllerClass, parameters).slash(id).withSelfRel());
        return resource;
    }
}
