package com.nazax.login_notion.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;
    @NotEmpty(message = "Name é obrigatório")
    private String name;
    @NotEmpty(message = "Email é obrigatório")
    private String email;
    @NotEmpty(message = "Senha é obrigatória")
    private String password;

    public UserDTO(String email, String name, String password, Long id) {
    }
}