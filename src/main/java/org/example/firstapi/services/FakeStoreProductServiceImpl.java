package org.example.firstapi.services;

import org.example.firstapi.dtos.product.FakeProductServiceDto;
import org.example.firstapi.models.Category;
import org.example.firstapi.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeProduct")
public class FakeStoreProductServiceImpl implements ProductService {

    private final RestTemplate restTemplate;

    @Autowired
    public FakeStoreProductServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = new RestTemplate();
    }

    private Product convertProductDtoToProduct(FakeProductServiceDto fakeProductServiceDto) {
        Product product = new Product();
        product.setId(fakeProductServiceDto.getId());
        product.setTitle(fakeProductServiceDto.getTitle());
        product.setDescription(fakeProductServiceDto.getDescription());
        product.setPrice(fakeProductServiceDto.getPrice());
        Category category = new Category();
        category.setName(fakeProductServiceDto.getCategory());
        product.setCategory(category);
        return product;
    }


    @Override
    public Product getProductById(long id) {
        // RestTemplate restTemplate = new RestTemplate();
        // Response will be converted in to a class
        FakeProductServiceDto product = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeProductServiceDto.class);
        // Class will be converted in to a JSON
        return convertProductDtoToProduct(product);
    }

    @Override
    public List<Product> getAllProducts() {
        //RestTemplate restTemplate = new RestTemplate();
        FakeProductServiceDto[] products = restTemplate.getForObject("https://fakestoreapi.com/products/", FakeProductServiceDto[].class);
        List<Product> productList = new ArrayList<>();
        for(FakeProductServiceDto fakeProductServiceDto : products) {
            Product product = convertProductDtoToProduct(fakeProductServiceDto);
            productList.add(product);
        }
        return productList;
    }

    @Override
    public Product createProduct(String title, String description, String image, double price, String category) {
        return null;
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
