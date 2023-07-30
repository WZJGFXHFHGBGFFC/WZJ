package com.example.mapper;

import com.example.entiy.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user")
    List<User> findAll();

    @Insert("INSERT into user(uid,name,age,email,is_deleted) VALUES (#{uid},#{name},#{age},#{email},#{is_deleted})")
    int insert(User user);

    Integer update(User user);

    //@Param("uid")让uid和sql里面的#{uid}中的uid对应绑定（一般用于mybatis）
    @Delete("delete from user where uid=#{uid} ")
    Integer deleteById(@Param("uid") Integer uid);

    @Select("SELECT COUNT(*) FROM user")
    Integer selectTotal();

    @Select("SELECT * FROM user WHERE name like concat('%',#{name},'%') LIMIT #{pageNum},#{pageSize}")
    List<User> selectPage(Integer pageNum, Integer pageSize,String name);
}
