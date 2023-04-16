package com.ahmet.controller;

import com.ahmet.dto.request.RegisterRestaurantRequestDto;

import com.ahmet.dto.request.UpdateRestaurantRequestDto;
import com.ahmet.dto.response.GetRestaurantsProductResponseDto;
import com.ahmet.dto.response.RegisterRestaurantResponseDto;
import com.ahmet.repository.entity.Restaurant;
import com.ahmet.repository.entity.jointable.RestaurantsOrder;
import com.ahmet.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static com.ahmet.constants.EndPoints.*;

import java.util.List;

@RestController
@RequestMapping(RESTAURANT)
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PutMapping(REGISTER)
    public ResponseEntity<RegisterRestaurantResponseDto> register(@RequestBody RegisterRestaurantRequestDto dto) {
        return ResponseEntity.ok(restaurantService.register(dto));
    }

    @GetMapping(FIND_ALL)
    public ResponseEntity<List<Restaurant>> findAll() {
        return ResponseEntity.ok(restaurantService.findAll());
    }

    @GetMapping(FIND_ALL_RESTAURANTS_ORDER)
    public ResponseEntity<List<RestaurantsOrder>> findAllRestaurantsOrder(Long restaurantId) {
        return ResponseEntity.ok(restaurantService.findAllRestaurantsOrder(restaurantId));
    }

    @GetMapping(GET_RESTAURANTS_PRODUCT)
    public ResponseEntity<List<GetRestaurantsProductResponseDto>> findAllByRestaurantId(Long restaurantId) {
        return ResponseEntity.ok(restaurantService.findAllByRestaurantId(restaurantId));
    }
    @PostMapping(UPDATE)
    public  ResponseEntity<Boolean> update(UpdateRestaurantRequestDto dto){
        return  ResponseEntity.ok(restaurantService.update(dto));
    }
}
