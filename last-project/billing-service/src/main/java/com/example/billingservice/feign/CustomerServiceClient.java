package com.example.billingservice.feign;

import com.example.billingservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="customer-service")
public interface CustomerServiceClient {
    @GetMapping("/customers/{id}?projection=fullCustomer")
    Customer getCustomerById(@PathVariable("id") Long id);

    @GetMapping("/customers")
    PagedModel<Customer> findAll();

}
