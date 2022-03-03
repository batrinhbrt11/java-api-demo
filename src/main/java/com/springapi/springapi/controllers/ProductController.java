package com.springapi.springapi.controllers;


import java.util.List;
import java.util.Optional;

import com.springapi.springapi.model.Product;
import com.springapi.springapi.model.ResponseObject;
import com.springapi.springapi.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="api/v1/Products")
public class ProductController {
    @Autowired
    private ProductRepository repository;
    
    @GetMapping("") //request is localhost:8080/api/v1/Products
   List<Product> getAllProducts(){
        return repository.findAll();
   }
   @GetMapping("/{id}")
   ResponseEntity<ResponseObject> findById(@PathVariable int id){
       Optional<Product> foundProduct = repository.findById(id);
       return foundProduct.isPresent() ? ResponseEntity.status(HttpStatus.OK).body(
        new ResponseObject("ok","san pham ton tai", foundProduct)
    ):ResponseEntity.status(HttpStatus.NOT_FOUND).body(
        new ResponseObject("fail","san pham khong ton tai", "")
    );
   }

   @PostMapping("/insert")
   ResponseEntity<ResponseObject> insertProduct(@RequestBody Product newProduct){
       List<Product> foundProducts = repository.findByProductName(newProduct.getProductName().trim());
       return (foundProducts.size() == 0) ? ResponseEntity.status(HttpStatus.OK).body(
        new ResponseObject("ok","insert product successfully",repository.save(newProduct))
    ): ResponseEntity.status(HttpStatus.NOT_FOUND).body(
        new ResponseObject("fail","insert product false",false));
   }
   @PutMapping("/{id}")
   ResponseEntity<ResponseObject> updateProduct(@RequestBody Product newProduct,@PathVariable Integer id){
    
        Product updatedProduct = repository.findById(id)
                .map(product->{
                    product.setProductName(newProduct.getProductName());
                    product.setPrice(newProduct.getPrice());
                    product.setYear(newProduct.getYear());
                    product.setUrl(newProduct.getUrl());
                    return repository.save(product);
                }).orElseGet(()->{
                    
                    return repository.save(newProduct);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
            new ResponseObject("ok","update product successfully",updatedProduct));
   }
   @DeleteMapping("/{id}")
   ResponseEntity<ResponseObject> deleteProduct(@PathVariable Integer id){
        boolean exists = repository.existsById(id);
        if(exists){
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","delete product successfully","")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseObject("fail","delete product false","")
        );
   }
}