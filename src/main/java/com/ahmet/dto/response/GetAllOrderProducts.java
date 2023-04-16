package com.ahmet.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllOrderProducts {

    private String name;
    private String category;
    private Double cost;
    private String restaurantId;
}
