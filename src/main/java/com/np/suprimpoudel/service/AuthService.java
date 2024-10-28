package com.np.suprimpoudel.service;

import com.np.suprimpoudel.dto.LoginRequestDTO;
import com.np.suprimpoudel.dto.LoginResponseDTO;
import com.np.suprimpoudel.dto.UserDTO;
import com.np.suprimpoudel.mapper.UserMapper;
import com.np.suprimpoudel.repository.UserRepository;
import com.np.suprimpoudel.util.helper.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final JwtHelper jwtHelper;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthService(JwtHelper jwtHelper, UserMapper userMapper, PasswordEncoder passwordEncoder, UserRepository userRepository, AuthenticationManager authenticationManager) {
        this.jwtHelper = jwtHelper;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
    }

    public UserDTO signUp(UserDTO userDTO) {
        var user = userMapper.toUser(userDTO.getUsername(), passwordEncoder.encode(userDTO.getPassword()));
        userRepository.saveAndFlush(user);

        return userMapper.toUserDTO(user);
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword()));

        var user = userRepository.findByUsername(loginRequestDTO.getUsername()).orElseThrow(() -> new RuntimeException("Username not found"));

        var jwtToken = jwtHelper.generateToken(user);
        var userDTO = userMapper.toUserDTO(user);

        return LoginResponseDTO.builder().user(userDTO).accessToken(jwtToken).build();
    }
}
