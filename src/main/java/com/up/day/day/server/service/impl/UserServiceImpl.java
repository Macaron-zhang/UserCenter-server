package com.up.day.day.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.up.day.day.server.model.User;
import com.up.day.day.server.service.UserService;
import com.up.day.day.server.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* @author Admin
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2024-03-05 23:07:52
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

    @Resource
    private UserMapper userMapper;  // 也可以使用原生的Mapper来操作，但是最好还是使用包装好的Service中的方法

    /**
     * 加密的盐
     */
    public static final String SALT = "DAY";

    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) {
        // 1. 校验
        // 不能为空, 用户名不能少于4位，密码长度不能少于8位
        if(StringUtils.isAnyBlank(userAccount,userPassword,checkPassword)){
            return -1;
        }
        if(userAccount.length() < 4){
            return -1;
        }
        if(userPassword.length() < 8 || checkPassword.length() < 8){
            return -1;
        }

        // 2. 账户并不能包含特殊字符
        Matcher matcher = Pattern.compile("[^a-zA-Z0-9\\u4e00-\\u9fa5]").matcher(userAccount);
        if(matcher.find()){
            return -1;
        }
        // 3.密码和校验密码需要相等
        if(!userPassword.equals(checkPassword)){
            return -1;
        }

        // 4. 账户不能重复
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq(User.Fields.userAccount,userAccount);
        long count = this.count(userQueryWrapper);
        if(count > 0){
            return -1;
        }
        // 5. 对密码加密
        String saltPwd = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        // 6. 插入数据
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(saltPwd);
        boolean save = this.save(user);
        // PS: 这里增加判断是为了避免下面 user.getId() 返回的Long为null时，导致 Long -> long 的拆箱异常问题
        if(!save){
            return -1;
        }
        return user.getId();
    }
}




