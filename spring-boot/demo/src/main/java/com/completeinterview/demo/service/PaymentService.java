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

    PaymentService() {
        PaymentGatewayFactory.register("phonepe", new PhonePe());
        PaymentGatewayFactory.register("googlepay", new GooglePay());
        PaymentGatewayFactory.register("paytm", new Paytm());
    }

    public boolean pay(String mode) {
        PaymentGatewayInterface paymentGateway = PaymentGatewayFactory.getPaymentGateway(mode);

        if (paymentGateway == null) return false;

        return paymentGateway.pay();
    }

    private boolean validate() {
        return true;
    }
}
