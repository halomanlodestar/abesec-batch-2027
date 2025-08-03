package com.completeinterview.demo.factories;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentGatewayFactory {

    private static final Map<String, PaymentGatewayInterface> registry = new HashMap<String, PaymentGatewayInterface>();

    public static void register(String name, PaymentGatewayInterface paymentGateway) {
        registry.put(name, paymentGateway);
    }

    public static PaymentGatewayInterface getPaymentGateway(String mode) {
        return registry.get(mode);
    }
}
