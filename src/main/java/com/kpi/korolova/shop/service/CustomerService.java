package com.kpi.korolova.shop.service;

import com.kpi.korolova.shop.exceptions.CustomerNotFound;
import com.kpi.korolova.shop.exceptions.InvalidParamsException;
import com.kpi.korolova.shop.exceptions.UserExistsException;
import com.kpi.korolova.shop.entities.Customer;
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
    @Autowired
    private UserService userService;

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

    public Customer getCustomer(Integer customerId) throws CustomerNotFound {
        if(!customerRepository.existsById(customerId)) {
            throw new CustomerNotFound("Customer is not found");
        }
        return customerRepository.findById(customerId).get();
    }

    public Customer getCurrentCustomer() throws CustomerNotFound {
        int userId = userService.getCurrentUser().getId();
        int customerId = customerRepository.findByUserId(userId).getId();
        if(!customerRepository.existsById(customerId)) {
            throw new CustomerNotFound("Customer is not found");
        }
        return customerRepository.findById(customerId).get();
    }

    public void editCustomer(Customer customer) throws CustomerNotFound {
        if(!customerRepository.existsById(customer.getId())) {
            throw new CustomerNotFound("Customer is not found");
        }
        if(customer.getUser().getPassword() == null && customer.getUser().getPassword().isEmpty()) {
            customer.getUser().setPassword(
                    customerRepository.getOne(customer.getId()).getUser().getPassword());
        }
        customerRepository.save(customer);
    }
}
