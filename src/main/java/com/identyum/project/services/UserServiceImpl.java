package com.identyum.project.services;

import com.identyum.project.domain.Role;
import com.identyum.project.domain.User;
import com.identyum.project.dto.UserDTO;
import com.identyum.project.repositories.RoleRepository;
import com.identyum.project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserServiceImpl {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserRepository userRepository;
    private RoleRepository roleRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public void saveUser(UserDTO account) {
        final User user = new User();
        user.setUserName(account.getUserName());
        user.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
        Role userRole = roleRepository.findByRole("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }
}
