package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyService.class);

    @Value("${app.version}")
    private String appVersion;

    @Autowired
    private Environment env; // 注入 Environment

    @Autowired
    private VersionedService versionedService;

    public void someMethod() {
        // 手動獲取 app.version
        String appVersionManual = env.getProperty("app.version");
        LOGGER.info("Manually retrieved app.version: {}", appVersionManual);

        // 打印 app.version 到 log
        LOGGER.info("Current app.version is: {}", appVersion);

        // 這部分是原來的邏輯，根據 app.version 進行操作
        if ("1.0".equals(appVersion)) {
            // Do something specific to version 1.0
        }

        // 這部分是新添加的，會根據當前的 app.version 執行相應版本的邏輯
        versionedService.execute();
    }
}
