package com.example.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

public interface ControllerImpl<T> {

    public Mono<String> findAll() throws JsonProcessingException;

    public String findOne(@PathVariable int id) throws JsonProcessingException;

    public String insert(T t) throws JsonProcessingException;

    public String update(@PathVariable int id, T t) throws JsonProcessingException;

    public String delete(@PathVariable int id) throws JsonProcessingException;
}
