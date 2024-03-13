package com.up.day.day.server.service;

import com.up.day.day.server.model.User;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

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

    /**
     * 用户登录
     *
     * @param userAccount  用户账号
     * @param userPassword 用户密码
     * @return 返回用户脱敏信息
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 根据账号查询用户
     *
     * @param userAccount
     * @return
     */
    List<User> searchUsers(String userAccount);

    /**
     * 用户脱敏
     *
     * @param oriUser
     * @return
     */
    User getSafetyUser(User oriUser);
}
