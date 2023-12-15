package com.example.carshop.App.LoginAndRegistration.Login;

import com.example.carshop.App.LoginAndRegistration.Role;

import java.util.Set;

public record LoginDto(String email , String password , Set<Role> role) {
}
