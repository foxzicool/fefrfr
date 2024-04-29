package com.example.demo.service;

import com.example.demo.model.TableData;
import com.example.demo.repository.TableDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableDataService {

    @Autowired
    private TableDataRepository tableDataRepository;

    public List<TableData> getAllTableData() {
        return tableDataRepository.findAll();
    }

    public TableData createTableData(TableData newTableData) {
        return tableDataRepository.save(newTableData);
    }
}
