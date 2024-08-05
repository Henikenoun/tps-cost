package com.example.demos.presence;


import com.example.demos.project.Project;
import com.example.demos.project.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PresenceService {

    private final PresenceRepository presenceRepository;
    private final ProjectRepository projectRepository;
    private final PresenceMapper presenceMapper;

    public Integer save(PresenceRequest request,Integer projectId) {
        // Get the authenticated user
        // User user = (User) connectedUser.getPrincipal();

        Project project = projectRepository.findById(projectId).orElseThrow(() -> new EntityNotFoundException("project Not found"));

        Presence presence = presenceMapper.toPresence(request);
        presence.setProject(project);
        Presence savedPresence = presenceRepository.save(presence);
        return savedPresence.getId();
    }

    public List<Presence> getPresenceByDateAndProject(Integer projectId, LocalDate date) {
        return presenceRepository.findAllByProjectIdAndDate(projectId, date);
    }

    public List<PresenceResponse> findAllPresences(int page, int size, Authentication connectedUser, Integer projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));

        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<Presence> presences = presenceRepository.findAllByProject(pageable, project);

        return presences
                .stream()
                .map(presenceMapper::toPresenceResponse)
                .toList();
    }
    public Integer updatePresence(PresenceRequest request, Integer presenceId){
        Presence presence = presenceRepository.findById(presenceId)
                .orElseThrow(() -> new EntityNotFoundException("Presence not found with ID: " + presenceId));
        presenceMapper.updatePresence(presence, request);
        Presence updatedPresence = presenceRepository.save(presence);
        return updatedPresence.getId();
    }
}
