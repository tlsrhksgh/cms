package com.zerobase.cms.user.service.customer;

import static com.zerobase.cms.user.exception.ErrorCode.*;

import com.zerobase.cms.user.domain.SignUpForm;
import com.zerobase.cms.user.domain.model.Customer;
import com.zerobase.cms.user.domain.repository.CustomerRepository;
import com.zerobase.cms.user.exception.CustomException;
import com.zerobase.cms.user.exception.ErrorCode;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SignUpCustomerService {

    private final CustomerRepository customerRepository;

    public Customer signUp(SignUpForm form) {
        return customerRepository.save(Customer.from(form));
    }

    public boolean isEmailExist(String email) {
        return customerRepository.findByEmail(email.toLowerCase(Locale.ROOT)).isPresent();
    }

    @Transactional
    public void verifyEmail(String email, String code) {
        Customer customer = customerRepository.findByEmail(email)
            .orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        if(customer.isVerify()) {
            throw new CustomException(ALREADY_VERIFY);
        } else if(!customer.getVerificationCode().equals(code)) {
            throw new CustomException(WRONG_VERIFICATION_CODE);
        } else if(customer.getVerifyExpiredAt().isBefore(LocalDateTime.now())) {
            throw new CustomException(EXPIRE_VERIFICATION_CODE);
        } else {
            customer.setVerify(true);
        }
    }

    @Transactional
    public LocalDateTime changeCustomerValidateEmail(Long customerId, String verificationCode) {
        Optional<Customer> optionalCustomer =customerRepository.findById(customerId);

        if(!optionalCustomer.isPresent()) {
            throw  new CustomException(USER_NOT_FOUND);
        }

        Customer customer = optionalCustomer.get();
        customer.setVerificationCode(verificationCode);
        customer.setVerifyExpiredAt(LocalDateTime.now().plusDays(1));

        return customer.getVerifyExpiredAt();
    }

}
