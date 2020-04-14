package com.projeto.changebookusers.domain.data;

import com.projeto.changebookusers.config.Messages;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequest {

    @Email(message = Messages.EMAIL_IS_INVALID)
    @NotBlank(message = Messages.EMAIL_IS_REQUIRED)
    private String email;

    @NotBlank(message = Messages.PASSWORD_IS_REQUIRED)
    private String password;

}
