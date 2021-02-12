package com.webimport.main.service;

import com.webimport.main.model.entity.Telefone;
import com.webimport.main.model.repository.TelefoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TelefoneService {
    @Autowired
    public TelefoneRepository repository;
    public Telefone insert(Telefone obj){
        try{
            return repository.save(obj);
        }catch(RuntimeException e){
            throw new IllegalStateException(e);
        }
    }
}
