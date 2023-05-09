package com.example.ssm_e2.services;

import com.example.ssm_e2.entities.User;
import com.example.ssm_e2.exception.UsernameAlreadyExistException;
import com.example.ssm_e2.model.security.SecurityUser;
import com.example.ssm_e2.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var u = repository.findByUsername(username);
        User user = u.orElseThrow(() -> new UsernameNotFoundException("Username not found !"));
        return new SecurityUser(user);
    }

    @Transactional
    public void createUser(User user) {
        var u = repository.findByUsername(user.getUsername());
        if (u.isPresent()) {
            throw new UsernameAlreadyExistException();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
    }


}
