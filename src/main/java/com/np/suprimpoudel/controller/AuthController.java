package com.np.suprimpoudel.controller;

import com.np.suprimpoudel.dto.Base;
import com.np.suprimpoudel.dto.LoginRequestDTO;
import com.np.suprimpoudel.dto.LoginResponseDTO;
import com.np.suprimpoudel.dto.UserDTO;
import com.np.suprimpoudel.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService service;

    @Autowired
    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/sign-up")
    private Base<UserDTO> signUp(@RequestBody @Valid UserDTO user) {
        var result = service.signUp(user);
        return new Base<>(result);
    }

    @PostMapping("/login")
    private Base<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO loginRequestDTO) {
        var result = service.login(loginRequestDTO);
        return new Base<>(result);
    }
}

