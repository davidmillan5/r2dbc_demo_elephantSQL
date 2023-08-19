package com.reactive.elephantr2dbccrud.services;

import com.reactive.elephantr2dbccrud.entity.Product;
import com.reactive.elephantr2dbccrud.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductServices {

    private final ProductRepository repository;

    public Flux<Product> getAllProducts(){
        return this.repository.findAll();
    }

    public Mono<Product> getProductById(int productId){
        return this.repository.findById(productId);
    }

    public Mono<Product> createProduct(final Product product){
        return this.repository.save(product);
    }

    public Mono<Product> updateProduct(int productId, final Mono<Product> productMono){
        return this.repository.findById(productId)
                .flatMap(product -> productMono.map(unit -> {
                    product.setDescription(unit.getDescription());
                    product.setPrice(unit.getPrice());
                    return product;
                }))
                .flatMap(p -> this.repository.save(p));
    }

    public Mono<Void> deleteProduct(final int id){
        return this.repository.deleteById(id);
    }

}
