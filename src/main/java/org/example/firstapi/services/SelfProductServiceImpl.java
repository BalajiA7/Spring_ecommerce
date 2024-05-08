package org.example.firstapi.services;

import org.example.firstapi.Repositories.ProductRepository;
import org.example.firstapi.exceptions.ProductNotFoundException;
import org.example.firstapi.models.Category;
import org.example.firstapi.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProduct")
public class SelfProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    @Autowired
    public SelfProductServiceImpl(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    @Override
    public Product getProductById(long id) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()){
            throw new ProductNotFoundException("Invalid Product id");
        }
        return product.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(String title, String description, String image, double price, String categoryName) {
        Category category = categoryService.createCategory(categoryName);
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setImage(image);
        product.setPrice(price);
        product.setCategory(category);
        return productRepository.save(product);
    }

    @Override
    public Product updatePrice(long productId, double price) {
        return null;
    }

    @Override
    public Product updateImage(long productId, String image) {
        return null;
    }

    @Override
    public boolean deleteProduct(long id) {
        return false;
    }
}
