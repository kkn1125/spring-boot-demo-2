package com.example.demo.controller;

import com.example.demo.entity.Board;
import com.example.demo.service.BoardService;
import com.example.demo.vo.ResMessageForm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/board")
@RestController
public class BoardController implements ControllerImpl<Board> {
    @Autowired
    private BoardService service;
    private ResMessageForm rmf;

    private BoardController(BoardService service) {
        this.service = service;
        this.rmf = new ResMessageForm();
    }

    private FilterProvider filter(String field) {
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.serializeAllExcept(field);
        FilterProvider fProv = new SimpleFilterProvider().addFilter(field, filter);
        return fProv;
    }

    private String mapper(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return mapper.writeValueAsString(object);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public String findAll() throws JsonProcessingException {
        List<Board> boardList = service.findAll();
        rmf.setPayload(boardList);
//        FilterProvider provider = filter("payload");
        return mapper(rmf);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public String findOne(int id) throws JsonProcessingException {
        System.out.println("id: " + id);
        Board board = service.findOne(id);
        boolean isExists = board != null;
        if (isExists) {
            rmf.setPayload(board);
            return mapper(rmf);
        } else {
            rmf.setCode(404);
            rmf.setMessage("not found");
            rmf.addDetail("id", "no exists board: " + id);
            rmf.setPayload(null);
            return mapper(rmf);
        }
    }

    @PostMapping("")
    @Override
    public String insert(Board board) throws JsonProcessingException {
        service.insert(board);

        return null;
    }

    @PutMapping("/{id}")
    @Override
    public String update(int id, Board board) throws JsonProcessingException {
        return null;
    }

    @DeleteMapping("/{id}")
    @Override
    public String delete(int id) throws JsonProcessingException {
        return null;
    }
}
