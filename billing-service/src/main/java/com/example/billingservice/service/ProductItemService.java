package com.example.billingservice.service;
import com.example.billingservice.repository.ProductItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
public class ProductItemService {
    @Autowired
    private ProductItemRepository repository;




}
