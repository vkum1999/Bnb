package com.airbnb.payload;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppUserDto {
  @Id
    private Long id;

  private String name;

  private String email;

  private String username;

  private String password;

  private String message;
  private String role;

}