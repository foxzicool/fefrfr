package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "table_data")
public class TableData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String time;
    private String type;
    private String result;
    private String description;

    // Getters
    public Long getId() {
        return id;
    }

    public String getTime() {
        return time;
    }

    public String getType() {
        return type;
    }

    public String getResult() {
        return result;
    }

    public String getDescription() {
        return description;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
