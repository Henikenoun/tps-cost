package com.example.demos.product;

import com.example.demos.project.Project;
import com.example.demos.project.ProjectRequest;
import com.example.demos.project.ProjectResponse;
import com.example.demos.user.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public Integer save(ProductRequest request) {
        // Get the authenticated user
        // User user = (User) connectedUser.getPrincipal();

        Product product = productMapper.toProduct(request);
        Product savedProduct = productRepository.save(product);
        return savedProduct.getId();
    }

    public ProductResponse findById(Integer productId) {
        return productRepository.findById(productId)
                .map(productMapper::toProductResponse)
                .orElseThrow(() -> new EntityNotFoundException("No Product found with the Id :: " + productId));
    }

    public List<ProductResponse> findAllProducts(int page, int size, Authentication connectedUser) {
        System.out.println(connectedUser.getPrincipal());
        Pageable pageable = PageRequest.of(page, size, Sort.by("type").descending());
        Page<Product> products = productRepository.findAll(pageable);
        return products
                .stream()
                .map(productMapper::toProductResponse)
                .toList();
    }

    public Integer updateProduct(ProductRequest request, Integer productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + productId));
        productMapper.updateProduct(product, request);
        Product updatedProject = productRepository.save(product);
        return updatedProject.getId();
    }

    public void deleteProduct(Integer productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + productId));
        productRepository.delete(product);
    }
}
