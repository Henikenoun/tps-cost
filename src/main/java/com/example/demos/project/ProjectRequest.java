package com.example.demos.project;

import com.example.demos.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.List;

public record ProjectRequest(
  Integer id,
        @NotBlank(message ="101")
        String name,

        Date endDate,

        String description,

        @NotBlank(message ="102")
        String type,

        @NotNull(message ="103")
        Date created_At,

        @NotNull(message="104")
        Integer budget,

        @NotBlank(message ="105")
        String status,

        @NotBlank(message ="106")
        String location,

        List<Integer> userIds
) {
}
