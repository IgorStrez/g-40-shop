package de.ait_tr.g_40_shop.service;

import de.ait_tr.g_40_shop.domain.entity.User;
import de.ait_tr.g_40_shop.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        return repository.findByUsername(username).orElseThrow(
//                () -> new UsernameNotFoundException(
//                        String.format("User %s not found", username)));

        // Альтернативный вариант
        User user = repository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(
                    String.format("User %s not found", username));
        }

        return user;
    }
}
