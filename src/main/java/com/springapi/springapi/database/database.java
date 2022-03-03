package com.springapi.springapi.database;

import com.springapi.springapi.model.Product;
import com.springapi.springapi.repositories.ProductRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class database {
    private static final Logger logger = LoggerFactory.getLogger(database.class);
    @Bean
    CommandLineRunner initDatabase(ProductRepository repository){
        return new CommandLineRunner() {
            public void run(String ...args) throws Exception{
               
            }
        };
    }
}
