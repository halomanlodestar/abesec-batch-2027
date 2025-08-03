package com.completeinterview.demo.factories;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentGatewayFactory {

    private final Map<String, PaymentGatewayInterface> registry = new HashMap<String, PaymentGatewayInterface>();

    public void register(String name, PaymentGatewayInterface paymentGateway) {
        registry.put(name, paymentGateway);
    }

    public PaymentGatewayInterface getPaymentGateway(String mode) {
        return registry.get(mode);
    }
}
