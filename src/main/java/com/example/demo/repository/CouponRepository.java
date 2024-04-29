package com.example.demo.repository;

import com.example.demo.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
    Coupon findByTitle(String title);

    // New Method
    @Query("SELECT c FROM Coupon c WHERE c.percent BETWEEN :minPercent AND :maxPercent")
    List<Coupon> findCouponsByPercentRange(@Param("minPercent") Integer minPercent,
            @Param("maxPercent") Integer maxPercent);
}
