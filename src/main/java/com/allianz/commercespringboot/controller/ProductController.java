package com.allianz.commercespringboot.controller;

import com.allianz.commercespringboot.database.entity.ProductEntity;
import com.allianz.commercespringboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("add-product")
    public ResponseEntity<ProductEntity> createProduct(@RequestBody ProductEntity productEntity) {
        ProductEntity newProduct = productService.createProduct(productEntity.getName(), productEntity.getStockAmount(),
                productEntity.getId());
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @GetMapping("list-all")
    public ResponseEntity<List<ProductEntity>> listAllProducts() {
        List<ProductEntity> productList = productService.getAllProducts();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }


    @DeleteMapping("{productID}")
    public ResponseEntity<Long> deleteProduct(@PathVariable Long productID) {

        boolean exists = productService.exists(productID) != null;
        if (exists) {
            productService.deleteProductByID(productID);
            return new ResponseEntity<>(productID, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
