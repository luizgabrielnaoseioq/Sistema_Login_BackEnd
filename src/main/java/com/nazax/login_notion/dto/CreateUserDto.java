package com.nazax.login_notion.dto;

import com.nazax.login_notion.enums.RoleName;

public record CreateUserDto(

        String email,
        String password,
        RoleName role

) {
}