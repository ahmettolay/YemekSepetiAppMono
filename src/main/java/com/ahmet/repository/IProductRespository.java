package com.ahmet.repository;

import com.ahmet.repository.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRespository extends JpaRepository<Product,Long> {
    public List<Product> findAllByRestaurantId(Long restaurantId);

    @Query("select p.cost from  Product  p where p.id= ?1")
    public Double getProductByCost(Long productId);

}
