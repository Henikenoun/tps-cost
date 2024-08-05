package com.example.demos.project;

import com.example.demos.user.User;
import com.example.demos.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectMapper projectMapper;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public Integer save(ProjectRequest request, Authentication connectedUser) {
        // Get the authenticated user
        // User user = (User) connectedUser.getPrincipal();
        System.out.println(connectedUser);
        // Map the request to a Project entity
        Project project = projectMapper.toProject(request);

        // Set the creator of the project
        project.setCreatedBy("asleeema");

        // Find all users by their IDs from the request
        List<User> users = userRepository.findAllById(request.userIds());
        /*
         * if (users == null || users.isEmpty()) {
         * throw new IllegalArgumentException("No users found with the provided IDs.");
         * }
         */

        // Print user information for debugging
        for (User u : users) {
            System.out.println("User ID: " + u.getId() + ", Name: " + u.getFirstname() + " " + u.getLastname());
        }

        // Set the users for the project
        project.setUsers(users);

        // Save the project
        Project savedProject = projectRepository.save(project);

        // Update users to include this project (if necessary)
        for (User u : users) {
            if (u.getProjects() == null) {
                u.setProjects(new ArrayList<>());
            }
            u.getProjects().add(savedProject);
            userRepository.save(u); // Save the user with the updated projects list
        }

        // Return the project ID
        return savedProject.getId();
    }

    public ProjectResponse findById(Integer projectId) {
        return projectRepository.findById(projectId)
                .map(projectMapper::toProjectResponse)
                .orElseThrow(() -> new EntityNotFoundException("No project found with the Id :: " + projectId));
    }

    public List<ProjectResponse> findAllProjects(int page, int size, Authentication connectedUser) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<Project> projects = projectRepository.findAll(pageable);
        return projects.stream()
                .map(projectMapper::toProjectResponse)
                .toList();
    }

    public Integer addUserToProject(Integer projectId, Integer userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<Project> projectOpt = projectRepository.findById(projectId);

        if (userOpt.isPresent() && projectOpt.isPresent()) {
            User user = userOpt.get();
            Project project = projectOpt.get();
            if (!project.getUsers().contains(user)) {
                project.getUsers().add(user);
                user.getProjects().add(project);

                projectRepository.save(project); // Save project to update the users
                System.out.println(project.getUsers().get(1).fullName());
                userRepository.save(user); // Save user to update the projects
            }
            return user.getId();

        } else {
            throw new IllegalArgumentException("User or Project not found");
        }
    }

    public Integer updateProject(ProjectRequest request, Integer projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Project not found with ID: " + projectId));
        projectMapper.updateProject(project, request);
        Project updatedProject = projectRepository.save(project);
        return updatedProject.getId();
    }

    public Integer updateProjectStatus(String status, Integer projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Project not found with ID: " + projectId));
        projectMapper.updateProjectStatus(project, status);
        Project updatedProject = projectRepository.save(project);
        return updatedProject.getId();
    }

    public void deleteProject(Integer projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Project not found with ID: " + projectId));
        projectRepository.delete(project);
    }
}
