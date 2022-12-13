package com.example.billingservice.web;

import com.example.billingservice.entity.Bill;
import com.example.billingservice.exeption.ProductItemNotFoundException;
import com.example.billingservice.feign.CustomerServiceClient;
import com.example.billingservice.feign.InventoryServiceClient;
import com.example.billingservice.model.Customer;
import com.example.billingservice.model.Product;
import com.example.billingservice.repository.BillRepository;
import com.example.billingservice.repository.ProductItemRepository;
import com.example.billingservice.service.ProductItemService;

import org.springframework.web.bind.annotation.*;


@RestController
public class BillingRestController {
    private BillRepository billRepository;
    private ProductItemRepository productItemRepository;
    private CustomerServiceClient customerServiceClient;
    private InventoryServiceClient inventoryServiceClient;
    private ProductItemService productItemService;

    public BillingRestController(BillRepository billRepository, ProductItemRepository productItemRepository, CustomerServiceClient customerServiceClient, InventoryServiceClient inventoryServiceClient, ProductItemService productItemService) {
        this.billRepository = billRepository;
        this.productItemRepository = productItemRepository;
        this.customerServiceClient = customerServiceClient;
        this.inventoryServiceClient = inventoryServiceClient;
        this.productItemService = productItemService;

    }



    @GetMapping(path="fullBill/{id}")
    public Bill getBill(@PathVariable(name="id") Long id) throws ProductItemNotFoundException {
        Bill bill =billRepository.findById(id).get();

        Customer customer=customerServiceClient.getCustomerById(bill.getCustomerID());
        customerServiceClient.findAll().forEach(System.out::println);
        System.out.println(customer.getName());
        bill.setCustomer(customer);

        bill.getProductItems().forEach(pi->{
            System.out.println("id est "+pi.getId());
            Product product=inventoryServiceClient.getProductById(pi.getProductID());
            pi.setProduct(product);
            pi.setProductName(product.getName());
        });

        return bill;
    }
}
