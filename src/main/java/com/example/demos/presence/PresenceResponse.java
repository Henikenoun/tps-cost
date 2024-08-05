package com.example.demos.presence;

import com.example.demos.project.Project;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PresenceResponse {
    private Integer id;
    private String personalName;
    private String company;
    private String type;
    private String subtype;
    private String presenceDescription;
    private Integer costs ;
    private LocalDate createdAt;
}
