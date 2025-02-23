package com.nazax.login_notion.controller;

import com.nazax.login_notion.dto.UserDTO;
import com.nazax.login_notion.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/user/")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Busca
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(Long id){
        List<UserDTO> user = userService.findAll();
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.findById(id));
    }

    // Criar
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        logger.info("Recebido: {}", userDTO);

        try {
            UserDTO savedUser = userService.createUser(userDTO); // O service cuida da conversão e persistência

            if (savedUser == null) {
                logger.warn("Usuário não foi criado.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

            logger.info("Usuário criado com sucesso: {}", savedUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        } catch (Exception e) {
            logger.error("Erro ao criar usuário", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
