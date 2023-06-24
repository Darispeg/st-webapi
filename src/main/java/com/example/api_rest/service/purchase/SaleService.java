package com.example.api_rest.service.purchase;

import com.example.api_rest.db.entities.Sale;
import com.example.api_rest.service.GenericService;

import java.util.UUID;

public interface SaleService extends GenericService<Sale, UUID> {
    Sale saveSale(Sale purchase);
}
