package com.airbnb.Service;

import com.airbnb.payload.*;

import org.springframework.stereotype.Service;

@Service
public interface AppService {


  public   AppUserDto createUser(AppUserDto appUserDto);

  String verifyLogin(LoginDto loginDto);
}
