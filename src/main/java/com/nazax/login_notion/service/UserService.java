package com.nazax.login_notion.service;

import com.nazax.login_notion.dto.UserDTO;
import com.nazax.login_notion.entity.User;
import com.nazax.login_notion.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService{

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Busca
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();  // Buscar todos os usuários

        if (users == null || users.isEmpty()) {
            return Collections.emptyList();  // Retorna uma lista vazia, nunca nula
        }

        // Converte a lista de usuários para DTOs
        return users.stream()
                .map(user -> new UserDTO(user.getEmail(), user.getName(), user.getPassword()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserDTO getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();  // Pega a entidade User
            return new UserDTO(user.getEmail(), user.getName(), user.getPassword());  // Retorna o DTO
        } else {
            // Caso não encontre o usuário
            throw new RuntimeException("Usuário não encontrado");  // Pode lançar uma exceção ou retornar um valor nulo
        }
    }

    // Criação
    public UserDTO createUser(UserDTO userDTO) {
        // Converter o DTO para a entidade User
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());

        // Persistir no banco de dados
        user = userRepository.save(user);

        // Após salvar, converter a entidade de volta para o DTO
        return new UserDTO(user.getEmail(), user.getName(), user.getPassword());
    }


    // Atualização
    @Transactional
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        System.out.println("Atualizando usuário com ID: " + id);  // Log para depuração
        Optional<User> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent()) {
            throw new EntityNotFoundException("Usuário não encontrado");
        }

        User user = userOptional.get();
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());

        System.out.println("Dados antes da atualização: " + user);  // Log antes de salvar
        user = userRepository.save(user);
        System.out.println("Dados após a atualização: " + user);  // Log após salvar

        return new UserDTO(user.getEmail(), user.getName(), user.getPassword());
    }


    // Deletar
    @Transactional
    public void deleteUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent()) {
            throw new RuntimeException("Usuário não encontrado");  // Lança uma exceção se o usuário não existir
        }

        userRepository.deleteById(id);  // Deleta o usuário com o ID especificado
    }
}
