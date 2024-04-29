package com.example.demo.model;

import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "coupons", schema = "spring_pra")
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "percent")
    private Integer percent;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "due_date", nullable = true)
    private LocalDate dueDate;

    @Column(name = "is_enabled")
    private Boolean isEnabled;

    // Getters
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getPercent() {
        return percent;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public Boolean getIsEnabled() {
        return isEnabled;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }
}
