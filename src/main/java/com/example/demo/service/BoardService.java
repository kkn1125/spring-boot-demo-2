package com.example.demo.service;

import com.example.demo.entity.Board;
import com.example.demo.mapper.BoardMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService implements ServiceImpl<Board> {

    @Autowired
    BoardMapper mapper;

    public BoardService(BoardMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<Board> findAll() throws JsonProcessingException {
        return mapper.findAll();
    }

    @Override
    public Board findOne(int id) throws JsonProcessingException {
        Board board = mapper.findOne(id).orElse(null);
        return board;
    }

    @Override
    public boolean insert(Board board) throws JsonProcessingException {
        mapper.insert(board);

        return board.getId() != null;
    }

    @Override
    public boolean update(int id, Board board) throws JsonProcessingException {
        Board existsBoard = mapper.findOne(id).orElse(null);
        if (existsBoard != null) {
            mapper.update(id, board);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) throws JsonProcessingException {
        Board existsBoard = mapper.findOne(id).orElse(null);
        if (existsBoard != null) {
            mapper.delete(id);
            return true;
        }
        return false;
    }
}
