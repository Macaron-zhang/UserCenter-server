package com.up.day.day.server.spi;

public class JavaSPIServiceImpl implements SPIService{
    @Override
    public void sayHello() {
        System.out.println("Java Hello World! ");
    }
}
