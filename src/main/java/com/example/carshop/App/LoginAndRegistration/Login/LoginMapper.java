package com.example.carshop.App.LoginAndRegistration.Login;

import com.example.carshop.App.LoginAndRegistration.Person;
import org.springframework.stereotype.Service;

@Service
public class LoginMapper {
    LoginDto map(Person person){
        return new LoginDto(person.getEmail(), person.getPassword(), person.getRoles());

    }


}
