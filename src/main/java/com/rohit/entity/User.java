package com.rohit.entity;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.rohit.dto.RegisterRequest;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String phone;

    private String role ; // Default role

	public User(String name, String email, String password, String phone, String role) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.role = role;
	}

	public User(RegisterRequest model) {
		this.name=model.getName();
		this.email=model.getEmail();
		this.password=model.getPassword();
		this.phone=model.getPhone();
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority sga=new SimpleGrantedAuthority(role);
		return Arrays.asList(sga);
	}
    @Override
	public String getUsername() {
		return email;
	}
}
