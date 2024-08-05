package com.example.demos.user;

import com.example.demos.role.Role;
import com.example.demos.role.RoleNotFoundException;
import com.example.demos.role.RoleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final TokenRepository tokenRepository;

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(Integer id, User updatedUser) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        if (updatedUser.getFirstname() != null) {
            user.setFirstname(updatedUser.getFirstname());
        }
        if (updatedUser.getLastname() != null) {
            user.setLastname(updatedUser.getLastname());
        }
        if (updatedUser.getDateOfBirth() != null) {
            user.setDateOfBirth(updatedUser.getDateOfBirth());
        }
        if (updatedUser.getPhone() != null) {
            user.setPhone(updatedUser.getPhone());
        }

        return userRepository.save(user);
    }

    @Transactional
    public User changeUserRole(Integer userId, Integer roleId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Role> roleOptional = roleRepository.findById(roleId);

        if (userOptional.isPresent() && roleOptional.isPresent()) {
            User user = userOptional.get();
            Role role = roleOptional.get();
            user.getRoles().clear(); // Remove all existing roles
            user.getRoles().add(role); // Add the new role
            return userRepository.save(user);
        }

        throw new IllegalArgumentException("User or Role not found");
    }

    @Transactional
    public void deleteUser(Integer userId) {

        tokenRepository.deleteByUserId(userId);

        userRepository.deleteById(userId);
    }

}
