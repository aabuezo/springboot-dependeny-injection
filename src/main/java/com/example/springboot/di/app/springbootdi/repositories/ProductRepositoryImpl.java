package com.example.springboot.di.app.springbootdi.repositories;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.springboot.di.app.springbootdi.models.Product;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    // La idea de la clase Repository es interactuar con la BD
    // sin aplicar logica de negocio
    private List<Product> data;

    public ProductRepositoryImpl() {
        this.data = Arrays.asList(
                new Product(1L, "Memoria corsair 32", 300.0),
                new Product(2L, "CPU Intel Core i9", 850.0),
                new Product(3L, "Teclado Razer Mini 60%", 180.0),
                new Product(4L, "Motherboard Gigabyte", 490.0));
    }

    @Override
    public List<Product> findAll() {
        return data;
    }

    @Override
    public Product findById(Long id) {
        return data.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }
}
