package com.target.myRetail.controller;

import com.target.myRetail.exception.ProductNotFoundException;
import com.target.myRetail.model.ProductItem;
import com.target.myRetail.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Products")
public class ProductController {

    ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping(value="/list")
    public ResponseEntity<List<ProductItem>> getProducts() throws IOException {
        return ResponseEntity.ok(productService.getProductList());
    }

    @PostMapping(value="/create")
    public ResponseEntity<String> createProduct(@RequestBody ProductItem item){
        productService.createProduct( item.getId(), item.getCurrent_price() );
        return ResponseEntity.ok("The product details are inserted");
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<String> updateProduct(@RequestBody ProductItem item) throws ProductNotFoundException {
        productService.updateProduct( item.getId(), item.getCurrent_price() );
        return ResponseEntity.ok("The product price is updated");
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<Optional<ProductItem>> getProductById(@PathVariable(value="id") int id) throws ProductNotFoundException, IOException {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PutMapping(value="/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(value="id") int id) throws ProductNotFoundException {
        productService.deleteProductById( id );
        return ResponseEntity.ok("The product is deleted");
    }
}
