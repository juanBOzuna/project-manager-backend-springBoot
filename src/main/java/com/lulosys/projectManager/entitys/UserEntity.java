package com.lulosys.projectManager.entitys;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(name = "dni", nullable = false, length = 150)
    private String dni;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Column(name = "lastname", nullable = false, length = 150)
    private String lastname;

    @Column(name = "address", nullable = false, length = 150)
    private String address;

    @Column(name = "number_phone", nullable = false, length = 150)
    private String number_phone;

    @Column(name = "email", nullable = false, length = 150)
    private String email;

    @Column(name = "hiring_date", nullable = true, length = 150)
    private Timestamp created_at;

    @Column(name = "role", nullable = false, length = 150)
    private String role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return name;
    }

    public void setNombre(String name) {
        this.name = name;
    }

}