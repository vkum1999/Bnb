package com.airbnb.controller;

import com.airbnb.Repository.AppUserRepository;
import com.airbnb.Service.AppService;
import com.airbnb.exception.UserExists;
import com.airbnb.payload.AppUserDto;
import com.airbnb.payload.JWTToken;
import com.airbnb.payload.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AppService appService;
    private final AppUserRepository appUserRepository;

    @Autowired
    public AuthController(AppService appService, AppUserRepository appUserRepository) {
        this.appService = appService;
        this.appUserRepository = appUserRepository;
    }

    @PostMapping("/createuser")
    public ResponseEntity<AppUserDto> addUser(@RequestBody AppUserDto appUserDto) {

        if (appUserRepository.findByEmail(appUserDto.getEmail()).isPresent()) {
            throw new UserExists("Email is already in use");
        }


        if (appUserRepository.findByUsername(appUserDto.getUsername()).isPresent()) {
            throw new UserExists("Username is already in use");
        }


        appUserDto.setRole("ROLE_USER");


        AppUserDto createdUser = appService.createUser(appUserDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PostMapping("/createPropertyowner")
    public ResponseEntity<AppUserDto> creatpropertyOwner(@RequestBody AppUserDto appUserDto) {

        if (appUserRepository.findByEmail(appUserDto.getEmail()).isPresent()) {
            throw new UserExists("Email is already in use");
        }


        if (appUserRepository.findByUsername(appUserDto.getUsername()).isPresent()) {
            throw new UserExists("Username is already in use");
        }


        appUserDto.setRole("ROLE_OWNER");


        AppUserDto createdUser = appService.createUser(appUserDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
    @PostMapping("/createPropertymanger")
    public ResponseEntity<AppUserDto> creatpropertymanger(@RequestBody AppUserDto appUserDto) {

        if (appUserRepository.findByEmail(appUserDto.getEmail()).isPresent()) {
            throw new UserExists("Email is already in use");
        }


        if (appUserRepository.findByUsername(appUserDto.getUsername()).isPresent()) {
            throw new UserExists("Username is already in use");
        }


        appUserDto.setRole("ROLE_MANGER");


        AppUserDto createdUser = appService.createUser(appUserDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
   }

    @PostMapping("/login")
    public ResponseEntity<?> signIn(@RequestBody LoginDto loginDto) {
        String token = appService.verifyLogin(loginDto);
        if (token != null) {
            JWTToken jwtToken = new JWTToken();
            jwtToken.setTokenType("JWT"); // Set the token type
            jwtToken.setToken(token);     // Set the actual token

            return new ResponseEntity<>(jwtToken, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid username/password", HttpStatus.UNAUTHORIZED);
        }
    }



}

