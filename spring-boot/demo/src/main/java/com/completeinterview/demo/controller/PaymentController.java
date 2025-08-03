package com.completeinterview.demo.controller;

import com.completeinterview.demo.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping
    public boolean phonepe(@RequestParam String mode) {
        return paymentService.pay(mode);
    }

}
