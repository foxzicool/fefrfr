package com.example.demo.controller;

import com.example.demo.model.ProductData;
import com.example.demo.service.ProductDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product-data")
public class ProductDataController {

    @Autowired
    private ProductDataService productDataService;

    @GetMapping
    public List<ProductData> getAllProductData() {
        return productDataService.getAllProductData();
    }

    @PostMapping
    public ProductData createProductData(@RequestBody ProductData newProductData) {
        return productDataService.createProductData(newProductData);
    }

    @PutMapping("/{id}")
    public ProductData updateProductData(@PathVariable Long id, @RequestBody ProductData updatedProductData) {
        return productDataService.updateProductData(id, updatedProductData);
    }

    @DeleteMapping("/{id}")
    public void deleteProductData(@PathVariable Long id) {
        productDataService.deleteProductData(id);
    }
}
