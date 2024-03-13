package com.up.day.day.server.service;

import java.security.DigestInputStream;
import java.time.LocalDateTime;

import com.up.day.day.server.model.User;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 用户服务测试，
 * ！！！建议与被测试的类在同样的包路径下！！！
 */
@SpringBootTest
class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    public void testAddUser() {
        User user = new User();
        // TODO 测试问题1：主键不从 1 开始自增？
        //  原因：
        //  1. mybatis-plus 3.x版本后，默认使用 IdType.ASSIGN_ID 类型进行初始id的生成，即使配置AUTO类型，初始id也由DefaultIdentifierGenerator生成，不会从1开始
        //  2. mybatis-plus 3.x版本后 强烈建议 主键生成策略使用 INPUT，使用用户自定义的 IKeyGenerator生成全局唯一主键
        //  解决方法：主键类型使用 IdType.NONE，且在数据库中对主键字段设置为 自增且 auto_increment=1, 此时框架的插入语句中才不会自动生成主键，且主键由数据库生成从 1 开始
//        user.setId(0L); 默认自动生成
        user.setUsername("咚咚锵4");
        user.setUserAccount("152022206");
        user.setAvatarUrl("");
        user.setGender(0);
        user.setUserPassword("123");
        user.setPhone("15312345678");
        user.setEmail("15312345678@163.com");
        boolean save = userService.save(user);
        System.out.println("userID = " + user.getId());
        Assertions.assertTrue(save);
    }

    @Test
    public void testPassword() {
        // 密码加解密算法
        // 单项加密，非对称加密
        String SALT = "DAY";
        String saltPwd = DigestUtils.md5DigestAsHex((SALT + "wakaka").getBytes());
        System.out.println("saltPwd = " + saltPwd);
        // saltPwd = c20f3e3fd4a3dbe6a4f7e6bdf22b57ee
    }

    @Test
    void userRegister() {
        // 用户注册测试
        // 1. 必填校验
        String userAccount = "wakaka";
        String userPwd = "";
        String userCheckPwd = "12345678";
        long result = userService.userRegister(userAccount, userPwd, userCheckPwd);
        Assertions.assertEquals(-1L, result);
        // 2. 账户长度校验、密码长度校验
        userAccount = "li";
        userPwd = "12345678";
        userCheckPwd = "12345678";
        result = userService.userRegister(userAccount, userPwd, userCheckPwd);
        Assertions.assertEquals(-1L, result);
        userAccount = "wakaka";
        userPwd = "123";
        userCheckPwd = "12345678";
        result = userService.userRegister(userAccount, userPwd, userCheckPwd);
        Assertions.assertEquals(-1L, result);
        // 3. 账户不能输入特殊字符校验
        userAccount = "%_aaaaa 123";
        userPwd = "12345678";
        userCheckPwd = "12345678";
        result = userService.userRegister(userAccount, userPwd, userCheckPwd);
        Assertions.assertEquals(-1L, result);
        // 4. 密码，校验密码一致校验
        userAccount = "wakaka";
        userPwd = "123456789";
        userCheckPwd = "12345678";
        result = userService.userRegister(userAccount, userPwd, userCheckPwd);
        Assertions.assertEquals(-1L, result);
        // 5. 重复账户校验
        userAccount = "152022206";
        userPwd = "12345678";
        userCheckPwd = "12345678";
        result = userService.userRegister(userAccount, userPwd, userCheckPwd);
        Assertions.assertEquals(-1L, result);
        // 6. 正常插入
        userAccount = "admin";
        userPwd = "12345678";
        userCheckPwd = "12345678";
        result = userService.userRegister(userAccount, userPwd, userCheckPwd);
        Assertions.assertTrue(result > 1);
    }
}