package edu.eci.dosw.tech_cup.service;

import edu.eci.dosw.tech_cup.entity.User;
import edu.eci.dosw.tech_cup.mapper.UserMapper;
import edu.eci.dosw.tech_cup.model.UserModel;
import edu.eci.dosw.tech_cup.model.enums.Role;
import edu.eci.dosw.tech_cup.repository.RoleRepository;
import edu.eci.dosw.tech_cup.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private UserService userService;

    private User user;
    private UserModel userModel;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setEmail("test@mail.com");
        user.setRole(Role.PLAYER);

        userModel = new UserModel();
        userModel.setId(1L);
        userModel.setEmail("test@mail.com");
        userModel.setRole(Role.PLAYER);
    }

    // TEST 1: findAll retorna lista de usuarios
    @Test
    void findAll_retornaListaDeUsuarios() {
        when(userRepository.findAll()).thenReturn(List.of(user));
        when(userMapper.toModel(user)).thenReturn(userModel);

        List<UserModel> result = userService.findAll();

        assertEquals(1, result.size());
        assertEquals("test@mail.com", result.get(0).getEmail());
    }

    // TEST 2: findById usuario existente retorna modelo
    @Test
    void findById_usuarioExiste_retornaModelo() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userMapper.toModel(user)).thenReturn(userModel);

        UserModel result = userService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    // TEST 3: findById usuario no existe lanza excepción
    @Test
    void findById_usuarioNoExiste_lanzaExcepcion() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> userService.findById(99L));

        assertEquals("Usuario no encontrado", ex.getMessage());
    }

    // TEST 4: create asigna rol PLAYER y available true automáticamente
    @Test
    void create_nuevoUsuario_asignaRolPlayerYAvailableTrue() {
        when(userMapper.toEntity(any())).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.toModel(user)).thenReturn(userModel);

        UserModel input = new UserModel();
        userService.create(input);

        assertEquals(Role.PLAYER, input.getRole());
        assertTrue(input.isAvailable());
    }
}