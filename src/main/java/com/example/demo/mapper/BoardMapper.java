package com.example.demo.mapper;

import com.example.demo.entity.Board;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BoardMapper {

    @Select("SELECT * FROM board ORDER BY id ASC")
    public List<Board> findAll();

    @Select("SELECT * FROM board WHERE id=#{id} ORDER BY id DESC")
    public Optional<Board> findOne(int id);

    //    @Options(keyProperty = "id", useGeneratedKeys = true)
    @SelectKey(statement = {"SELECT MAX(id) FROM board"}, keyProperty = "id", before = false, resultType = int.class)
    @Insert("INSERT INTO board (title, content, author) VALUES (#{title},#{content},#{author})")
    public void insert(Board board);

    @Update("UPDATE TABLE board SET title=#{title}, content=#{content}, author=#{author} WHERE id=#{id}}")
    public void update(int id, Board board);

    @Delete("DELETE FROM board WHERE id=#{id}")
    public void delete(int id);
}
