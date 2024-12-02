package com.up.day.day.server;

import com.up.day.day.server.spi.SPIService;
import org.junit.jupiter.api.Test;

import java.util.ServiceLoader;

public class SPITest {

    @Test
    public void test() {
        // NOTE: SPI（Service Provider Interface），是JDK内置的一种 服务提供发现机制，可以用来启用框架扩展和替换组件
        // Java中SPI机制主要思想是将装配的控制权移到程序之外，在模块化设计中这个机制尤其重要，其核心思想就是 解耦。
        // 当服务的提供者提供了一种接口的实现之后，需要在classpath下的 META-INF/services/ 目录里创建一个以服务接口命名的文件，这个文件里的内容就是这个接口的具体的实现类。
        // 当其他的程序需要这个服务的时候，就可以通过查找这个jar包（一般都是以jar包做依赖）的META-INF/services/中的配置文件，配置文件中有接口的具体实现类名，可以根据这个类名进行加载实例化，就可以使用该服务了。
        // NOTE: JDK中查找服务的实现的工具类是：java.util.ServiceLoader; MYSQL 和 PGSQL 的驱动加载就是SPI的具体实践应用
        // MYSQL(mysql-connector-java-6.0.6.jar): META-INF/services/com.mysql.jdbc.Driver ====> com.mysql.cj.jdbc.Driver
        // PGSQL(postgresql-42.0.0.jar): META-INF/services/com.mysql.jdbc.Driver =====> org.postgresql.Driver
        ServiceLoader<SPIService> load = ServiceLoader.load(SPIService.class);
        load.forEach(SPIService::sayHello);
    }

    @Test
    public void unicodeTest() {
        String unicode = "\u0000";      // \ 代表转义符 表示后面跟着一个十六进制的数 u 代表 unicode 编码
        System.out.println("unicode = " + unicode);     // unicode =
        int hexCode = 0x0000;
        System.out.println("hexCode = " + hexCode);     // hexCode = 0
    }

    @Test
    public void unicodeTest2() {
        String unicode = "\u0032";      // \ 代表转义符 表示后面跟着一个十六进制的数 u 代表unicode 编码  查询 0032 对应的ASCII 即为字符2
        System.out.println("unicode = " + unicode);     // unicode = 2
        int hexCode = 0xface;
        System.out.println("hexCode = " + hexCode);     // hexCode = 64206
    }

}
