package com.nazax.login_notion.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;
    @Size(min = 3, max = 20)
    @NotEmpty(message = "Name é obrigatório")
    private String name;
    @Size(min = 3, max = 30)
    @NotEmpty(message = "Email é obrigatório")
    private String email;
    @Size(min = 8, max = 60, message = "A senha precisa ter no minimo 8 caracteres!")
    @NotEmpty(message = "Senha é obrigatória")
    private String password;

    public UserDTO(String email, String name, String password, Long id) {
    }
}