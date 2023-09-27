package com.example.salonbella.service;

import com.example.salonbella.controller.register.RegisterRequest;
import com.example.salonbella.entity.ConfirmationTokenEntity;
import com.example.salonbella.entity.UserEntity;
import com.example.salonbella.repository.UserRepository;
import com.example.salonbella.service.mail.ConfirmationTokenService;
import com.example.salonbella.service.mail.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository repository;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailService emailService;

    @Autowired
    public UserService(UserRepository repository, ConfirmationTokenService confirmationTokenService, EmailService emailService) {
        this.repository = repository;
        this.confirmationTokenService = confirmationTokenService;
        this.emailService = emailService;
    }

    public void registerNewUser(RegisterRequest registerRequest) {
        PasswordEncoder encoder = new BCryptPasswordEncoder(10);
        UserEntity userEntity = new UserEntity();
        userEntity.setName(registerRequest.getName());
        userEntity.setSurname(registerRequest.getSurname());
        userEntity.setNumber(registerRequest.getNumber());
        userEntity.setEmail(registerRequest.getEmail());
        userEntity.setUsername(registerRequest.getUsername());
        userEntity.setPassword(encoder.encode(registerRequest.getPassword()));

        String token = UUID.randomUUID().toString();
        ConfirmationTokenEntity confirmationTokenEntity = new ConfirmationTokenEntity(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(30),
                userEntity
        );
        repository.save(userEntity);
        confirmationTokenService.saveConfirmationToken(confirmationTokenEntity);
        emailService.send(registerRequest.getEmail(), registerRequest.getName(), token);
    }

    public void registerAdmin(UserEntity userEntity) {
        repository.save(userEntity);
    }

    public boolean checkUsernameExists(String username) {
        List<UserEntity> userEntities = repository.findAll();
        for (UserEntity u : userEntities) {
            if (u.getUsername().equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkEmailExists(String email) {
        List<UserEntity> userEntities = repository.findAll();
        for (UserEntity u : userEntities) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        final UserEntity user = repository.findByUsername(username);
        if (user == null || !user.isValid())
            throw new UsernameNotFoundException(username);
        return User.withUsername(user.getUsername()).password(user.getPassword()).roles(user.getRole()).build();
    }
}
