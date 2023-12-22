package com.example.carshop.App.LoginAndRegistration.Login;

import com.example.carshop.App.LoginAndRegistration.Person;
import com.example.carshop.App.LoginAndRegistration.Role;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class LoginMapper {

    LoginDto map(Person person){
        return new LoginDto(person.getEmail(), person.getPassword(), person.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toSet()), person.getShoppingCart().getId());

    }


}
