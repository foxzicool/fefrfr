package com.example.demo.repository;

import com.example.demo.model.TableData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableDataRepository extends JpaRepository<TableData, Long> {
}
