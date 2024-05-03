package com.example.demo.controller;

import com.example.demo.model.PageVisit;
import com.example.demo.repository.PageVisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api") // 基础路径设置为 /api
public class PageVisitController {
    @Autowired
    private PageVisitRepository repository;

    @PostMapping("/visit")
    public PageVisit logVisit(@RequestBody PageVisit visit) {
        return repository.save(visit);
    }
}
