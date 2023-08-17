package com.scl.devnest.validation.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;

    private String password;

    private Integer age;

    @Temporal(TemporalType.DATE)
    private Date enrolledDate;

    private String role;
}
