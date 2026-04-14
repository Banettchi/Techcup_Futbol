package edu.eci.dosw.tech_cup.service;

import edu.eci.dosw.tech_cup.entity.User;
import edu.eci.dosw.tech_cup.mapper.UserMapper;
import edu.eci.dosw.tech_cup.model.UserModel;
import edu.eci.dosw.tech_cup.model.enums.Role;
import edu.eci.dosw.tech_cup.repository.RoleRepository;
import edu.eci.dosw.tech_cup.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

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
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + email));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority(user.getRole().name()))
        );
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