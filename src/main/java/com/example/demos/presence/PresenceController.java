package com.example.demos.presence;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/presence")
@RequiredArgsConstructor
public class PresenceController {

  private final PresenceService presenceService;

  @PostMapping("/project/{projectId}")
  public ResponseEntity<Integer> savePresence(@Valid @RequestBody PresenceRequest request,
      @PathVariable Integer projectId) {
    System.out.println(request);
    Integer savedPresenceId = presenceService.save(request, projectId);
    return ResponseEntity.ok(savedPresenceId);
  }

  // Endpoint pour obtenir les présences par date et projet
  @GetMapping("/by-date-and-project")
  public List<Presence> getPresenceByDateAndProject(@RequestParam("date") String dateStr,
      @RequestParam("projectId") Integer projectId) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate date = LocalDate.parse(dateStr, formatter);

    List<Presence> presences = presenceService.getPresenceByDateAndProject(projectId, date);
    System.out.println("Nombre de présences trouvées : " + presences.size());
    return presences;
  }

  @GetMapping("/project/{projectId}")
  public List<PresenceResponse> findAllPresences(
      @RequestParam(name = "page", defaultValue = "0", required = false) int page,
      @RequestParam(name = "size", defaultValue = "10", required = false) int size,
      @PathVariable Integer projectId,
      Authentication connectedUser) {
    System.out.println("PresenceController findAllPresences");
    return presenceService.findAllPresences(page, size, connectedUser, projectId);
  }

  // Endpoint pour mettre à jour une présence
  @PutMapping("/{presenceId}")
  public Integer updatePresence(@RequestBody PresenceRequest request, @PathVariable Integer presenceId) {
    return presenceService.updatePresence(request, presenceId);
  }
}
