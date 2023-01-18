package com.example.billingservice.feign;

import com.example.billingservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="inventory-service")
@RestController
public interface InventoryServiceClient {



    @GetMapping("/products/{id}?projection=fullProduct")
    Product findProductById(@PathVariable("id") Long id);

    @GetMapping("/products")
    PagedModel<Product> findAll();

    @GetMapping(path = "/products/{id}")
    Product getProductById(@PathVariable("id") Long id);








}
