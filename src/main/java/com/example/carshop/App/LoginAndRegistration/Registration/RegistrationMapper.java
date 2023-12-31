package com.example.carshop.App.LoginAndRegistration.Registration;

import com.example.carshop.App.LoginAndRegistration.Address.Address;
import com.example.carshop.App.LoginAndRegistration.Person;
import com.example.carshop.App.LoginAndRegistration.Role;
import com.example.carshop.App.LoginAndRegistration.RoleRepository;
import com.example.carshop.App.Shop.ShoppingCart;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class RegistrationMapper {
    private final BigDecimal bigDecimal;

    private final String USER = "User";
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;




    public RegistrationMapper(BigDecimal bigDecimal, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.bigDecimal = bigDecimal;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;

    }
    Person map(RegistrationDto dto){
        ShoppingCart shoppingCart = new ShoppingCart();
        Person person = new Person();
        Address address = new Address();

        person.setId(dto.getUserId());
        person.setFirstName(dto.getFirstName());
        person.setLastName(dto.getLastName());
        person.setEmail(dto.getEmail());
        String password = passwordEncoder.encode(dto.getPassword());
        person.setPassword(password);

        Role role = roleRepository.findByName(USER).orElseThrow();
        person.getRoles().add(role);

        address.setId(dto.getAddressId());
        address.setCity(dto.getCity());
        address.setZipCode(dto.getZipCode());
        address.setStreet(dto.getStreet());
        address.setHouseNumber(dto.getHouseNumber());

        person.setAddress(address);

        person.setShoppingCart(shoppingCart);
        return person;
    }

    RegistrationDto map(Person person){
        RegistrationDto dto = new RegistrationDto();

        dto.setUserId(person.getId());
        dto.setFirstName(person.getFirstName());
        dto.setLastName(person.getLastName());
        dto.setEmail(person.getEmail());
        dto.setAddressId(person.getAddress().getId());
        dto.setCity(person.getAddress().getCity());
        dto.setZipCode(person.getAddress().getZipCode());
        dto.setStreet(person.getAddress().getStreet());
        dto.setHouseNumber(person.getAddress().getHouseNumber());



        return dto;
     }
}
