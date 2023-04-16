package com.ahmet.controller;

import  static  com.ahmet.constants.EndPoints.*;
import com.ahmet.dto.request.*;
import com.ahmet.dto.response.RegisterCustomerResponseDto;
import com.ahmet.repository.entity.Customer;
import com.ahmet.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(CUSTOMER)
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PutMapping(REGISTER)
    public ResponseEntity<RegisterCustomerResponseDto> register(@RequestBody @Valid RegisterCustomerRequestDto dto) {
        return ResponseEntity.ok(customerService.register(dto));
    }

    @GetMapping(FIND_ALL)
    public ResponseEntity<List<Customer>> findAll() {
        return ResponseEntity.ok(customerService.findAll());
    }

    @PostMapping(ACTIVATE_STATUS)
    public ResponseEntity<Boolean> activateStatus(@RequestBody  CustomerActivateRequestDto dto) {
        return ResponseEntity.ok(customerService.activateStatus(dto));
    }
    @PostMapping(LOGIN)
    public ResponseEntity<Boolean>login(@RequestBody  @Valid LoginRequestDto dto){
        return  ResponseEntity.ok(customerService.login(dto));
    }
    @PostMapping(UPDATE)
    public ResponseEntity<Boolean> update(@RequestBody UpdateCustomerRequestDto dto){
        return  ResponseEntity.ok(customerService.update(dto));
    }
    @GetMapping(ADD_BALANCE)
    public ResponseEntity<Boolean> addBalance(AddCustomerBalanceRequestDto dto){
        return  ResponseEntity.ok(customerService.addBalance(dto));
    }
}
