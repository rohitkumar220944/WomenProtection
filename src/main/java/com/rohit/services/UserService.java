package com.rohit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rohit.entity.User;
import com.rohit.dto.LoginRequest;
import com.rohit.dto.RegisterRequest;
import com.rohit.repositories.UserRepositoty;

@Service
public class UserService implements UserDetailsService{

    @Autowired
    private UserRepositoty urep;

    public User saveUser(RegisterRequest model) {
        try {
            User ob = new User(model);
            ob.setRole("ROLE_USER");
            return urep.save(ob);
        } catch (Exception e) {
            return null;
        }
    }

    public List<User> list() {
        return urep.findByRole("ROLE_CUSTOMER");
    }

    public User checkLogin(LoginRequest model) {
        User ob = urep.findByEmailAndPassword(model.getEmail(), model.getPassword());
        if(ob != null)
        	return ob;
        else 
        	return null;
    }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User ob = urep.findByEmail(username);
		if(ob != null)
        	return ob;
        else 
        	return null;
	}
}
