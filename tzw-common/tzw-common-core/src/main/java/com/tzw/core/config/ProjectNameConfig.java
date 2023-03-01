package com.tzw.core.config;

import com.tzw.core.utils.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * 解决订阅者列表 应用名unknown问题
 *
 * @author: tanzhiwei
 * @date: 2023/3/1  10:34
 */
@Configuration
public class ProjectNameConfig implements EnvironmentAware {
    @Value("${spring.application.name}")
    private String applicationName;

    @Override
    public void setEnvironment(Environment environment) {
        if (StringUtils.isBlank(System.getProperty("project.name"))) {
            System.setProperty("project.name", applicationName);
        }
    }
}
