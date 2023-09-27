package com.example.salonbella.startup;

import com.example.salonbella.entity.UserEntity;
import com.example.salonbella.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {

    private final UserService userService;

    @Autowired
    public CommandLineAppStartupRunner(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        UserEntity userEntity = new UserEntity();
        userEntity.setName("Mladen");
        userEntity.setSurname("Jovanovski");
        userEntity.setEmail("mjovanovski04@gmail.com");
        userEntity.setPassword(passwordEncoder.encode("admin"));
        userEntity.setUsername("admin");
        userEntity.setRole("ADMIN");
        userEntity.setValid(true);
        userEntity.setNumber("072312134");
        userService.registerAdmin(userEntity);

        UserEntity userEntity2 = new UserEntity();
        userEntity2.setName("Mladen");
        userEntity2.setSurname("Jovanovski");
        userEntity2.setEmail("m@gmail.com");
        userEntity2.setPassword(passwordEncoder.encode("maky"));
        userEntity2.setUsername("maky");
        userEntity2.setRole("USER");
        userEntity2.setValid(true);
        userEntity2.setNumber("072312134");
        userService.registerAdmin(userEntity2);


    }
}
