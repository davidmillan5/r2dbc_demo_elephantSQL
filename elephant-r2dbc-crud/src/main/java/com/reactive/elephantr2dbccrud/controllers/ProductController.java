package com.reactive.elephantr2dbccrud.controllers;

import com.reactive.elephantr2dbccrud.entity.Product;
import com.reactive.elephantr2dbccrud.services.ProductServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductServices productService;

    public ProductController(ProductServices productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public Flux<Product> getAll(){
        return this.productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public Mono<ResponseEntity<Product>> getProductById(@PathVariable int productId){
        return this.productService.getProductById(productId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public Mono<Product> createProduct(@RequestBody Mono<Product> productMono){
        return productMono.flatMap(this.productService::createProduct);
    }

    @PutMapping("/{productId}")
    public Mono<Product> updateProduct(@PathVariable int productId, @RequestBody Mono<Product> productMono){
        return this.productService.updateProduct(productId, productMono);
    }

    @DeleteMapping("/{productId}")
    public Mono<Void> deleteProduct(@PathVariable int productId){
        return this.productService.deleteProduct(productId);
    }

}
