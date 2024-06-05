package de.ait_tr.g_40_shop.service.mapping;

import de.ait_tr.g_40_shop.domain.dto.CustomerDto;
import de.ait_tr.g_40_shop.domain.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMappingService {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", constant = "true")
    Customer mapDtoToEntity(CustomerDto dto);
    CustomerDto mapEntityToDto(Customer entity);
}