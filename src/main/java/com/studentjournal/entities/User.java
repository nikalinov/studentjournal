package com.studentjournal.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Entity
@Table(name="user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "u_id")
    private UUID id;

    @Column(name = "u_username")
    private String username;

    @Column(name = "u_password")
    private String password;
}
