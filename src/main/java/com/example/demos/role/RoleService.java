package com.example.demos.role;

import com.example.demos.user.User;
import com.example.demos.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    public Role getRoleById(Integer id) {
        return roleRepository.findById(id).orElseThrow(() -> new RoleNotFoundException(id));
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role updateRole(Integer id, Role updatedRole) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new RoleNotFoundException(id));
        role.setName(updatedRole.getName());
        return roleRepository.save(role);
    }


}
