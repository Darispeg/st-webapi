package com.example.api_rest.service.category;

import com.example.api_rest.api.CategoryRepository;
import com.example.api_rest.db.entities.Category;
import com.example.api_rest.db.entities.Sale;
import com.example.api_rest.service.GenericServiceImpl;
import com.example.api_rest.service.purchase.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class CategoryServiceImpl extends GenericServiceImpl<Category, UUID> implements CategoryService {

    @Autowired
    CategoryRepository repository;

    @Override
    public CrudRepository<Category, UUID> getDao() {
        return repository;
    }


    @Override
    public Category saveCategory(Category category) {
        category.setKey(UUID.randomUUID());
        return getDao().save(category);
    }
}
