package com.example.carshop.App.LoginAndRegistration.Login;


import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final LoginService loginService;

    public CustomUserDetailsService(LoginService loginService) {
        this.loginService = loginService;

    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return loginService.findByEmail(username)
                .map(this::createUserDetails).orElseThrow(() ->
                        new UsernameNotFoundException("Bledny email " + username));
    }


    private UserDetails createUserDetails(LoginDto login) {
        return User.builder()
                .username(login.email())
                .password(login.password())
                .roles(login.role().toArray(String[]::new))
                .build();
    }

}
