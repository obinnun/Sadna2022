package com.example.demogame.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("from Product c where c.name=:name")
    Product findByName(@Param("name")String name);

    //List<Product> findAllById(int i);
}
