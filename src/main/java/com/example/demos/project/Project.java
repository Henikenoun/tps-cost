package com.example.demos.project;

import com.example.demos.demande.Demande;
import com.example.demos.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Project {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    @CreatedBy
    @Column(updatable = false)
    private String createdBy;

    private String description;

    @Column(nullable = false)
    private String type;

    @CreatedDate
    @Column(updatable = false)
    private Date createdAt;

    @Column(insertable = false)
    private Date end_date;

    @Column(nullable = false, updatable = false)
    private Integer budget;

    @Column(nullable = false)
    private String status = "created";

    @Column(nullable = false)
    private String location;

    @ManyToMany(mappedBy = "projects")
    private List<User> users;

    @OneToMany(mappedBy = "project")
    private List<Demande> demandes;
}
