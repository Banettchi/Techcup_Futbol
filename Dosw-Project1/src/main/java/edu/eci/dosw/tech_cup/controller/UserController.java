package edu.eci.dosw.tech_cup.controller;

import edu.eci.dosw.tech_cup.model.UserModel;
import edu.eci.dosw.tech_cup.model.enums.Role;
import edu.eci.dosw.tech_cup.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Usuarios", description = "Endpoints para la gestión de usuarios")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los usuarios")
    public ResponseEntity<List<UserModel>> getAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener usuario por ID")
    public ResponseEntity<UserModel> getById(@PathVariable Long id) {
        UserModel user = userService.findById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    @Operation(summary = "Crear usuario", description = "Se crea con rol PLAYER por defecto")
    public ResponseEntity<UserModel> create(@RequestBody UserModel user) {
        UserModel created = userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar usuario")
    public ResponseEntity<UserModel> update(@PathVariable Long id, @RequestBody UserModel user) {
        UserModel updated = userService.update(id, user);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/{id}/role")
    @Operation(summary = "Asignar rol", description = "Solo el ADMIN puede asignar roles")
    public ResponseEntity<UserModel> assignRole(@PathVariable Long id,
                                           @RequestParam Role role,
                                           @RequestParam Role requesterRole) {
        if (requesterRole != Role.ADMIN) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        UserModel updated = userService.assignRole(id, role);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/{id}/deactivate")
    @Operation(summary = "Inactivar usuario")
    public ResponseEntity<UserModel> deactivate(@PathVariable Long id) {
        UserModel updated = userService.deactivate(id);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}