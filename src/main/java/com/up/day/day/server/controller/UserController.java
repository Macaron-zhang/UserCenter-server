package com.up.day.day.server.controller;

import com.up.day.day.server.constant.UserConstant;
import com.up.day.day.server.model.User;
import com.up.day.day.server.model.request.UserLoginRequest;
import com.up.day.day.server.model.request.UserRegisterRequest;
import com.up.day.day.server.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController// 适用于编写RestFul风格的API,返回值格式默认为JSON类型
@RequestMapping("/user")
public class UserController {

    // NOTE : controller 层倾向于对请求参数本身的校验,不涉及业务逻辑本身(越少越好)
    // NOTE : service 层是对业务逻辑的校验(有可能被controller以外的类调用)

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public Long userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (null == userRegisterRequest) {
            return null;
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            return null;
        }
        return userService.userRegister(userAccount, userPassword, checkPassword);
    }

    @PostMapping("/login")
    public User userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (null == userLoginRequest) {
            return null;
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            return null;
        }
        return userService.userLogin(userAccount, userPassword, request);
    }

    // NOTE : 必须鉴权
    @GetMapping("/search")
    public List<User> searchUsers(@RequestParam String userAccount, HttpServletRequest request) {
        // 鉴权,仅管理员可以查询
        if (!isAdmin(request)) {
            return new ArrayList<>();
        }
        if (StringUtils.isBlank(userAccount)) {
            return null;
        }
        return userService.searchUsers(userAccount);
    }

    @PostMapping("/delete")
    public boolean deleteUser(@RequestBody long id, HttpServletRequest request) {
        // 鉴权,仅管理员可以查询
        if (!isAdmin(request)) {
            return false;
        }
        if (id <= 0) {
            return false;
        }
        return userService.removeById(id);
    }

    /**
     * 是否为管理员权限
     *
     * @param request
     * @return
     */
    private boolean isAdmin(HttpServletRequest request) {
        Object userObj = request.getSession().getAttribute(UserConstant.USER_LOGIN_STATE);
        User user = (User) userObj;
        return null != user && user.getUserRole() == UserConstant.ADMIN_ROLE;
    }


}
