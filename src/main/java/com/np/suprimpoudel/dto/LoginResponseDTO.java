package com.np.suprimpoudel.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponseDTO {
    private UserDTO user;
    private String accessToken;
}

