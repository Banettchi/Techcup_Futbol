package edu.eci.dosw.tech_cup.service;

import edu.eci.dosw.tech_cup.entity.User;
import edu.eci.dosw.tech_cup.mapper.UserMapper;
import edu.eci.dosw.tech_cup.model.UserModel;
import edu.eci.dosw.tech_cup.model.enums.Role;
import edu.eci.dosw.tech_cup.repository.RoleRepository;
import edu.eci.dosw.tech_cup.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, UserMapper userMapper, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.roleRepository = roleRepository;
    }

    public List<UserModel> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toModel)
                .collect(Collectors.toList());
    }

    public UserModel findById(Long id) {
        return userMapper.toModel(
                userRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Usuario no encontrado")));
    }

    public UserModel create(UserModel user) {
        user.setRole(Role.PLAYER);
        user.setAvailable(true);
        return userMapper.toModel(userRepository.save(userMapper.toEntity(user)));
    }

    public UserModel update(Long id, UserModel updated) {
        userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        updated.setId(id);
        return userMapper.toModel(userRepository.save(userMapper.toEntity(updated)));
    }


    public UserModel assignRole(Long id, Role role) {
        UserModel user = findById(id);
        user.setRole(role);
        return userMapper.toModel(userRepository.save(userMapper.toEntity(user)));
    }


    public UserModel assignRoleEntity(Long userId, Long roleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        edu.eci.dosw.tech_cup.entity.Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        user.getRoles().add(role);
        return userMapper.toModel(userRepository.save(user));
    }
}