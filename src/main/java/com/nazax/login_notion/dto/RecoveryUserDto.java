package com.nazax.login_notion.dto;

import com.nazax.login_notion.entity.Role;

import java.util.List;

public record RecoveryUserDto(

        Long id,
        String email,
        List<Role> roles

) {
}
