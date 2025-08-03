package com.completeinterview.demo.service;

import com.completeinterview.demo.factories.PaymentGatewayFactory;
import com.completeinterview.demo.factories.PaymentGatewayInterface;
import com.completeinterview.demo.factories.payment_gateways.GooglePay;
import com.completeinterview.demo.factories.payment_gateways.Paytm;
import com.completeinterview.demo.factories.payment_gateways.PhonePe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    PaymentGatewayFactory paymentGatewayFactory;

    PaymentService(@Autowired PaymentGatewayFactory paymentGatewayFactory) {

        this.paymentGatewayFactory = paymentGatewayFactory;

        paymentGatewayFactory.register("phonepe", new PhonePe());
        paymentGatewayFactory.register("googlepay", new GooglePay());
        paymentGatewayFactory.register("paytm", new Paytm());
    }

    public boolean pay(String mode) {
        PaymentGatewayInterface paymentGateway = paymentGatewayFactory.getPaymentGateway(mode);
        return paymentGateway.pay();
    }

    private boolean validate() {
        return true;
    }
}
