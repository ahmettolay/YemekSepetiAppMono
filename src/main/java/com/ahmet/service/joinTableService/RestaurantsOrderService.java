package com.ahmet.service.joinTableService;

import com.ahmet.exception.ErrorType;
import com.ahmet.exception.YemekSepetiException;
import com.ahmet.repository.entity.jointable.RestaurantsOrder;
import com.ahmet.repository.joinRepository.IRestaurantsOrderRepository;
import com.ahmet.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantsOrderService extends ServiceManager<RestaurantsOrder, Long> {
    private final IRestaurantsOrderRepository repository;

    public RestaurantsOrderService(IRestaurantsOrderRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public List<RestaurantsOrder> findAllRestaurantsOrder(Long restaurantId) {
        List<RestaurantsOrder> restaurantsOrderList = repository.findAllRestaurantsOrder(restaurantId);
        if (restaurantsOrderList.size()<=0){
            throw  new YemekSepetiException(ErrorType.RESTAURANT_ORDER_FOUND);
        }
            return restaurantsOrderList;
    }

}
