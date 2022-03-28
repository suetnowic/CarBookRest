package com.viktorsuetnov.carbook.entity;

import com.viktorsuetnov.carbook.entity.enums.ERole;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(length = 120)
    private String password;

    @ElementCollection(targetClass = ERole.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<ERole> roles = new HashSet<>();

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "owner", orphanRemoval = true)
//    private List<Car> cars = new ArrayList<Car>();

    @Transient
    private Collection<? extends GrantedAuthority> authorities;
}
