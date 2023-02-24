package com.zerobase.cms.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.zerobase.cms.user.domain.SignUpForm;
import com.zerobase.cms.user.domain.model.Customer;
import com.zerobase.cms.user.domain.repository.CustomerRepository;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SignUpCustomerServiceTest {

    @InjectMocks
    private SignUpCustomerService signUpCustomerService;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    void signUp() {
        //given
        Customer customer = Customer.builder()
            .name("name")
            .birth(LocalDate.now())
            .email("test@naver.com")
            .phone("01000000000")
            .password("1")
            .build();

        SignUpForm form = SignUpForm.builder()
            .name("name")
            .birth(LocalDate.now())
            .email("asd@naver.com")
            .phone("01054561321")
            .password("1")
            .build();

        given(customerRepository.save(any()))
            .willReturn(customer);

        //when
        Customer customerInfo = signUpCustomerService.signUp(form);

        //then
        assertEquals(customerInfo.getPassword(), "1");
        assertEquals(customerInfo.getName(), "name");
        assertEquals(customerInfo.getPhone(), "01000000000");
        assertEquals(customerInfo.getEmail(), "test@naver.com");
    }
}