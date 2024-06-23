package de.ait_tr.g_40_shop.service.interfaces;

import de.ait_tr.g_40_shop.domain.entity.User;

public interface EmailService {

    void sendConfirmationEmail(User user);
}