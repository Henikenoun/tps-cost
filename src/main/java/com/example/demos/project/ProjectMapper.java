package com.example.demos.project;

import org.springframework.stereotype.Service;

@Service
public class ProjectMapper {

    public Project toProject(ProjectRequest request){
        return Project.builder()
                .name(request.name())
                .description(request.description())
                .createdAt(request.created_At())
                .type(request.type())
                .budget(request.budget())
                .status(request.status())
                .location(request.location())
                .build();
    }

    public ProjectResponse toProjectResponse(Project project) {
       return ProjectResponse.builder()
               .id(project.getId())
               .name(project.getName())
               .description(project.getDescription())
               .created_At(project.getCreatedAt())
               .type(project.getType())
               .budget(project.getBudget())
               .status(project.getStatus())
               .location(project.getLocation())
               .build();
    }

    public void updateProject(Project project, ProjectRequest request) {
        project.setName(request.name());
        project.setDescription(request.description());
        project.setLocation(request.location());
        project.setEnd_date(request.endDate());
        project.setStatus(request.status());

    }
    public void updateProjectStatus(Project project, String status) {
        project.setStatus(status);
    }
}
