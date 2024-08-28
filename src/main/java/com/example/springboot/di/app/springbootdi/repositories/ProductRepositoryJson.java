package com.example.springboot.di.app.springbootdi.repositories;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.example.springboot.di.app.springbootdi.models.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

// esta es una clase comun y corriente, no es un componente de spring
public class ProductRepositoryJson implements ProductRepository {

    private List<Product> list;

    // esto aca no funciona porque no es un componente de spring
    // se podria pasar a AppConfig que es un componente @Configuration
    // y pasar por constructor a ProductRepositoryJson, pero habria que
    // modificar o sobrecargar el constructor de abajo
    // @Value("classpath:json/product.json")
    // private Resource resource;

    public ProductRepositoryJson() {
        // este constructor obtiene el recurso de forma programatica usando
        // ClassPathResource
        Resource resource = new ClassPathResource("json/product.json");
        readValueJson(resource);
    }

    public ProductRepositoryJson(Resource resource) {
        // este lo obtiene de forma declarativa, usando anotaciones
        readValueJson(resource);
    }

    private void readValueJson(Resource resource) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            list = Arrays.asList(objectMapper.readValue(resource.getFile(), Product[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> findAll() {
        return list;
    }

    @Override
    public Product findById(Long id) {
        return list.stream().filter(p -> p.getId().equals(id)).findFirst().orElseThrow();
    }

}
