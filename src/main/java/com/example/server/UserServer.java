package com.example.server;

import com.example.entiy.User;
import com.example.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServer{
    @Autowired
    private UserMapper userMapper;

    public Integer insert(User user) {
        if(user.getUid()==null){//user没有uid,则表示新增
           return userMapper.insert(user);
        }else {// 否则为更新
            return userMapper.update(user);
        }
    }

    public List<User> findAll(){
       return userMapper.findAll();
    }

    public Integer deleteById(Integer uid) {
        return userMapper.deleteById(uid);
    }

    //查询总条数
    public Integer selectTotal() {
        return userMapper.selectTotal();
    }

    public List<User> selectPage(Integer pageNum, Integer pageSize,String name) {
        return userMapper.selectPage(pageNum,pageSize,name);
    }


//    public void setUserMapper(UserMapper userMapper) {
//        this.userMapper = userMapper;
//    }
}
