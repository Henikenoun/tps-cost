package com.example.demos.product;

import com.example.demos.project.Project;
import com.example.demos.project.ProjectRequest;
import com.example.demos.project.ProjectResponse;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public Product toProduct(ProductRequest request){
        return Product.builder()
                .name(request.name())
                .type(request.type())
                .unit_price(request.unit_price())
                .unity(request.unity())
                .build();
    }

    public ProductResponse toProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .type(product.getType())
                .unit_price(product.getUnit_price())
                .unity(product.getUnity())
                .build();
    }

    public void updateProduct(Product product, ProductRequest request) {
        product.setName(request.name());
        product.setType(request.type());
        product.setUnit_price(request.unit_price());
        product.setUnity(request.unity());
    }
}
