package com.example.categorymongo.Entities;

import com.example.categorymongo.Entities.ApiKey;
import com.example.categorymongo.Entities.ApiKeyException;
import org.springframework.core.MethodParameter;
import org.springframework.web.reactive.BindingContext;
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class ApiKeyHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return ApiKey.class.equals(parameter.getParameterType());
    }

    @Override
    public Mono<Object> resolveArgument(MethodParameter parameter, BindingContext bindingContext, ServerWebExchange exchange) {
        return Mono.fromSupplier(()->{
            String value = exchange.getRequest().getHeaders().getFirst("Api-Key");

            if(value==null){
                throw new ApiKeyException("Error");
            }else{
                return new ApiKey(value);
            }
        });
    }
}
