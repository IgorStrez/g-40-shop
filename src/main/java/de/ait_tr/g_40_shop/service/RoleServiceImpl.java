package de.ait_tr.g_40_shop.service;

import de.ait_tr.g_40_shop.domain.entity.Role;
import de.ait_tr.g_40_shop.repository.RoleRepository;
import de.ait_tr.g_40_shop.service.interfaces.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repository;

    public RoleServiceImpl(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Role getRoleUser() {
        return repository.findByTitle("ROLE_USER").orElseThrow(
                () -> new RuntimeException("Database doesn't contain ROLE_USER")
        );
    }
}
