package com.kpi.korolova.shop.service;

import com.kpi.korolova.shop.exceptions.InvalidParamsException;
import com.kpi.korolova.shop.exceptions.UserExistsException;
import com.kpi.korolova.shop.model.Customer;
import com.kpi.korolova.shop.repository.CustomerRepository;
import com.kpi.korolova.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private UserRepository userRepository;

    public void addCustomer(Customer customer) throws InvalidParamsException, UserExistsException {
        if (customer.getUser().getEmail() == null || customer.getUser().getEmail().isEmpty() ||
                customer.getUser().getPassword() == null || customer.getUser().getPassword().isEmpty() ||
                customer.getFullName() == null || customer.getFullName().isEmpty() ||
                customer.getPhone() == null || customer.getPhone().isEmpty()) {
            throw new InvalidParamsException(String.format("customer: %s", customer));
        }
        if(userRepository.existsByEmail(customer.getUser().getEmail())) {
            throw new UserExistsException("Пользователь с таким email существует");
        }
        customer.getUser().setRole("ROLE_USER");
        customerRepository.save(customer);
    }
}
