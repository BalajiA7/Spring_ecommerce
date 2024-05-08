package org.example.firstapi.controllers;

import org.example.firstapi.dtos.ResponseStatus;
import org.example.firstapi.dtos.product.CreateProductRequestDTO;
import org.example.firstapi.dtos.product.CreateProductResponseDTO;
import org.example.firstapi.dtos.product.ProductResponseDTO;
import org.example.firstapi.dtos.product.ProductsResponseDTO;
import org.example.firstapi.models.Product;
import org.example.firstapi.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(@Qualifier("selfProduct") ProductService productService) {
        this.productService = productService;
    }

    /* GET REQUEST */
    @GetMapping("/{id}")
    public ProductResponseDTO getProductById(@PathVariable("id") long id) {
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        try{
            productResponseDTO.setProduct(productService.getProductById(id));
            productResponseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        }catch (Exception e){
            productResponseDTO.setMessage(e.getMessage());
            productResponseDTO.setResponseStatus(ResponseStatus.FAILURE);
        }
        return productResponseDTO;
    }

    @GetMapping("")
    public ProductsResponseDTO getProducts(){
        ProductsResponseDTO productsResponseDTO = new ProductsResponseDTO();
        productsResponseDTO.setProducts(productService.getAllProducts());
        productsResponseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        return productsResponseDTO;
    }

    /* POST REQUEST */
    @PostMapping("")
    public CreateProductResponseDTO createProduct(@RequestBody CreateProductRequestDTO createProductRequestDTO){
        // Basic Validation
        String title = createProductRequestDTO.getTitle();
        String description = createProductRequestDTO.getDescription();
        double price = createProductRequestDTO.getPrice();
        String image = createProductRequestDTO.getImage();
        String categoryName = createProductRequestDTO.getCategoryName();
        CreateProductResponseDTO productResponseDTO = new CreateProductResponseDTO();
        try{
           productResponseDTO.setProduct(productService.createProduct(title, description,image,price, categoryName));
           productResponseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        }catch (Exception e) {
            productResponseDTO.setMessage(e.getMessage());
            productResponseDTO.setResponseStatus(ResponseStatus.FAILURE);
        }
        return productResponseDTO;
    }
}
