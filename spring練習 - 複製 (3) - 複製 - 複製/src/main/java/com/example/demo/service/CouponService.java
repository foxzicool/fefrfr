package com.example.demo.service;

import com.example.demo.dto.CouponDTO;
import com.example.demo.model.Coupon;
import com.example.demo.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class CouponService {

    @Autowired
    private CouponRepository couponRepository;

    @Value("${app.version}")
    private String appVersion;

    public List<CouponDTO> getAllCouponDTOs() {
        return couponRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public CouponDTO createCoupon(CouponDTO newCouponDTO) {
        Coupon newCoupon = convertToEntity(newCouponDTO);
        Coupon savedCoupon = couponRepository.save(newCoupon);
        return convertToDTO(savedCoupon);
    }

    public CouponDTO updateCoupon(Long id, CouponDTO updatedCouponDTO) {
        Coupon existingCoupon = couponRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Coupon not found with id " + id));
        existingCoupon.setTitle(updatedCouponDTO.getTitle());
        existingCoupon.setPercent(updatedCouponDTO.getPercent());
        existingCoupon.setDueDate(updatedCouponDTO.getDueDate());
        existingCoupon.setIsEnabled(updatedCouponDTO.getIsEnabled());
        Coupon updatedCoupon = couponRepository.save(existingCoupon);
        return convertToDTO(updatedCoupon);
    }

    public void deleteCoupon(Long id) {
        couponRepository.deleteById(id);
    }

    public CouponDTO findByTitle(String title) {
        Coupon foundCoupon = couponRepository.findByTitle(title);
        return foundCoupon != null ? convertToDTO(foundCoupon) : null;
    }

    public void enableAllCoupons() {
        List<Coupon> allCoupons = couponRepository.findAll();
        allCoupons.forEach(coupon -> coupon.setIsEnabled(true));
        couponRepository.saveAll(allCoupons);
    }

    public List<CouponDTO> getCouponsByPercentRange(Integer minPercent, Integer maxPercent) {
        if ("2.0".equals(appVersion)) {
            List<Coupon> coupons = couponRepository.findCouponsByPercentRange(minPercent, maxPercent);
            return coupons.stream().map(this::convertToDTO).collect(Collectors.toList());
        } else {
            throw new UnsupportedOperationException("This feature is only available in version 2.0");
        }
    }

    private Coupon convertToEntity(CouponDTO couponDTO) {
        Coupon coupon = new Coupon();
        coupon.setId(couponDTO.getId());
        coupon.setTitle(couponDTO.getTitle());
        coupon.setPercent(couponDTO.getPercent());
        coupon.setDueDate(couponDTO.getDueDate());
        coupon.setIsEnabled(couponDTO.getIsEnabled());
        return coupon;
    }

    private CouponDTO convertToDTO(Coupon coupon) {
        CouponDTO dto = new CouponDTO();
        dto.setId(coupon.getId());
        dto.setTitle(coupon.getTitle());
        dto.setPercent(coupon.getPercent());
        dto.setDueDate(coupon.getDueDate());
        dto.setIsEnabled(coupon.getIsEnabled());
        return dto;
    }
}
