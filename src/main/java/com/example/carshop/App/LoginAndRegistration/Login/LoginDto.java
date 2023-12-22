package com.example.carshop.App.LoginAndRegistration.Login;



import java.util.Set;

public record LoginDto(String email , String password , Set<String> role,long basketId) {
}
