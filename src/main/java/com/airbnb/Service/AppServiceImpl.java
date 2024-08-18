package com.airbnb.Service;

import com.airbnb.Entity.AppUser;
import com.airbnb.Repository.AppUserRepository;

import com.airbnb.payload.AppUserDto;

import com.airbnb.payload.LoginDto;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppServiceImpl implements  AppService {
    private AppUserRepository appUserRepository;
    private  JWTService jwtService;

    public AppServiceImpl(PasswordEncoder passwordEncoder, AppUserRepository appUserRepository, JWTService jwtService) {
        this.appUserRepository = appUserRepository;
        this.jwtService = jwtService;
    }


    @Override
    public AppUserDto createUser(AppUserDto appUserDto) {
        String hashpw = BCrypt.hashpw(appUserDto.getPassword(), BCrypt.gensalt(10));
        appUserDto.setPassword(hashpw);
        AppUser entity=mapToEntity(appUserDto);
        AppUser save = appUserRepository.save(entity);
        AppUserDto dto = mapToDto(save);
        dto.setMessage("User Save SuccessFully");
        return dto;
    }

    @Override
    public String verifyLogin(LoginDto loginDto) {
        Optional<AppUser> opUser = appUserRepository.findByUsername(loginDto.getUsername());
        if (opUser.isPresent()) {
            AppUser appUser = opUser.get();
            if (BCrypt.checkpw(loginDto.getPassword(), appUser.getPassword())) {
                return jwtService.generateToken(appUser);
            }
        }
        return null;

    }

    AppUser mapToEntity(AppUserDto dto){
        AppUser entity = new AppUser();
       entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setRole(dto.getRole());
        return entity;
    }

    AppUserDto mapToDto(AppUser entity){
        AppUserDto dto = new AppUserDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setUsername(entity.getUsername());
        dto.setPassword(entity.getPassword());
        dto.setRole(entity.getRole());
        return  dto;
    }
}