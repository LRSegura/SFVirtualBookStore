package com.dev2ever.service;

import com.dev2ever.api.rest.JsonBuy;
import com.dev2ever.model.Book;
import com.dev2ever.model.Buy;
import com.dev2ever.repository.RepositoryBook;
import com.dev2ever.repository.RepositoryBuy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceBuy {

    private final RepositoryBuy repositoryBuy;
    private final RepositoryBook repositoryBook;

    public ServiceBuy(RepositoryBuy repositoryBuy, RepositoryBook repositoryBook) {
        this.repositoryBuy = repositoryBuy;
        this.repositoryBook = repositoryBook;
    }
    public void createBuy(JsonBuy jsonBuy){
        Buy buy = new Buy();
        buy.setDescription(jsonBuy.description());
        buy.setPrice(jsonBuy.price());
        Book book = repositoryBook.findById(jsonBuy.idBook()).orElseThrow(()-> new RuntimeException("Book not found"));
        buy.setBook(book);
        buy.setAmount(jsonBuy.amount());
        repositoryBuy.save(buy);
    }

    public Buy findBuy(Long id){
        return repositoryBuy.findById(id).orElse(null);
    }

    public List<Buy> findAll(){
        return repositoryBuy.findAll();
    }

    public void deleteBuy(Long id){
        repositoryBuy.deleteById(id);
    }
}
