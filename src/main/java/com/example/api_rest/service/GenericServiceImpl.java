package com.example.api_rest.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public abstract class GenericServiceImpl<T, ID extends Serializable> implements GenericService<T, ID> {

    public abstract CrudRepository<T, ID> getDao();

    @Override
    public T save(T entity) {
        return getDao().save(entity);
    }

    @Override
    public void delete(ID id) {
        getDao().deleteById(id);
    }

    @Override
    public T findById(ID id) {
        Optional<T> obj = getDao().findById(id);
        if (obj.isPresent()) 
            return obj.get();
        return null;
    }

    @Override
    public List<T> findAll() {
        List<T> returnList = new ArrayList<>();
        getDao().findAll().forEach(obj -> returnList.add(obj));
        return returnList;
    }
    
}
