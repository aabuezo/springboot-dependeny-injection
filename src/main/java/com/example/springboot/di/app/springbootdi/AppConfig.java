package com.example.springboot.di.app.springbootdi;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import com.example.springboot.di.app.springbootdi.repositories.ProductRepository;
import com.example.springboot.di.app.springbootdi.repositories.ProductRepositoryJson;

@Configuration
@PropertySources(@PropertySource("config.properties"))
public class AppConfig {

    // creamos un nuevo componente de Spring a partir de una clase cualquiera sin
    // anotar (esta implementa la interfaz ProductRepository)
    // el 'public' se puede omitir
    @Bean("productJson")
    ProductRepository productRepositoryJson() {
        return new ProductRepositoryJson();
    }
}
