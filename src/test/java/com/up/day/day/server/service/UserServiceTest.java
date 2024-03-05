package com.up.day.day.server.service;
import java.time.LocalDateTime;

import com.up.day.day.server.model.User;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

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
        //  2. mybatis-plus 3.x版本后 强烈建议 主键生成策略必须使用 INPUT，使用用户自定义的 IKeyGenerator生成全局唯一主键
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

}