// V1Service.java
package com.example.demo.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("1.0")
public class V1Service implements VersionedService {
    public void execute() {
        // 版本1.0 的特定邏輯
    }
}
