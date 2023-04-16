package com.ahmet.mapper;

import com.ahmet.dto.request.AddOrderRequestDto;
import com.ahmet.repository.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IOrderMapper {
    IOrderMapper INSTANCE= Mappers.getMapper((IOrderMapper.class));

    Order toOrder(final AddOrderRequestDto dto);

}
