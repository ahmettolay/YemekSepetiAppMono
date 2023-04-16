package com.ahmet.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCustomerRequestDto {
    private Long id;
    private String name;
    private String surname;
    private String address;
    private String phoneNumber;
    private String cardDetails;
}
