package com.ahmet.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductRequestDto {
    private Long id;
    private String name;
    private String category;
    @Min(value = 1, message = "ürün fiyatı 1'en düşük olamaz.")
    private Double cost;
}
