package com.rohit.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rohit.config.JWTUtils;
import com.rohit.entity.User;
import com.rohit.dto.LoginRequest;
import com.rohit.dto.RegisterRequest;
import com.rohit.services.UserService;
import com.rohit.util.APIResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    public static APIResponse resp;

    @Autowired
    private UserService userv;

    @Autowired
    private JWTUtils jwt;

    @PostMapping("/register")
    public ResponseEntity<APIResponse> save(@RequestBody RegisterRequest model) {
        User ob = userv.saveUser(model);
        if (ob != null) {
            resp = new APIResponse("User added successfully...", true, ob);
        } else {
            resp = new APIResponse("User could not be added...", false, null);
        }
        return ResponseEntity.ok(resp);
    }
//
//    @GetMapping("/custlist")
//    public ResponseEntity<APIResponse> list() {
//        List<User> list = userv.list();
//        resp = new APIResponse("List of Customers", true, list);
//        return ResponseEntity.ok(resp);
//    }
//    
    @GetMapping("/wrongauth")
    public ResponseEntity<APIResponse> wrongauth() {
//        List<Users> list = userv.list();
        resp = new APIResponse("UnAuthorised User", true, wrongauth());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resp);
    }

    @PostMapping("/login")
    public ResponseEntity<APIResponse> login(@RequestBody LoginRequest model) {
        User ob = userv.checkLogin(model);
        if (ob != null) {
            String token = JWTUtils.generateToken(model.getEmail());
            resp = new APIResponse("Welcome to app", true, token);
        } else {
            resp = new APIResponse("Invalid Credentials....", false, null);
        }
        return ResponseEntity.ok(resp);
    }
}
