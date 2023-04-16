package com.ahmet.service;

import com.ahmet.dto.request.RegisterRestaurantRequestDto;
import com.ahmet.dto.request.UpdateRestaurantRequestDto;
import com.ahmet.dto.response.GetRestaurantsProductResponseDto;
import com.ahmet.dto.response.RegisterRestaurantResponseDto;
import com.ahmet.exception.ErrorType;
import com.ahmet.exception.YemekSepetiException;
import com.ahmet.mapper.IRestaurantMapper;
import com.ahmet.repository.IRestaurantRepository;
import com.ahmet.repository.entity.Restaurant;
import com.ahmet.repository.entity.jointable.RestaurantsOrder;
import com.ahmet.service.joinTableService.RestaurantsOrderService;
import com.ahmet.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService extends ServiceManager<Restaurant, Long> {
    private final IRestaurantRepository restaurantRepository;
    private final ProductService productService;
    private final RestaurantsOrderService restaurantsOrderService;

    public RestaurantService(IRestaurantRepository restaurantRepository,
                             RestaurantsOrderService restaurantsOrderService,
                             ProductService productService) {
        super(restaurantRepository);
        this.restaurantRepository = restaurantRepository;
        this.restaurantsOrderService = restaurantsOrderService;
        this.productService = productService;
    }

    public RegisterRestaurantResponseDto register(RegisterRestaurantRequestDto dto) {
        if (restaurantRepository.existsByAddress(dto.getAddress())) {
            throw new YemekSepetiException(ErrorType.ADDRESS_DUPLICATE);
        }
        Restaurant restaurant = IRestaurantMapper.INSTANCE.toRestaurant(dto);
        RegisterRestaurantResponseDto registerRestaurantResponseDto = IRestaurantMapper.INSTANCE.toRegisterRestaurantResponseDto(restaurant);
        save(restaurant);
        return registerRestaurantResponseDto;
    }

    public List<RestaurantsOrder> findAllRestaurantsOrder(Long restaurantId) {
        return restaurantsOrderService.findAllRestaurantsOrder(restaurantId);
    }

    public List<GetRestaurantsProductResponseDto> findAllByRestaurantId(Long restaurantId) {
        return productService.findAllByRestaurantId(restaurantId);
    }

    public Boolean update(UpdateRestaurantRequestDto dto) {
        Optional<Restaurant> restaurant = findById(dto.getId());
        if (restaurant.isEmpty()) {
            throw new YemekSepetiException(ErrorType.RESTAURANT_NOT_FOUND);
        }
        update(IRestaurantMapper.INSTANCE.updateRestaurant(dto, restaurant.get()));
        return true;
    }
}
