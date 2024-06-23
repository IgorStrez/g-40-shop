package de.ait_tr.g_40_shop.service.mapping;

import de.ait_tr.g_40_shop.domain.dto.CartDto;
import de.ait_tr.g_40_shop.domain.entity.Cart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ProductMappingService.class)
public interface CartMappingService {

    CartDto mapEntityToDto(Cart entity);
}