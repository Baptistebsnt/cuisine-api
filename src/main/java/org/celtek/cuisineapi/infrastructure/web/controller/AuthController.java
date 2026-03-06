package org.celtek.cuisineapi.infrastructure.web.controller;

import lombok.RequiredArgsConstructor;
import org.celtek.cuisine.api.AuthApi;
import org.celtek.cuisineapi.application.AuthService;
import org.celtek.cuisine.dto.AuthResponseDTO;
import org.celtek.cuisine.dto.UserLoginDTO;
import org.celtek.cuisine.dto.UserRegistrationDTO;
import org.celtek.cuisineapi.infrastructure.web.mapper.UserMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApi {

    private final AuthService authService;
    private final UserMapper mapper;

    @Override
    public ResponseEntity<Void> registerUser(UserRegistrationDTO dto) {
        var user = mapper.toDomain(dto);
        authService.register(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<AuthResponseDTO> loginUser(UserLoginDTO dto) {
        String token = authService.login(dto.getEmail(), dto.getPassword());

        // On prépare la réponse
        AuthResponseDTO response = new AuthResponseDTO();
        response.setToken(token);

        return ResponseEntity.ok(response);
    }
}
