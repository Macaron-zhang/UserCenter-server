package com.up.day.day.server.service;

import com.up.day.day.server.model.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 用户服务
 *
 * @author Admin
 * @description 针对表【user(用户表)】的数据库操作Service
 * @createDate 2024-03-05 23:07:52
 */
public interface UserService extends IService<User> {

    /**
     * 用户注册
     *
     * @param userAccount   用户账号
     * @param userPassword  用户密码
     * @param checkPassword 校验密码
     * @return 新用户id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);

}
