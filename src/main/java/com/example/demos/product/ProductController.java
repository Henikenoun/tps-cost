package com.example.demos.product;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @PostMapping
    public ResponseEntity<Integer> saveProduct(
            @Valid @RequestBody ProductRequest request
    ) {
        return ResponseEntity.ok(service.save(request));
    }

    @GetMapping("{product-id}")
    public ResponseEntity<ProductResponse> findProductById(@PathVariable("product-id") Integer productId) {
        return ResponseEntity.ok(service.findById(productId));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAllProducts(
            @RequestParam(name="page" , defaultValue = "0" , required = false) int page,
            @RequestParam(name="size" , defaultValue = "10" , required = false) int size,
            Authentication connectedUser
    ){
        return ResponseEntity.ok(service.findAllProducts(page,size,connectedUser));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateProduct(
            @Valid @RequestBody ProductRequest request ,
            @PathVariable("id") Integer id
    ) {
        return ResponseEntity.ok(service.updateProduct(request,id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Integer id) {
        service.deleteProduct(id);
        return ResponseEntity.ok("Project deleted successfully");
    }

}
