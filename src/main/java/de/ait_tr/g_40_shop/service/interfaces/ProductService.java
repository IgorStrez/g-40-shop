package de.ait_tr.g_40_shop.service.interfaces;

import de.ait_tr.g_40_shop.domain.dto.ProductDto;
import de.ait_tr.g_40_shop.domain.dto.ProductSupplyDto;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    ProductDto save(ProductDto product);
    List<ProductDto> getAllActiveProducts();
    ProductDto getById(Long id);
    ProductDto update(ProductDto product);
    void deleteById(Long id);
    void deleteByTitle(String title);
    void restoreById(Long id);
    long getActiveProductsQuantity();
    BigDecimal getActiveProductsTotalPrice();
    BigDecimal getActiveProductsAveragePrice();
    void attachImage(String imageUrl, String productTitle);
    List<ProductSupplyDto> getProductsForSupply();

}
