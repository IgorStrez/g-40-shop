package de.ait_tr.g_40_shop.service;

import de.ait_tr.g_40_shop.domain.entity.ConfirmationCode;
import de.ait_tr.g_40_shop.domain.entity.User;
import de.ait_tr.g_40_shop.exception_handling.exceptions.ConfirmationFailedException;
import de.ait_tr.g_40_shop.repository.ConfirmationCodeRepository;
import de.ait_tr.g_40_shop.service.interfaces.ConfirmationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ConfirmationServiceImpl implements ConfirmationService {

    private final ConfirmationCodeRepository repository;

    public ConfirmationServiceImpl(ConfirmationCodeRepository repository) {
        this.repository = repository;
    }

    @Override
    public String generateConfirmationCode(User user) {
        LocalDateTime expired = LocalDateTime.now().plusMinutes(2);
        String code = UUID.randomUUID().toString();
        ConfirmationCode entity = new ConfirmationCode(code, expired, user);
        repository.save(entity);
        return code;
    }

    @Override
    public User getUserByConfirmationCode(String code) {
        ConfirmationCode entity = repository.findByCode(code).orElse(null);

        if (entity == null) {
            throw new ConfirmationFailedException("Confirmation code not found");
        }

        if (LocalDateTime.now().isAfter(entity.getExpired())) {
            throw new ConfirmationFailedException("Confirmation code expired");
        }

        return entity.getUser();
    }
}