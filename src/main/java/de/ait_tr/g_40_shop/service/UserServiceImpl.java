package de.ait_tr.g_40_shop.service;

import de.ait_tr.g_40_shop.domain.entity.User;
import de.ait_tr.g_40_shop.repository.UserRepository;
import de.ait_tr.g_40_shop.service.interfaces.EmailService;
import de.ait_tr.g_40_shop.service.interfaces.RoleService;
import de.ait_tr.g_40_shop.service.interfaces.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final BCryptPasswordEncoder encoder;
    private final RoleService roleService;
    private final EmailService emailService;

    public UserServiceImpl(UserRepository repository, BCryptPasswordEncoder encoder, RoleService roleService, EmailService emailService) {
        this.repository = repository;
        this.encoder = encoder;
        this.roleService = roleService;
        this.emailService = emailService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return repository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(
                        String.format("User %s not found", username)));

        // Альтернативный вариант
//        User user = repository.findByUsername(username).orElse(null);
//
//        if (user == null) {
//            throw new UsernameNotFoundException(
//                    String.format("User %s not found", username));
//        }
//
//        return user;
    }

    @Override
    public void register(User user) {
        user.setId(null);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setActive(false);
        user.setRoles(Set.of(roleService.getRoleUser()));

        // Генерация кода активации и установка времени истечения
        String activationCode = UUID.randomUUID().toString();
        user.setActivationCode(activationCode);
        user.setActivationExpiry(LocalDateTime.now().plusHours(24));

        repository.save(user);
        emailService.sendConfirmationEmail(user);
    }

    @Override
    public boolean activateUser(String activationCode) {
        Optional<User> userOptional = repository.findByActivationCode(activationCode);
        if (userOptional.isEmpty()) {
            return false;
        }

        User user = userOptional.get();
        if (user.getActivationExpiry().isBefore(LocalDateTime.now())) {
            return false;
        }

        user.setActive(true);
        user.setActivationCode(null);
        user.setActivationExpiry(null);
        repository.save(user);
        return true;
    }
}
