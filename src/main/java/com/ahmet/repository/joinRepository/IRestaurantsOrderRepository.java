package com.ahmet.repository.joinRepository;

import com.ahmet.repository.entity.jointable.RestaurantsOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IRestaurantsOrderRepository  extends JpaRepository<RestaurantsOrder,Long> {
    @Query(value = "SELECT p.id,p.name FROM order_product_id AS o_d\n" +
            "INNER JOIN products AS p  ON p.id=o_d.product_id\n" +
            "WHERE p.restaurant_id= ?1",nativeQuery = true)
    List<RestaurantsOrder> findAllRestaurantsOrder(Long restaurantId);
}
