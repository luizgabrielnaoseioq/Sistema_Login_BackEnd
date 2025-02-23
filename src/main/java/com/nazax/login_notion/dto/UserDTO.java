package com.nazax.login_notion.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @NotEmpty(message = "Name é obrigatório")
    private String name;
    @NotEmpty(message = "Email é obrigatório")
    private String email;
    @NotEmpty(message = "Senha é obrigatória")
    private String password;

    public UserDTO(Long id, String name, String email, String password) {
    }

    public UserDTO(String erroAoCriarUsuário) {
    }
}