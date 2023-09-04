package com.itranks.gateway.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.itranks.gateway.dto.UserDto;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "users")
@Entity
public class User {

    @Id
    private Long id;
    private String name;
    @Column(name = "user_name")
    private String userName;
    private String title;
    @Column(name = "jop_name")
    private String jopName;
    @Column(unique=true)
    private String email;
    private String password;
    private String role;
    @Lob
    private byte[] IMAGE;


    public UserDto mapUsertoUserDto() {
        return new UserDto(id, email, name,password,role);
    }
}
