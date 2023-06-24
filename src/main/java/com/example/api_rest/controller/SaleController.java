package com.example.api_rest.controller;

import com.example.api_rest.db.entities.Sale;
import com.example.api_rest.model.PaymentRequest;
import com.example.api_rest.service.StripeService;
import com.example.api_rest.service.purchase.SaleService;
import com.example.api_rest.util.Response;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/sales")
public class SaleController {

    @Autowired
    private StripeService _stripeService;

    @Autowired
    private SaleService _saleSerice;

    @PostMapping(value = "/payment")
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

        String chargeId = _stripeService.createCharge(detail, token.getId(), payment.getTotal() * 100, payment.getName());

        if (chargeId == null) {
            return new Response(false, "Se produjo un error al cargar.");
        }

        // Almacena datos del pedido
        try {
            _saleSerice.saveSale(mappingPurhcaseDate(payment, chargeId));
        } catch (Exception e) {
            return new Response(false, "Se produjo un error al guardar los datos");
        }

        return new Response(true, "Pago Exitoso, su codigo de pago es: " + chargeId);
    }

    private Sale mappingPurhcaseDate(PaymentRequest payment, String chargeId){
        Sale purchase = new Sale();
        purchase.setName(payment.getName());
        purchase.setEmail(payment.getEmail());
        purchase.setPhone(payment.getPhone());
        purchase.setDetails(payment.getDetails());
        purchase.setTotal(payment.getTotal());
        purchase.setChargeId(chargeId);
        return purchase;
    }

    @GetMapping
    public ResponseEntity<List<Sale>> getAll()
    {
        return ResponseEntity.ok(_saleSerice.findAll());
    }

}
