package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResMessageForm {
    private Integer code = 200;
    private String message = "success";
    private HashMap<String, String> detail = new HashMap<String, String>();
    private Object payload;

    public void addDetail(String key, String value) {
        this.detail.put(key, value);
    }
}
