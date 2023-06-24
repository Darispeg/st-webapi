package com.example.api_rest.service.purchase;

import com.example.api_rest.api.SaleRepository;
import com.example.api_rest.db.entities.Sale;
import com.example.api_rest.service.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class SaleServiceImpl extends GenericServiceImpl<Sale, UUID> implements SaleService {

    @Autowired
    private SaleRepository repository;

    @Override
    public CrudRepository<Sale, UUID> getDao() {
        return repository;
    }


    @Override
    public Sale saveSale(Sale purchase) {
        purchase.setKey(UUID.randomUUID());
        purchase.setCreatedDate(LocalDateTime.now());
        return getDao().save(purchase);
    }
}
