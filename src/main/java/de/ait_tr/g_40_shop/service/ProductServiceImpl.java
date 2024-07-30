package de.ait_tr.g_40_shop.service;

import de.ait_tr.g_40_shop.domain.dto.ProductDto;
import de.ait_tr.g_40_shop.domain.dto.ProductSupplyDto;
import de.ait_tr.g_40_shop.domain.entity.Product;
import de.ait_tr.g_40_shop.exception_handling.exceptions.*;
import de.ait_tr.g_40_shop.repository.ProductRepository;
import de.ait_tr.g_40_shop.service.interfaces.ProductService;
import de.ait_tr.g_40_shop.service.mapping.ProductMappingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository repository;
    private final ProductMappingService mappingService;

    public ProductServiceImpl(ProductRepository repository, ProductMappingService mappingService) {
        this.repository = repository;
        this.mappingService = mappingService;
    }

    @Override
    public ProductDto save(ProductDto dto) {
        Product entity = mappingService.mapDtoToEntity(dto);

        try {
            repository.save(entity);
        } catch (Exception e) {
            throw new SavingProductException(String.format("Error while saving product: %s", entity), e);
        }

        return mappingService.mapEntityToDto(entity);
    }

    @Override
    public List<ProductDto> getAllActiveProducts() {
        List<ProductDto> products = repository.findAll()
                .stream()
                .filter(Product::isActive)
                .map(mappingService::mapEntityToDto)
                .toList();

        if (products.isEmpty()) {
            throw new NoActiveProductsException("There are no active products in the database");
        }

        return products;
    }

//    @Override
//    public ProductDto getById(Long id) {
//
          // Демонстрация логирования на разные уровни
//        logger.info("Method getById called with parameter {}", id);
//        logger.warn("Method getById called with parameter {}", id);
//        logger.error("Method getById called with parameter {}", id);
//
//        Product product = repository.findById(id).orElse(null);
//
//        if (product == null || !product.isActive()) {
//            return null;
//        }
//
//        return mappingService.mapEntityToDto(product);
//    }

    @Override
    public ProductDto getById(Long id) {
        Product product = repository.findById(id).orElse(null);

        if (product == null || !product.isActive()) {
            throw new ThirdTestException(String.format("Product with id %d not found", id));
        }

        return mappingService.mapEntityToDto(product);
    }

    @Override
    public ProductDto update(ProductDto product) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void deleteByTitle(String title) {

    }

    @Override
    public void restoreById(Long id) {

    }

    @Override
    public long getActiveProductsQuantity() {
        return 0;
    }

    @Override
    public BigDecimal getActiveProductsTotalPrice() {
        return null;
    }

    @Override
    public BigDecimal getActiveProductsAveragePrice() {
        return null;
    }

    @Override
    public void attachImage(String imageUrl, String productTitle) {

    }

    @Override
    public List<ProductSupplyDto> getProductsForSupply() {
        return null;
    }

}