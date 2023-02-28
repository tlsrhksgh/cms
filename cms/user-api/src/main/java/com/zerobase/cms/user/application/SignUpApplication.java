package com.zerobase.cms.user.application;

import com.zerobase.cms.user.client.MailgunClient;
import com.zerobase.cms.user.client.mailgun.SendMailForm;
import com.zerobase.cms.user.domain.SignUpForm;
import com.zerobase.cms.user.domain.model.Customer;
import com.zerobase.cms.user.domain.model.Seller;
import com.zerobase.cms.user.domain.seller.SellerService;
import com.zerobase.cms.user.domain.seller.SignUpSellerService;
import com.zerobase.cms.user.exception.CustomException;
import com.zerobase.cms.user.exception.ErrorCode;
import com.zerobase.cms.user.service.customer.SignUpCustomerService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SignUpApplication {
    private final MailgunClient mailgunClient;
    private final SignUpCustomerService signUpCustomerService;
    private final SellerService sellerService;

    public void customerVerify(String email, String code) {
        signUpCustomerService.verifyEmail(email, code);
    }

    public String customerSignUp(SignUpForm form) {
        if(signUpCustomerService.isEmailExist(form.getEmail())) {
            throw new CustomException(ErrorCode.ALREADY_REGISTER_USER);
        } else {
            Customer customer = signUpCustomerService.signUp(form);

            String verifyCode = getRandomCode();

            SendMailForm sendMailForm = SendMailForm.builder()
                .from("tester@test.com")
                .to(form.getEmail())
                .subject("Verification Email")
                .text(getVerificationEmailBody(customer.getEmail(), customer.getName(), "customer", verifyCode))
                .build();

            mailgunClient.sendEmail(sendMailForm);
            signUpCustomerService.changeCustomerValidateEmail(customer.getId(), verifyCode);
            return "회원 가입에 성공하였습니다";
        }
    }

    public void sellerVerify(String email, String code) {
        sellerService.verifyEmail(email, code);
    }

    public String sellerSignUp(SignUpForm form) {
        if(sellerService.isEmailExist(form.getEmail())) {
            throw new CustomException(ErrorCode.ALREADY_REGISTER_USER);
        } else {
            Seller seller = sellerService.signUp(form);

            String verifyCode = getRandomCode();

            SendMailForm sendMailForm = SendMailForm.builder()
                .from("tester@test.com")
                .to(form.getEmail())
                .subject("Verification Email")
                .text(getVerificationEmailBody(seller.getEmail(), seller.getName(), "seller", verifyCode))
                .build();

            mailgunClient.sendEmail(sendMailForm);
            sellerService.changeSellerValidateEmail(seller.getId(), verifyCode);
            return "회원 가입에 성공하였습니다";
        }
    }


    private String getRandomCode() {
        return RandomStringUtils.random(10, true, true);
    }

    private String getVerificationEmailBody(String email, String name, String type, String code) {
        StringBuilder builder = new StringBuilder();
        return builder.append("Hello ").append(name).append("! Please Click Link for verification.\n\n")
            .append("http://localhost:8081/signup/" + type + "customer/verify?email=")
            .append(email)
            .append("&code=")
            .append(code).toString();
    }

}
