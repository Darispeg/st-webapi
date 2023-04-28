package com.example.api_rest.controller;

import com.example.api_rest.model.PaymentRequest;
import com.example.api_rest.service.StripeService;
import com.example.api_rest.util.Response;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/payment")
public class PaymentController {

    @Autowired
    private StripeService _stripeService;

    @PostMapping
    public Response createPayment(@RequestBody PaymentRequest payment) throws StripeException {
        Stripe.apiKey = "sk_test_51Kw20HLDNhqIEF9IhlFVQYJzqldpeYQW0QhByitGub8VDetaeXnJAZyBIavVlGfWFY4A05jzUISCwyet5ZrVzuSF00KZ5Jt31l";

        Map<String, Object> card = new HashMap<>();
        card.put("number", payment.getCardRequest().getNumber());
        card.put("exp_month", payment.getCardRequest().getExp_month());
        card.put("exp_year", payment.getCardRequest().getExp_year());
        card.put("cvc", payment.getCardRequest().getCvc());
        Map<String, Object> params = new HashMap<>();
        params.put("card", card);

        Token token = Token.create(params);
        System.out.println(token.getId());

        String detail = "Pago realizado por: " + payment.getName() + ", email: " + payment.getEmail() + ", productos: [" + payment.getDetails() + "]";

        String chargeId = _stripeService.createCharge(detail, token.getId(), payment.getTotal(), payment.getName());

        if (chargeId == null) {
            return new Response(false, "Se produjo un error al cargar.");
        }

        // Almacena datos del pedido

        return new Response(true, "Pago Exitoso, su codigo de pago es: " + chargeId);
    }
}
