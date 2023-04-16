package com.ahmet.mapper;

import com.ahmet.dto.request.AddProductRequestDto;
import com.ahmet.dto.request.UpdateProductRequestDto;
import com.ahmet.dto.response.AddProductResponseDto;
import com.ahmet.dto.response.GetRestaurantsProductResponseDto;
import com.ahmet.repository.entity.Product;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IProductMapper {
    IProductMapper INSTANCE = Mappers.getMapper(IProductMapper.class);

    Product toProduct(final AddProductRequestDto dto);

    AddProductResponseDto toAddProductResponseDto(final Product product);
    List<GetRestaurantsProductResponseDto> toGetRestaurantsProductResponseDto(final List<Product> product);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Product updateProduct(final UpdateProductRequestDto dto, @MappingTarget final  Product product);
}
