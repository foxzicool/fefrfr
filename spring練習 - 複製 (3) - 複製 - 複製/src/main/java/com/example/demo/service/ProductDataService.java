package com.example.demo.service;

import com.example.demo.model.ProductData;
import com.example.demo.repository.ProductDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductDataService {

    @Autowired
    private ProductDataRepository productDataRepository;

    public List<ProductData> getAllProductData() {
        return productDataRepository.findAll();
    }

    public ProductData createProductData(ProductData newProductData) {
        return productDataRepository.save(newProductData);
    }

    public ProductData updateProductData(Long id, ProductData updatedProductData) {
        ProductData existingProductData = productDataRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product not found with id " + id));
        existingProductData.setCategory(updatedProductData.getCategory());
        existingProductData.setTitle(updatedProductData.getTitle());
        existingProductData.setOrigin_price(updatedProductData.getOrigin_price());
        existingProductData.setPrice(updatedProductData.getPrice());
        existingProductData.setIs_enabled(updatedProductData.getIs_enabled());
        return productDataRepository.save(existingProductData);
    }

    public void deleteProductData(Long id) {
        productDataRepository.deleteById(id);
    }
}
