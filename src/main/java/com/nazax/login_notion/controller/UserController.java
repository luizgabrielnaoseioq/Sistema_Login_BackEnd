package com.nazax.login_notion.controller;

import com.nazax.login_notion.dto.UserDTO;
import com.nazax.login_notion.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Busca
    @GetMapping("/findAll")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        try {
            List<UserDTO> users = userService.getAllUsers(); // Chama o método no serviço
            return ResponseEntity.status(HttpStatus.OK).body(users);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);  // Caso ocorra algum erro
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        try {
            UserDTO user = userService.getUserById(id); // Chama o método no serviço
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);  // Caso o usuário não seja encontrado
        }
    }

    // Criar
    @PostMapping("/criar")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO UserDTO) {
        logger.info("Recebido: {}", UserDTO);

        try {
            UserDTO savedUser = userService.createUser(UserDTO); // O service cuida da conversão e persistência

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

    // Atualizar
    @PutMapping("/update/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO) {
        try {
            UserDTO updatedUser = userService.updateUser(id, userDTO);  // Chama o método de atualização no serviço
            return ResponseEntity.status(HttpStatus.OK).body(updatedUser);  // Retorna o usuário atualizado
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);  // Caso não encontre o usuário
        }

    }

    // Deletar
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);  // Chama o método de deleção no serviço
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();  // Retorna status 204 (Sem Conteúdo) se a deleção for bem-sucedida
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);  // Caso o usuário não seja encontrado
        }
    }
}
