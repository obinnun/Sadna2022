package com.example.demogame.user;

import com.example.demogame.cart.Cart;
import com.example.demogame.cart.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;

    private CartRepository cartRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, CartRepository cartRepository,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User myUser = getUserByEmail(username);
        if (myUser == null) {
            System.out.println("credentials dont match: auth error");
            throw new UsernameNotFoundException("No in db");
        } else {
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("read"));
            return new org.springframework.security.core.userdetails.User(myUser.getEmail(), myUser.getPassword(), authorities);
        }
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public User addUser(User userToInsert) {
        try {

            userToInsert.setPassword(passwordEncoder.encode(userToInsert.getPassword()));
            User addedUser = userRepository.save(userToInsert);
            addedUser.getUser_id();
            Cart userCart = new Cart(addedUser);
            cartRepository.save(userCart);
            return addedUser;
        } catch (Exception e) {
            System.out.println(e.toString());
            throw e;
        }
    }

    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }
}
