package com.example.springboot.di.app.springbootdi.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.example.springboot.di.app.springbootdi.models.Product;
import com.example.springboot.di.app.springbootdi.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    // La clase Services es donde se aplica la logica de negocio
    // private ProductRepositoryImpl repository = new ProductRepositoryImpl();
    // Principio Hollywood: no nos llames, nosotros te llamaremos
    // @Autowired // inyeccion de dependencias
    // @Qualifier("productFoo")
    private ProductRepository repository; // se usa la interfaz

    @Value("${config.price.tax}")
    private Double tax;

    private Environment environment;

    // con el constructor no hace falta el @Autowired
    // usar el constructor es mas limpio que usar el @Autowired
    // con @Qualifier le decimos que queremos que inyecte otra impl distinta a la
    // Primary
    public ProductServiceImpl(@Qualifier("productList") ProductRepository repository, Environment environment) {
        this.repository = repository;
        this.environment = environment;
    }

    @Override
    public List<Product> findAll() {
        // Aqui va la logica del negocio, por ejemplo agregar el IVA
        return repository.findAll().stream().map(p -> {
            // Double priceTax = p.getPrice() * environment.getProperty("config.price.tax",
            // Double.class);
            Double priceTax = p.getPrice() * tax;
            // Product newProduct = new Product(p.getId(), p.getName(), priceTax);
            // una forma mas limpia es clonando el objeto

            Product newProduct = (Product) p.clone();
            newProduct.setPrice(priceTax);
            return newProduct;

            // p.setPrice(priceTax); // esta linea modifica el precio original, por eso es
            // necesario crear y devolver un producto nuevo (newProduct)
            // return p;
        }).collect(Collectors.toList());
    }

    @Override
    public Product findById(Long id) {
        // Aqui va la logica del negocio
        return repository.findById(id);
    }

}
