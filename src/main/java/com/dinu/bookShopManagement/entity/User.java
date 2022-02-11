package com.dinu.bookShopManagement.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;

    @Column(name = "user_name", length = 20, nullable = false)
    private String user_name;

    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column(name = "password", length = 20, nullable = false)
    private String password;

    @Column(name = "is_active", nullable = false)
    private Boolean is_active;

    @Column(name = "telephone", length = 20)
    private Character telephone;

    @Column(name = "user_type", length = 10, nullable = false)
    private Integer user_type;

    @Column(name = "dob", nullable = false)
    private Date dob;

    @Column(name = "join_date", nullable = false)
    private Date join_date;

    @Column(name = "is_adult", nullable = false)
    private Boolean is_adult;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "user_roles", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
            @JoinColumn(name = "role_id") })
    private Set<Role> roles = new HashSet<Role>();



}
