package com.nazax.login_notion.service;

import com.nazax.login_notion.dto.UserDTO;
import com.nazax.login_notion.entity.User;
import com.nazax.login_notion.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserSerivice{

    private final UserRepository userRepository;


    // Busca
    @Transactional(readOnly = true)
    public List<UserDTO> findAll(){
        return userRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public UserDTO findById(Long id){
        return userRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("User not found!"));
    }

    // Criação
    public UserDTO createUser(UserDTO userDTO){
        User user = toEntity(userDTO);
        user = userRepository.save(user);
        return toDTO(user);
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
        return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getPassword());
    }

    private User toEntity(UserDTO dto){
        return new User(dto.getEmail(), dto.getName());
    }
}
