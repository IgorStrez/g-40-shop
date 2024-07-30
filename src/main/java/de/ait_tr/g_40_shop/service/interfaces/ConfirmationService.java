package de.ait_tr.g_40_shop.service.interfaces;

import de.ait_tr.g_40_shop.domain.entity.User;

public interface ConfirmationService {

    String generateConfirmationCode(User user);

    User getUserByConfirmationCode(String code);
}