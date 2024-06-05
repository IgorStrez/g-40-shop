package de.ait_tr.g_40_shop.service;

import de.ait_tr.g_40_shop.domain.dto.CustomerDto;
import de.ait_tr.g_40_shop.domain.entity.Customer;
import de.ait_tr.g_40_shop.repository.CustomerRepository;
import de.ait_tr.g_40_shop.service.interfaces.CustomerService;
import de.ait_tr.g_40_shop.service.mapping.CustomerMappingService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository repository;
    private final CustomerMappingService mappingService;

    public CustomerServiceImpl(CustomerRepository repository, CustomerMappingService mappingService) {
        this.repository = repository;
        this.mappingService = mappingService;
    }

    @Override
    public CustomerDto save(CustomerDto dto) {
        Customer entity = mappingService.mapDtoToEntity(dto);
        repository.save(entity);
        return mappingService.mapEntityToDto(entity);
    }

    @Override
    public List<CustomerDto> getAllActiveCustomers() {
        return repository.findAll().stream().filter(Customer::isActive).map(mappingService::mapEntityToDto).toList();
    }

    @Override
    public CustomerDto getById(Long id) {
        Customer customer = repository.findById(id).orElse(null);
        if (customer == null || !customer.isActive()) {
            return null;
        }
        return mappingService.mapEntityToDto(customer);
    }

    @Override
    public CustomerDto update(CustomerDto customer) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void deleteByName(String name) {

    }

    @Override
    public void restoreById(Long id) {

    }

    @Override
    public long getActiveCustomersQuantity() {
        return 0;
    }

    @Override
    public BigDecimal getCartTotalPrice(Long customerId) {
        return null;
    }

    @Override
    public BigDecimal getCartAveragePrice(Long customerId) {
        return null;
    }

    @Override
    public void addProductToCart(Long customerId, Long productId) {

    }

    @Override
    public void removeProductFromCart(Long customerId, Long productId) {

    }

    @Override
    public void clearCart(Long customerId) {

    }
}
