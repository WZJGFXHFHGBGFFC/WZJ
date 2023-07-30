package com.example.entiy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer uid;
    private  String name;
    private Integer age;
    private String email;
    @JsonIgnore //忽略这个字段
    private Integer is_deleted;
}
