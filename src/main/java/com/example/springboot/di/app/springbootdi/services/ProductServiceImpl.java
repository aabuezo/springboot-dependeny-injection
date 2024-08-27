package com.example.springboot.di.app.springbootdi.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.di.app.springbootdi.models.Product;
import com.example.springboot.di.app.springbootdi.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    // La clase Services es donde se aplica la logica de negocio
    // private ProductRepositoryImpl repository = new ProductRepositoryImpl();
    // Principio Hollywood: no nos llames, nosotros te llamaremos
    @Autowired // inyeccion de dependencias
    private ProductRepository repository; // se usa la interfaz

    @Override
    public List<Product> findAll() {
        // Aqui va la logica del negocio, por ejemplo agregar el IVA
        return repository.findAll().stream().map(p -> {
            Double priceTax = p.getPrice() * 1.21d;
            // Product newProduct = new Product(p.getId(), p.getName(), priceTax);
            // una forma mas limpia es clonando el objeto
            Product newProduct = (Product) p.clone();
            newProduct.setPrice(priceTax);
            return newProduct;
            // p.setPrice(priceTax); // esta linea modifica el precio original, por eso es
            // necesario crear y devolver un producto nuevo (newProduct)
        }).collect(Collectors.toList());
    }

    @Override
    public Product findById(Long id) {
        // Aqui va la logica del negocio
        return repository.findById(id);
    }

}
