package com.zerobase.cms.user.service;

import static com.zerobase.cms.user.exception.ErrorCode.LOGIN_CHECK_FAIL;
import static com.zerobase.cms.user.exception.ErrorCode.USER_NOT_FOUND;

import com.zerobase.cms.user.domain.model.Customer;
import com.zerobase.cms.user.domain.repository.CustomerRepository;
import com.zerobase.cms.user.exception.CustomException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public Optional<Customer> findByIdAndEmail(Long id, String email) {
        return customerRepository.findByIdAndEmail(id, email);
    }

    public Customer findValidCustomer(String email, String password) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(email);
        if(!optionalCustomer.isPresent()) {
            throw new CustomException(USER_NOT_FOUND);
        }
        Customer customer = optionalCustomer.get();

        if(!customer.getPassword().equals(password) && customer.isVerify()) {
            throw new CustomException(LOGIN_CHECK_FAIL);
        }

        return customer;
    }
}
