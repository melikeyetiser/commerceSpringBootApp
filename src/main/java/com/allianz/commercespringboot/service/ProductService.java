package com.allianz.commercespringboot.service;

import com.allianz.commercespringboot.database.entity.ProductEntity;
import com.allianz.commercespringboot.database.repository.BaseRepository;
import com.allianz.commercespringboot.database.repository.ProductRepository;
import com.allianz.commercespringboot.util.dbutil.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public ProductEntity createProduct(String name, int stockAmount, Long id) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(name);
        productEntity.setStockAmount(stockAmount);
        productEntity.setId(id);

        productRepository.save(productEntity);
        return productEntity;
    }

    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    public List<ProductEntity> getProductsByNameContaining(String key) {
        return productRepository.findAllByNameContainingIgnoreCase(key);
    }

    public BigDecimal calculateTaxFreePrice(BigDecimal taxAddedPrice, double taxRate) {
        return taxAddedPrice.multiply(new BigDecimal(100)).divide(new BigDecimal(100 + taxRate));
    }

    public BigDecimal calculatePriceWithTax(BigDecimal taxFreePrice, double taxRate) {
        return taxFreePrice.multiply(new BigDecimal(100 + taxRate)).divide(new BigDecimal(100));
    }

    public void deleteProductByID(Long id){
        productRepository.deleteById(id);
    }

    public ProductEntity exists(Long id){
        return productRepository.findByID(id);
    }
}
