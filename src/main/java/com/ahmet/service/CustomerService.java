package com.ahmet.service;

import com.ahmet.dto.request.*;
import com.ahmet.dto.response.RegisterCustomerResponseDto;
import com.ahmet.exception.ErrorType;
import com.ahmet.exception.YemekSepetiException;
import com.ahmet.mapper.ICustomerMapper;
import com.ahmet.repository.Enum.EStatus;
import com.ahmet.repository.ICustomerRepository;
import com.ahmet.repository.entity.Customer;
import com.ahmet.utility.CodeGenerator;
import com.ahmet.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService extends ServiceManager<Customer, Long> {
    private final ICustomerRepository customerRepository;

    public CustomerService(ICustomerRepository customerRepository) {
        super(customerRepository);
        this.customerRepository = customerRepository;
    }

    public RegisterCustomerResponseDto register(RegisterCustomerRequestDto dto) {
        Customer customer = ICustomerMapper.INSTANCE.toCustomer(dto);
        if (!dto.getPassword().equals(dto.getRepassword())) {
            throw new YemekSepetiException(ErrorType.PASSWORD_ERROR);
        } else if (customerRepository.existsByEmail(dto.getEmail())) {
            throw new YemekSepetiException(ErrorType.EMAIL_DUPLICATE);
        }
        customer.setActivationCode(CodeGenerator.generateCode());
        save(customer);
        return ICustomerMapper.INSTANCE.toRegisterResponseDto(customer);
    }

    public Boolean activateStatus(CustomerActivateRequestDto dto) {
        Optional<Customer> customer = findById(dto.getId());
        if (customer.isEmpty()) {
            throw new YemekSepetiException(ErrorType.CUSTOMER_NOT_FOUND);
        } else if (customer.get().getActivationCode().equals(dto.getActivateCode())) {
            customer.get().setStatus(EStatus.ACTIVE);
            update(customer.get());
            return true;
        }
        throw new YemekSepetiException(ErrorType.ACTIVATE_CODE_ERROR);
    }

    public Boolean login(LoginRequestDto dto) {
        Optional<Customer> customer = customerRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword());
        if (customer.isEmpty()) {
            throw new YemekSepetiException(ErrorType.LOGIN_ERROR);
        } else if (!customer.get().getStatus().equals(EStatus.ACTIVE)) {
            throw new YemekSepetiException(ErrorType.NOT_ACTIVATE_ERROR);
        }

        return true;
    }

    public List<Customer> findAll() {
        List<Customer> customerList = customerRepository.findAll();
        if (customerList.isEmpty()) {
            throw new YemekSepetiException(ErrorType.CUSTOMERS_NOT_FOUND);
        }
        return customerList;
    }

    public Optional<Double> getCustomerByBalance(Long customerId) {
        return customerRepository.getCustomerByBalance(customerId);
    }

    public Boolean update(UpdateCustomerRequestDto dto) {
        Optional<Customer> customer = findById(dto.getId());
        if (customer.isEmpty()) {
            throw new YemekSepetiException(ErrorType.CUSTOMERS_NOT_FOUND);
        }
        update(ICustomerMapper.INSTANCE.updateCustomer(dto, customer.get()));
        return true;
    }

    public Boolean addBalance(AddCustomerBalanceRequestDto dto) {
        Optional<Customer> customer = findById(dto.getId());
        if (customer.isEmpty()) {
            throw new YemekSepetiException(ErrorType.CUSTOMERS_NOT_FOUND);
        }
        update(ICustomerMapper.INSTANCE.addBalance(dto, customer.get()));
        return true;
    }
}
