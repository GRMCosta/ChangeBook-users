package com.projeto.changebookusers.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.dom4j.tree.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
public class ChangeBookUser extends AbstractEntity implements Serializable {
    @NotEmpty
    @JsonProperty("user_name")
    private String userName;
    @Id
    @Email
    private String email;
    @NotEmpty
    private String password;
}
