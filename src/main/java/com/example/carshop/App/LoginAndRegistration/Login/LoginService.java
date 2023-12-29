package com.example.carshop.App.LoginAndRegistration.Login;

import com.example.carshop.App.LoginAndRegistration.Person;
import com.example.carshop.App.LoginAndRegistration.PersonRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class LoginService {
    private final LoginMapper mapper;
    private final PersonRepository repository;
    private final PasswordEncoder passwordEncoder;

    public LoginService(LoginMapper mapper, PersonRepository repository, PasswordEncoder passwordEncoder) {
        this.mapper = mapper;
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }
    Optional<LoginDto>findByEmail(String email){
        return repository.findByEmail(email)
                .map(mapper::map);
    }
    @Transactional
    void changePassword(String password){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Person person = repository.findByEmail(email).orElseThrow();
        String encode = passwordEncoder.encode(password);
        person.setPassword(encode);
    }
}
