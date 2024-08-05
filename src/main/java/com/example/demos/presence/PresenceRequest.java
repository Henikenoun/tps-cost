package com.example.demos.presence;

import jakarta.validation.constraints.NotBlank;

public record PresenceRequest(
        @NotBlank(message ="112")
        String personalName,
        @NotBlank(message ="113")
        String type,
        @NotBlank(message ="114")
        String company,
        String subtype,
        String presenceDescription,
        Integer costs
) {
}
