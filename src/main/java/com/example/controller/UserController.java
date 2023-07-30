package com.example.controller;

import com.example.entiy.User;
import com.example.server.UserServer;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin  //解决请求跨域问题
@RestController
@RequestMapping("/user")//统一给接口加前缀
public class UserController {

    @Autowired
    private UserServer userServer;

    //新增和修改
    @PostMapping
    public Integer insert(@RequestBody User user) {//@RequestBody把前端的json对象转成User对象
        return userServer.insert(user);
    }

    //查询所有数据
    @GetMapping
    public List<User> findAll(){
        return userServer.findAll();
    }

    @DeleteMapping("/{uid}")
    public Integer delete(@PathVariable Integer uid){
        return userServer.deleteById(uid);
    }

    //分页查询；通过@RequestParam注解可以将请求参数映射到方法的参数上，并进行相应的数据绑定和转换。
    //(@RequestParam("paramName") String value)，可以不写括号里面的就会默认为参数名称的映射
    @GetMapping("/page")
    public Map<String,Object> findPage(@RequestParam Integer pageNum,
                                       @RequestParam Integer pageSize,
                                       @RequestParam String name){
        pageNum=(pageNum-1)*pageSize;
        Integer total = userServer.selectTotal();
        List<User> data = userServer.selectPage(pageNum, pageSize,name);
        HashMap<String, Object> res = new HashMap<>();
        res.put("total",total);
        res.put("data",data);
        userServer.selectTotal();
        return res;
    }

}
