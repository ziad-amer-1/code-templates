package com.template.authentication;

import jakarta.servlet.http.HttpServletResponse;

public interface AuthenticationService {
    AuthenticationResponseDTO login(AuthenticationRequestDTO request, HttpServletResponse response) throws Exception;
}
