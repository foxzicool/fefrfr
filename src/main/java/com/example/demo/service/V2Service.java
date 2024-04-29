// V2Service.java
package com.example.demo.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("2.0")
public class V2Service implements VersionedService {
    public void execute() {
        // 版本2.0 的特定邏輯
    }
}
