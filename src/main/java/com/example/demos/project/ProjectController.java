package com.example.demos.project;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService service;

    @PostMapping
    public ResponseEntity<Integer> saveProject(
            @Valid @RequestBody ProjectRequest request ,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(service.save(request,connectedUser));
    }

    @GetMapping("{project-id}")
    public ResponseEntity<ProjectResponse> findProjectById(@PathVariable("project-id") Integer projectId) {
        return ResponseEntity.ok(service.findById(projectId));
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponse>> findAllProjects(
            @RequestParam(name="page" , defaultValue = "0" , required = false) int page,
            @RequestParam(name="size" , defaultValue = "10" , required = false) int size,
            Authentication connectedUser
    ){
        return ResponseEntity.ok(service.findAllProjects(page,size,connectedUser));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateProject(
            @Valid @RequestBody ProjectRequest request ,
            @PathVariable("id") Integer id
    ) {
        return ResponseEntity.ok(service.updateProject(request,id));
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<Integer> updateProjectStatus(
            @Valid @RequestBody String status ,
            @PathVariable("id") Integer id
    ) {
        return ResponseEntity.ok(service.updateProjectStatus(status,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable("id") Integer id) {
        service.deleteProject(id);
        return ResponseEntity.ok("Project deleted successfully");
    }
    @PutMapping("/{id_project}/{id_user}")
    public ResponseEntity<String> addUserToProject(@PathVariable("id_project") Integer id_project,@PathVariable("id_user") Integer id_user){
        service.addUserToProject(id_project,id_user);
        return ResponseEntity.ok("user added successfully");
    }


}
