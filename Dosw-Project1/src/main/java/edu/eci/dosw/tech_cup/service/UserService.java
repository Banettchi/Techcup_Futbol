package edu.eci.dosw.tech_cup.service;

import edu.eci.dosw.tech_cup.mapper.UserMapper;
import edu.eci.dosw.tech_cup.model.UserModel;
import edu.eci.dosw.tech_cup.model.enums.Role;
import edu.eci.dosw.tech_cup.repository.UserRepository;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserModel> findAll() {
        log.debug("Obteniendo todos los usuarios");
        return userRepository.findAll()
                .stream()
                .map(userMapper::toModel)
                .collect(Collectors.toList());
    }

    public UserModel findById(Long id) {
        log.debug("Buscando usuario con id: {}", id);
        return userRepository.findById(id)
                .map(userMapper::toModel)
                .orElse(null);
    }

    public UserModel create(UserModel user) {
        log.debug("Creando usuario: {}", user.getEmail());
        user.setRole(Role.PLAYER);
        user.setAvailable(true);
        UserModel saved = userMapper.toModel(userRepository.save(userMapper.toEntity(user)));
        log.info("Usuario creado con id: {}", saved.getId());
        return saved;
    }

    public UserModel update(Long id, UserModel updated) {
        log.debug("Actualizando usuario con id: {}", id);
        return userRepository.findById(id).map(existing -> {
            updated.setId(id);
            updated.setRole(existing.getRole());
            UserModel saved = userMapper.toModel(userRepository.save(userMapper.toEntity(updated)));
            log.info("Usuario actualizado con id: {}", id);
            return saved;
        }).orElse(null);
    }

    public UserModel assignRole(Long id, Role role) {
        log.debug("Asignando rol {} a usuario con id: {}", role, id);
        return userRepository.findById(id).map(entity -> {
            entity.setRole(role);
            UserModel saved = userMapper.toModel(userRepository.save(entity));
            log.info("Rol {} asignado a usuario con id: {}", role, id);
            return saved;
        }).orElse(null);
    }

    public UserModel deactivate(Long id) {
        log.debug("Inactivando usuario con id: {}", id);
        return userRepository.findById(id).map(entity -> {
            entity.setAvailable(false);
            UserModel saved = userMapper.toModel(userRepository.save(entity));
            log.info("Usuario inactivado con id: {}", id);
            return saved;
        }).orElse(null);
    }
}