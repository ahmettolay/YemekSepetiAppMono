package com.ahmet.mapper;

import com.ahmet.dto.request.RegisterRestaurantRequestDto;
import com.ahmet.dto.request.UpdateRestaurantRequestDto;
import com.ahmet.dto.response.RegisterRestaurantResponseDto;
import com.ahmet.repository.entity.Restaurant;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IRestaurantMapper {
    IRestaurantMapper INSTANCE= Mappers.getMapper(IRestaurantMapper.class);
    Restaurant toRestaurant(final RegisterRestaurantRequestDto dto);
    RegisterRestaurantResponseDto toRegisterRestaurantResponseDto (final  Restaurant restaurant);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Restaurant updateRestaurant(final UpdateRestaurantRequestDto dto, @MappingTarget Restaurant restaurant);
}
