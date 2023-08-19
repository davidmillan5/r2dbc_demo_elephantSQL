package com.reactive.elephantr2dbccrud.repository;

import com.reactive.elephantr2dbccrud.entity.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ProductRepository extends ReactiveCrudRepository<Product, Integer> {
}
