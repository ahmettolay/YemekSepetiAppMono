package com.ahmet.service;

import com.ahmet.dto.request.AddProductRequestDto;
import com.ahmet.dto.request.UpdateProductRequestDto;
import com.ahmet.dto.response.AddProductResponseDto;
import com.ahmet.dto.response.GetAllOrderProducts;
import com.ahmet.dto.response.GetRestaurantsProductResponseDto;
import com.ahmet.exception.ErrorType;
import com.ahmet.exception.YemekSepetiException;
import com.ahmet.mapper.IProductMapper;
import com.ahmet.repository.IProductRespository;
import com.ahmet.repository.entity.Product;
import com.ahmet.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService extends ServiceManager<Product, Long> {

    private final IProductRespository productRespository;


    public ProductService(IProductRespository productRespository) {
        super(productRespository);
        this.productRespository = productRespository;


    }

    public AddProductResponseDto add(AddProductRequestDto dto) {
        Product product = IProductMapper.INSTANCE.toProduct(dto);
        AddProductResponseDto productResponseDto = IProductMapper.INSTANCE.toAddProductResponseDto(product);
        save(product);
        return productResponseDto;
    }

    public List<GetRestaurantsProductResponseDto> findAllByRestaurantId(Long restaurantId) {

        List<Product> productList = productRespository.findAllByRestaurantId(restaurantId);
        if (productList.size() == 0) {
            throw new YemekSepetiException(ErrorType.PRODUCT_NOT_FOUND);
        }
        List<GetRestaurantsProductResponseDto> getRestaurantsProductResponseDto
                = IProductMapper.INSTANCE.toGetRestaurantsProductResponseDto(productList);
        return getRestaurantsProductResponseDto;
    }


    public List<Product> getAllOrderProducts(List<Long> productIds) {
        List<Product> productList = findAllById(productIds);
        if (productList.size() == 0) {
            throw new YemekSepetiException(ErrorType.PRODUCT_NOT_FOUND);
        }
        return productList;
    }

    public  Boolean update(UpdateProductRequestDto dto){
        Optional<Product> product=findById(dto.getId());
        if (product.isEmpty()){
            throw  new YemekSepetiException(ErrorType.PRODUCT_NOT_FOUND);
        }
        update(IProductMapper.INSTANCE.updateProduct(dto,product.get()));
        return  true;
    }
}
