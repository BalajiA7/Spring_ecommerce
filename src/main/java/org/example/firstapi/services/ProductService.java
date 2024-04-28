package org.example.firstapi.services;

import org.example.firstapi.models.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(long id);
    List<Product> getAllProducts();
}
