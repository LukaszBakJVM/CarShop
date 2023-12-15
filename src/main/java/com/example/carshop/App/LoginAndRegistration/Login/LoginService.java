package com.example.carshop.App.LoginAndRegistration.Login;

import com.example.carshop.App.LoginAndRegistration.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {
    private final LoginMapper mapper;
    private final PersonRepository repository;

    public LoginService(LoginMapper mapper, PersonRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }
    Optional<LoginDto>findByEmail(String email){
        return repository.findByEmail(email)
                .map(mapper::map);
    }
}
