package com.up.day.day.server;

import com.up.day.day.server.spi.SPIService;
import org.junit.jupiter.api.Test;

import java.util.ServiceLoader;

public class SPITest {

    @Test
    public void test(){
        ServiceLoader<SPIService> load = ServiceLoader.load(SPIService.class);
        load.forEach(SPIService :: sayHello);
    }

}
