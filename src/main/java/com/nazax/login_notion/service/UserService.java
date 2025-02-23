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
    @Transactional(readOnly = true)
    public UserDTO updateUser(Long id, UserDTO userDTO){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found!"));

        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());

        user = userRepository.save(user);

        return toDTO(user);
    }

    // Deletar
    @Transactional
    public void deleteUser(Long id){
        if (!userRepository.existsById(id)){
            throw new EntityNotFoundException("User not found!");
        }
        userRepository.deleteById(id);
    }

    // Auxiliares de conversão
    private UserDTO toDTO(User user){
        return new  UserDTO(user.getId(), user.getEmail(), user.getName(), user.getPassword());
    }
    private User toEntity(UserDTO dto){
        return new User(dto.getEmail(), dto.getName(), dto.getPassword()); // Supondo que o User tenha password
    }
}
