package com.nazax.login_notion.service;

import com.nazax.login_notion.dto.UserDTO;
import com.nazax.login_notion.entity.User;
import com.nazax.login_notion.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService{

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserDTO createUser(UserDTO userDTO) {
        User user = userDTOToUser(userDTO);
        User UserSaved = userRepository.save(user);
        return userToUserDTO(UserSaved);
    }

    // Deletar
    public void delete (Long id){
        userRepository.deleteById(id);
    }

    // Metodos auxiliares
    public UserDTO userToUserDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        dto.setPassword(user.getPassword());
        return dto;
    }

    public User userDTOToUser(UserDTO dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setPassword(dto.getPassword());
        return user;
    }

//    private UserDTO toDTO(User user) {
//        return new UserDTO(user.getEmail(), user.getName(), user.getPassword(), user.getId());
//    }
//
//    private User toUser(UserDTO userDTO) {
//        return new User(userDTO.getEmail(), userDTO.getName(), userDTO.getPassword());
//    }
}
