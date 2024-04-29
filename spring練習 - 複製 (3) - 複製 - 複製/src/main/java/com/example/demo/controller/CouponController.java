package com.example.demo.controller;

import com.example.demo.dto.CouponDTO;
import com.example.demo.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.support.RequestContextUtils;

@RestController
@RequestMapping("/api/coupons")
public class CouponController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CouponController.class);

    @Autowired
    private CouponService couponService;

    @Autowired
    private MessageSource messageSource;

    @Value("${app.version}")
    private String appVersion;

    @GetMapping
    public List<CouponDTO> getAllCoupons() {
        return couponService.getAllCouponDTOs();
    }

    @PostMapping
    public ResponseEntity<String> createCoupon(@RequestBody CouponDTO newCouponDTO, HttpServletRequest request) {
        Locale locale = RequestContextUtils.getLocale(request);
        try {
            CouponDTO createdCouponDTO = couponService.createCoupon(newCouponDTO);
            String successMessage = messageSource.getMessage("coupon.create.success", null, locale);
            LOGGER.debug("Exiting createCoupon method with success");
            return ResponseEntity.ok(successMessage);
        } catch (Exception e) {
            String failMessage = messageSource.getMessage("coupon.create.fail", null, locale);
            LOGGER.error("Error occurred while creating coupon", e);
            return ResponseEntity.badRequest().body(failMessage);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CouponDTO> updateCoupon(@PathVariable Long id, @RequestBody CouponDTO updatedCouponDTO) {
        try {
            CouponDTO updatedCoupon = couponService.updateCoupon(id, updatedCouponDTO);
            return ResponseEntity.ok(updatedCoupon);
        } catch (Exception e) {
            LOGGER.error("Error occurred while updating coupon", e);
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteCoupon(@PathVariable Long id) {
        couponService.deleteCoupon(id);
    }

    @GetMapping("/validate/{code}")
    public ResponseEntity<CouponDTO> validateCoupon(@PathVariable String code) {
        CouponDTO foundCouponDTO = couponService.findByTitle(code);
        if (foundCouponDTO != null && foundCouponDTO.getIsEnabled()) {
            return ResponseEntity.ok(foundCouponDTO);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/enable-all")
    public ResponseEntity<String> enableAllCoupons() {
        try {
            couponService.enableAllCoupons();
            return ResponseEntity.ok("All coupons enabled.");
        } catch (Exception e) {
            LOGGER.error("Error occurred while enabling all coupons", e);
            return ResponseEntity.badRequest().body("Failed to enable all coupons");
        }
    }

    @GetMapping("/percent-range")
    public ResponseEntity<List<CouponDTO>> getCouponsByPercentRange(@RequestParam Integer minPercent,
            @RequestParam Integer maxPercent) {
        try {
            if ("2.0".equals(appVersion)) {
                List<CouponDTO> coupons = couponService.getCouponsByPercentRange(minPercent, maxPercent);
                return ResponseEntity.ok(coupons);
            } else {
                return ResponseEntity.status(403).body(null);
            }
        } catch (UnsupportedOperationException e) {
            return ResponseEntity.status(403).body(null);
        }
    }
}
