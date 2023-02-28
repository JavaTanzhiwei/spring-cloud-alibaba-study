package com.tzw.system;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;
/**
 * 系统模块 启动类
 *
 * @author: tanzhiwei
 * @date: 2023/2/28  10:33
 */
@EnableDubbo
@SpringBootApplication
public class TzwSystemApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(TzwSystemApplication.class);
        application.setApplicationStartup(new BufferingApplicationStartup(2048));
        application.run(args);
        System.out.println("系统模块-------->>>>启动成功");
    }
}
