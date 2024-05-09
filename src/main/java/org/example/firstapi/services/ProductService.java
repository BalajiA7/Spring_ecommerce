package org.example.firstapi.services;

import org.example.firstapi.exceptions.ProductNotFoundException;
import org.example.firstapi.models.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(long id) throws ProductNotFoundException;
    List<Product> getAllProducts();
    public Product createProduct(String title, String description, String image, double price, String categoryName);
    public Product updatePrice(long productId, double price) throws ProductNotFoundException;
    public void deleteProduct(long id) throws ProductNotFoundException;
}
