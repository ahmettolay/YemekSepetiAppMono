package com.ahmet.repository;

import com.ahmet.repository.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderRepository extends JpaRepository<Order,Long> {

    public List<Order> findAllByCustomerId(Long customerId);

    @Query("select  o.id from  Order o where o.customerId= ?1")
    List<Long> getAllByOrderIds(Long customerId);

    @Query(value = "SELECT op.product_id FROM order_product_id as op where op.order_id IN(?1)",nativeQuery = true)
    List<Long> gelAllByProductIds(List<Long> orderIds);

}