package com.RestauranteWeb.restauranteweb.service;
import com.RestauranteWeb.restauranteweb.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.RestauranteWeb.restauranteweb.repository.UserRepository;

@Service
public class UserService {

        private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    
    public boolean updatePasswordByUsername(String username, String newPassword) {
        return userRepository.findByUsername(username).map(user -> {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            return true;
        }).orElse(false);
    }
    
    public boolean updatePasswordByCorreo(String correo, String newPassword) {
        return userRepository.findByCorreo(correo).map(user -> {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            return true;
        }).orElse(false);
    }
}
