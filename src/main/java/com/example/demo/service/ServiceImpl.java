package com.example.demo.service;

import com.example.demo.entity.Board;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface ServiceImpl<T> {
    public List<T> findAll() throws JsonProcessingException;

    public T findOne(int id) throws JsonProcessingException;

    public boolean insert(T t) throws JsonProcessingException;

    public boolean update(int id, T t) throws JsonProcessingException;

    public boolean delete(int id) throws JsonProcessingException;
}
