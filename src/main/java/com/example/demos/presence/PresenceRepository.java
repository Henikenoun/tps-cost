package com.example.demos.presence;

import com.example.demos.project.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PresenceRepository extends JpaRepository<Presence,Integer> {
    Page<Presence> findAllByProject(Pageable pageable, Project project);

    @Query("SELECT p FROM Presence p WHERE p.project.id = :projectId AND p.createdAt = :date")
    List<Presence> findAllByProjectIdAndDate(Integer projectId, LocalDate date);
}
