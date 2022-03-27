package com.viktorsuetnov.carbook.entity;

import com.viktorsuetnov.carbook.entity.enums.ERole;
import org.springframework.security.core.GrantedAuthority;

import java.util.*;

public class User {

    private Long id;
    private String username;
    private String email;
    private String password;

    private Set<ERole> roles = new HashSet<ERole>();
    private List<Car> cars = new ArrayList<Car>();

    private Collection<? extends GrantedAuthority> authorities;
}
