package com.example.demo.controller;

import com.example.demo.model.TableData;
import com.example.demo.service.TableDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/table-data")
public class TableDataController {

    @Autowired
    private TableDataService tableDataService;

    @GetMapping
    public List<TableData> getAllTableData() {
        return tableDataService.getAllTableData();
    }

    @PostMapping
    public TableData createTableData(@RequestBody TableData newTableData) {
        return tableDataService.createTableData(newTableData);
    }
}
