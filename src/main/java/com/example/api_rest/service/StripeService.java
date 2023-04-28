package com.example.api_rest.service;

import com.stripe.Stripe;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StripeService {

    @Value("${stripe.key.secret}")
    private String API_SECRET_KEY;

    public String createCharge(String detail, String token, int amount, String name){

        String chargeId = null;

        try {
            Stripe.apiKey = API_SECRET_KEY;

            Map<String, Object> chargeParams = new HashMap<>();
            chargeParams.put("description", detail);
            chargeParams.put("currency", "BOB");
            chargeParams.put("amount", amount);
            chargeParams.put("source", token);

            Charge charge = Charge.create(chargeParams);

            chargeId = charge.getId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chargeId;
    }
}
