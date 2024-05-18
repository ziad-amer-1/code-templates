package com.template.authentication;

import com.template.security.entity.Role;
import lombok.Builder;

@Builder
public record AuthenticationResponseDTO(String token, Role role, String username) {
}
