package com.ahmet.mapper;

import com.ahmet.dto.request.AddCustomerBalanceRequestDto;
import com.ahmet.dto.request.UpdateCustomerRequestDto;
import com.ahmet.dto.request.RegisterCustomerRequestDto;
import com.ahmet.dto.response.RegisterCustomerResponseDto;
import com.ahmet.repository.entity.Customer;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ICustomerMapper {

    ICustomerMapper INSTANCE = Mappers.getMapper(ICustomerMapper.class);

    Customer toCustomer(final RegisterCustomerRequestDto dto);

    RegisterCustomerResponseDto toRegisterResponseDto(final Customer customer);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Customer updateCustomer(final UpdateCustomerRequestDto dto, @MappingTarget final Customer customer);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Customer addBalance(final AddCustomerBalanceRequestDto dto, @MappingTarget final Customer customer);
}
