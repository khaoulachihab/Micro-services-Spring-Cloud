package com.example.billingservice.repository;

import com.example.billingservice.entity.Bill;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;


import java.util.List;

@RepositoryRestResource
public interface BillRepository extends JpaRepository<Bill,Long> {

    @RestResource(path = "/byCustomerID")

    List<Bill> findByCustomerID(@Param("customerID") Long customerID);


}
