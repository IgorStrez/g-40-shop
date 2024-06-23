package de.ait_tr.g_40_shop.service;

import de.ait_tr.g_40_shop.domain.dto.CustomerDto;
import de.ait_tr.g_40_shop.domain.entity.Cart;
import de.ait_tr.g_40_shop.domain.entity.Customer;
import de.ait_tr.g_40_shop.exception_handling.exceptions.CustomerInactiveException;
import de.ait_tr.g_40_shop.exception_handling.exceptions.CustomerNotFoundException;
import de.ait_tr.g_40_shop.repository.CustomerRepository;
import de.ait_tr.g_40_shop.service.interfaces.CustomerService;
import de.ait_tr.g_40_shop.service.mapping.CustomerMappingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        Cart cart = new Cart();
        entity.setCart(cart);
        cart.setCustomer(entity);

        repository.save(entity);
        return mappingService.mapEntityToDto(entity);
    }

    @Override
    public List<CustomerDto> getAllActiveCustomers() {
        return repository.findAll()
                .stream()
                .filter(Customer::isActive)
                .map(mappingService::mapEntityToDto)
                .toList();
    }

    @Override
    public CustomerDto getActiveCustomerById(Long id) {
        Customer customer = repository.findById(id).orElseThrow(
                () -> new CustomerNotFoundException(id)
        );

        if (!customer.isActive()) {
            throw new CustomerInactiveException(id);
        }

        return mappingService.mapEntityToDto(customer);
    }

    @Override
    @Transactional
    public CustomerDto update(CustomerDto dto) {
        Long id = dto.getId();
        Customer customer = repository.findById(id).orElseThrow(
                () -> new CustomerNotFoundException(id)
        );

        customer.setName(dto.getName());
        return mappingService.mapEntityToDto(customer);
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
    public long getActiveCustomersNumber() {
        return 0;
    }

    @Override
    public BigDecimal getCartTotalCost(Long customerId) {
        return null;
    }

    @Override
    public BigDecimal getAverageProductCost(Long customerId) {
        return null;
    }

    @Override
    public void addProductToCustomersCart(Long customerId, Long productId) {

    }

    @Override
    public void removeProductFromCustomersCart(Long customerId, Long productId) {

    }

    @Override
    public void clearCart(Long customerId) {

    }
}