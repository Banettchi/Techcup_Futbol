package edu.eci.dosw.tech_cup.service;

import edu.eci.dosw.tech_cup.entity.User;
import edu.eci.dosw.tech_cup.mapper.UserMapper;
import edu.eci.dosw.tech_cup.model.UserModel;
import edu.eci.dosw.tech_cup.model.enums.Role;
import edu.eci.dosw.tech_cup.repository.RoleRepository;
import edu.eci.dosw.tech_cup.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, UserMapper userMapper, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.roleRepository = roleRepository;
    }

    /**
     * Preguntas LAB
     *
     * a. El objetivo del método loadUserByUsername es cargar la información de un usuario
     *    a partir de su identificador (en este caso el email) para que Spring Security
     *    pueda validar sus credenciales durante la autenticación.
     *
     * b. UserDetails es una interfaz de Spring Security que representa al usuario autenticado.
     *    Contiene información como username, password y authorities (roles/permisos).
     *
     * c. SimpleGrantedAuthority es una implementación de GrantedAuthority que representa
     *    un permiso o rol asignado al usuario. Spring Security la usa para controlar
     *    el acceso a recursos según el rol.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.debug("Buscando usuario por email: {}", email);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    log.warn("Usuario no encontrado con email: {}", email);
                    return new UsernameNotFoundException("Usuario no encontrado: " + email);
                });
        log.info("Usuario encontrado: {} con rol: {}", email, user.getRole());
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority(user.getRole().name()))
        );
    }

    public List<UserModel> findAll() {
        log.info("Consultando todos los usuarios");
        List<UserModel> users = userRepository.findAll()
                .stream()
                .map(userMapper::toModel)
                .collect(Collectors.toList());
        log.info("Se encontraron {} usuarios", users.size());
        return users;
    }

    public UserModel findById(Long id) {
        log.debug("Buscando usuario con ID: {}", id);
        return userMapper.toModel(
                userRepository.findById(id)
                        .orElseThrow(() -> {
                            log.error("Usuario con ID {} no encontrado", id);
                            return new RuntimeException("Usuario no encontrado");
                        }));
    }

    public UserModel create(UserModel user) {
        log.info("Creando nuevo usuario con email: {}", user.getEmail());
        user.setRole(Role.PLAYER);
        user.setAvailable(true);
        UserModel created = userMapper.toModel(userRepository.save(userMapper.toEntity(user)));
        log.info("Usuario creado exitosamente con ID: {} y rol: PLAYER", created.getId());
        return created;
    }

    public UserModel update(Long id, UserModel updated) {
        log.info("Actualizando usuario con ID: {}", id);
        userRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("No se puede actualizar: usuario con ID {} no existe", id);
                    return new RuntimeException("Usuario no encontrado");
                });
        updated.setId(id);
        UserModel result = userMapper.toModel(userRepository.save(userMapper.toEntity(updated)));
        log.info("Usuario con ID {} actualizado exitosamente", id);
        return result;
    }


    public UserModel assignRole(Long id, Role role) {
        log.info("Asignando rol {} al usuario con ID: {}", role, id);
        UserModel user = findById(id);
        user.setRole(role);
        UserModel result = userMapper.toModel(userRepository.save(userMapper.toEntity(user)));
        log.info("Rol {} asignado exitosamente al usuario con ID: {}", role, id);
        return result;
    }


    public UserModel assignRoleEntity(Long userId, Long roleId) {
        log.info("Asignando rol entity (roleId={}) al usuario con ID: {}", roleId, userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    log.error("Usuario con ID {} no encontrado para asignar rol", userId);
                    return new RuntimeException("Usuario no encontrado");
                });
        edu.eci.dosw.tech_cup.entity.Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> {
                    log.error("Rol con ID {} no encontrado", roleId);
                    return new RuntimeException("Rol no encontrado");
                });
        user.getRoles().add(role);
        UserModel result = userMapper.toModel(userRepository.save(user));
        log.info("Rol entity '{}' asignado al usuario ID: {} via ManyToMany", role.getName(), userId);
        return result;
    }
}