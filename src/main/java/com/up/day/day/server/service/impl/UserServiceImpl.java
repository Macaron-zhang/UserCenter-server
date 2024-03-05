package com.up.day.day.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.up.day.day.server.model.User;
import com.up.day.day.server.service.UserService;
import com.up.day.day.server.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author Admin
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2024-03-05 23:07:52
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




