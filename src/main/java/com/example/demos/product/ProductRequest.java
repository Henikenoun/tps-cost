package com.example.demos.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRequest(
        @NotBlank(message ="107")
        String name,
        @NotBlank(message ="108")
        String type,
        @NotNull(message="109")
        Integer unit_price,
        @NotBlank(message ="110")
        String unity
) {
}
