package com.example.carshop.App.LoginAndRegistration;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;



public interface PersonRepository extends JpaRepository<Person,Long> {
    Optional<Person> findByEmail(String email);
  //  @Query("SELECT DISTINCT p FROM Person p JOIN p.roles r WHERE r.name = :roleName")
  //  Set<Person> findAllByRoleName(@Param("roleName") String roleName);

}
