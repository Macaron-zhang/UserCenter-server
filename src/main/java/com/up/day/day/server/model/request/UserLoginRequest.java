package com.up.day.day.server.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户注册请求体
 *
 * @author Admin
 */
@Data
public class UserLoginRequest implements Serializable {

    private String userAccount;
    private String userPassword;

}
