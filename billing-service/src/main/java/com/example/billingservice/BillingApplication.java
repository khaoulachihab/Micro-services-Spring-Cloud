package com.example.billingservice;

import com.example.billingservice.entity.Bill;
import com.example.billingservice.entity.ProductItem;
import com.example.billingservice.feign.CustomerServiceClient;
import com.example.billingservice.feign.InventoryServiceClient;
import com.example.billingservice.model.Customer;
import com.example.billingservice.model.Product;
import com.example.billingservice.repository.BillRepository;
import com.example.billingservice.repository.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.PagedModel;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
@EnableFeignClients
public class BillingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingApplication.class, args);

	}

	@Bean
	CommandLineRunner start(

			BillRepository billRepository,
			ProductItemRepository productItemRepository,
			CustomerServiceClient customerRestClient,
			InventoryServiceClient productItemRestClient ){
					return args -> {
			Customer customer=customerRestClient.getCustomerById(1L);
			Bill bill1=billRepository.save(new Bill(null,new Date(),null,customer.getId(),null));
			PagedModel<Product> productPagedModel=productItemRestClient.findAll();
						AtomicInteger i= new AtomicInteger(1);
						PagedModel<Product>prductPageModel=productItemRestClient.findAll();
						prductPageModel.forEach(p->{
									ProductItem productItem=new ProductItem();
									productItem.setProductID(i.getAndIncrement());
									productItem.setPrice(p.getPrice());
									productItem.setQuantity(1+new Random().nextInt(100));
									productItem.setBill(bill1);
									productItemRepository.save(productItem);
								}
						);



};}}
