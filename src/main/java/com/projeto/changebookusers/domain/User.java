package com.projeto.changebookusers.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.projeto.changebookusers.config.Messages;
import lombok.*;
import org.dom4j.tree.AbstractEntity;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
public class User extends AbstractEntity implements Serializable {

    @JsonProperty("user_name")
    @NotBlank(message = Messages.NAME_IS_REQUIRED)
    private String userName;

    @Id
    @CPF(message = Messages.CPF_IS_INVALID)
    @NotBlank(message = Messages.CPF_IS_REQUIRED)
    private String cpf;

    @NotBlank(message = Messages.CITY_IS_REQUIRED)
    private String city;

    @Column(unique = true)
    @Email(message = Messages.EMAIL_IS_INVALID)
    @NotBlank(message = Messages.EMAIL_IS_REQUIRED)
    private String email;

    @NotBlank(message = Messages.PASSWORD_IS_REQUIRED)
    private String password;

    @NotBlank(message = Messages.PHONE_IS_REQUIRED)
    private String phone;
}
