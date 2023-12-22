package com.example.carshop.App.LoginAndRegistration.Registration;

import com.example.carshop.App.LoginAndRegistration.Address.Address;
import com.example.carshop.App.LoginAndRegistration.Address.AddressRepository;
import com.example.carshop.App.LoginAndRegistration.Person;
import com.example.carshop.App.LoginAndRegistration.PersonRepository;


import com.example.carshop.App.Shop.ShoppingCart;

import org.springframework.stereotype.Service;



@Service
public class RegistrationService {
    private final RegistrationMapper mapper;
    private final PersonRepository repository;
    private final AddressRepository addressRepository;




    public RegistrationService(RegistrationMapper mapper, PersonRepository repository,
                               AddressRepository addressRepository) {
        this.mapper = mapper;
        this.repository = repository;
        this.addressRepository = addressRepository;


    }
    public void register(RegistrationDto dto) {
        Person person = mapper.map(dto);
        Address address = person.getAddress();
        addressRepository.save(address);
        address.setPerson(person);


        ShoppingCart shoppingCart = person.getShoppingCart();
        shoppingCart.setPerson(person);

        Person savePerson = repository.save(person);


        mapper.map(savePerson);
    }
}
