package com.completeinterview.demo.factories.payment_gateways;

import com.completeinterview.demo.factories.PaymentGatewayInterface;

public class PhonePe implements PaymentGatewayInterface {
    @Override
    public boolean pay() {
        return true;
    }
}
