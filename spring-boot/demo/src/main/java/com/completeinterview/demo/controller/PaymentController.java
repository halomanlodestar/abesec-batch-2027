package com.completeinterview.demo.controller;

import com.completeinterview.demo.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    final
    PaymentService paymentService;

    public PaymentController(@Autowired PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public boolean pay(@RequestParam String mode) {
        return paymentService.pay(mode);
    }

}
