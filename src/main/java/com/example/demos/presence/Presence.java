package com.example.demos.presence;

import com.example.demos.project.Project;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Presence {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String personalName;

    // tps ou etap
    @Column(nullable = false)
    private String company;

    // stuf - consultant ....
    private String type;

    private String subtype;

    private String presenceDescription;


    private Integer costs ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "project_id")
    private Project project;

    @CreatedBy
    @Column(updatable = false)
    private String createdBy;
    @CreatedDate
    @Column(updatable = false )
    private LocalDate createdAt;

}
