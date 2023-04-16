package com.ahmet.repository.entity;

import com.ahmet.repository.Enum.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    @Column(unique = true)
    private String email;
    private String address;
    private String phoneNumber;
    private String password;
    private String cardDetails;
    @Min(value = 0,message = "Bakiye 0 dan düşük olamaz.")
    @Builder.Default
    private Double balance=0.0;
    private String activationCode;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private EStatus status = EStatus.PENDING;
}
