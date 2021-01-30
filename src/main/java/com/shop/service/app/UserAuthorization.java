package com.shop.service.app;

import com.shop.model.entity.User;
import com.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAuthorization implements UserDetailsService {
    private UserRepository userRepository;

    public UserAuthorization(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.getByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("Пользователь " + login + " не найден.");
        }
        return user;
    }
}
