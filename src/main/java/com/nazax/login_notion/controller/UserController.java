package com.nazax.login_notion.controller;

import com.nazax.login_notion.dto.UserDTO;
import com.nazax.login_notion.service.UserSerivice;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/")
@RequiredArgsConstructor
public class UserController {

    private final UserSerivice userSerivice;

    // Busca
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(Long id){
        List<UserDTO> user = userSerivice.findAll();
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userSerivice.findById(id));
    }

    // Criar
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO){
        UserDTO saveUser = userSerivice.createUser(userDTO);
        System.out.println("Recebido: " + saveUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveUser);
    }
}
