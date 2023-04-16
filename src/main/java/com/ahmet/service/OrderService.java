package com.ahmet.service;

import com.ahmet.dto.request.AddOrderRequestDto;
import com.ahmet.dto.response.GetAllOrderProducts;
import com.ahmet.exception.ErrorType;
import com.ahmet.exception.YemekSepetiException;
import com.ahmet.mapper.IOrderMapper;
import com.ahmet.repository.entity.Customer;
import com.ahmet.repository.entity.Order;
import com.ahmet.repository.IOrderRepository;
import com.ahmet.repository.entity.Product;
import com.ahmet.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService extends ServiceManager<Order, Long> {
    private final IOrderRepository orderRepository;
    private final CustomerService customerService;
    private final ProductService productService;
    private final RestaurantService restaurantService;

    public OrderService(IOrderRepository orderRepository,
                        CustomerService customerService,
                        ProductService productService,
                        RestaurantService restaurantService) {
        super(orderRepository);
        this.orderRepository = orderRepository;
        this.customerService = customerService;
        this.productService = productService;
        this.restaurantService = restaurantService;
    }

    public Boolean buyProduct(AddOrderRequestDto dto) {
        List<Product> productList = productService.findAllById(dto.getProductId());
        Optional<Customer> customer = customerService.findById(dto.getCustomerId());
        Double sumCost = 0.0;
        for (Product product : productList) {
            sumCost += product.getCost();
        }
        if (customer.get().getBalance() < sumCost) {
            throw new YemekSepetiException(ErrorType.BALANCE_NOT_FOUND);
        }
        customer.get().setBalance(customer.get().getBalance()-sumCost);
        customerService.update(customer.get());
        Order order = IOrderMapper.INSTANCE.toOrder(dto);
        save(order);
        return true;
    }

    public List<Order> findAllByCustomerId(Long customerId) {
        List<Order> orderList = orderRepository.findAllByCustomerId(customerId);
        if (orderList.size() == 0) {
            throw new YemekSepetiException(ErrorType.ORDER_NOT_FOUND);
        }
        return orderList;
    }

    public List<GetAllOrderProducts> getAllOrderProducts(Long customerId) {
        List<GetAllOrderProducts> result = new ArrayList<>();
        List<Long> orderIds = orderRepository.getAllByOrderIds(customerId);
        List<Long> productIds = orderRepository.gelAllByProductIds(orderIds);
        List<Product> productList = productService.getAllOrderProducts(productIds);
        if (productList.size() == 0) {
            throw new YemekSepetiException(ErrorType.PRODUCT_NOT_FOUND);
        }
        productList.forEach(p -> {
            GetAllOrderProducts dto = GetAllOrderProducts.builder()
                    .name(p.getName())
                    .category(p.getCategory())
                    .cost(p.getCost())
                    .restaurantId(restaurantService.findById(p.getRestaurantId()).get().getName())
                    .build();
            result.add(dto);
        });
        return result;
    }
}
