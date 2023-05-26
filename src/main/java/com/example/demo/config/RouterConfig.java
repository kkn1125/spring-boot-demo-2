package com.example.demo.config;

import com.example.demo.handler.BoardHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

//@Configuration
public class RouterConfig {

    @Bean
    public RouterFunction<ServerResponse> routes(BoardHandler boardHandler) {
        System.out.println("test");
        return RouterFunctions.route(RequestPredicates.GET("/board").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), boardHandler::read);
    }

}
