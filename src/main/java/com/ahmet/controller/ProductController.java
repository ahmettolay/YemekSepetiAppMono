package com.ahmet.controller;

import com.ahmet.dto.request.AddProductRequestDto;
import com.ahmet.dto.request.UpdateProductRequestDto;
import com.ahmet.dto.response.AddProductResponseDto;
import com.ahmet.repository.entity.Product;
import com.ahmet.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static com.ahmet.constants.EndPoints.*;

import java.util.List;

@RestController
@RequestMapping(PRODUCT)
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PutMapping(ADD)
    public ResponseEntity<AddProductResponseDto> add(AddProductRequestDto dto) {
        return ResponseEntity.ok(productService.add(dto));
    }
    @GetMapping(FIND_ALL)
    public ResponseEntity<List<Product>> findAll(){
        return  ResponseEntity.ok(productService.findAll());
    }


    @PostMapping(UPDATE)
    public ResponseEntity<Boolean> update(@RequestBody UpdateProductRequestDto dto){
        return  ResponseEntity.ok(productService.update(dto));
    }
}
