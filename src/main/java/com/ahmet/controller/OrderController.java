package com.ahmet.controller;

import com.ahmet.dto.request.AddOrderRequestDto;
import com.ahmet.dto.response.GetAllOrderProducts;
import com.ahmet.repository.entity.Order;
import com.ahmet.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ahmet.constants.EndPoints.*;

import java.util.List;

@RestController
@RequestMapping(ORDER)
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping(BUY_PRODUCT)
    public ResponseEntity<Boolean> buyProduct(AddOrderRequestDto dto) {
        return ResponseEntity.ok(orderService.buyProduct(dto));
    }

    @GetMapping(FIND_ALL)
    public ResponseEntity<List<Order>> findAll() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping(FIND_ALL_BY_CUSTOMER)
    public ResponseEntity<List<Order>> findAllByCustomerId(Long customerId) {
        return ResponseEntity.ok(orderService.findAllByCustomerId(customerId));
    }


    @GetMapping(GET_ALL_ORDER_PRODUCT)
    public ResponseEntity<List<GetAllOrderProducts>> getAllOrderProducts(Long customerId) {
        return ResponseEntity.ok(orderService.getAllOrderProducts(customerId));
    }
}
