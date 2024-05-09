package org.example.firstapi.controllers;

import org.example.firstapi.dtos.ResponseStatus;
import org.example.firstapi.dtos.product.*;
import org.example.firstapi.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;


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

    /* PATCH REQUEST */
    @PatchMapping("{id}/updatePrice")
    public UpdateProductPriceResponseDTO updateProductPrice(@PathVariable("id") long productId, @RequestBody UpdateProductPriceRequestDTO  updateProductPriceRequestDTO){
        double price = updateProductPriceRequestDTO.getPrice();
        UpdateProductPriceResponseDTO updateProductPriceResponseDTO = new UpdateProductPriceResponseDTO();
        try{
            updateProductPriceResponseDTO.setProduct(productService.updatePrice(productId, price));
            updateProductPriceResponseDTO.setMessage("price updated successfully for " + productId);
            updateProductPriceResponseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        }catch (Exception e) {
            updateProductPriceResponseDTO.setMessage(e.getMessage());
            updateProductPriceResponseDTO.setResponseStatus(ResponseStatus.FAILURE);
        }
        return updateProductPriceResponseDTO;
    }

    /* DELETE REQUEST */
    @DeleteMapping("/{id}")
    public DeleteProductResponseDTO deleteProduct(@PathVariable("id") long productId){
        DeleteProductResponseDTO responseDTO = new DeleteProductResponseDTO();
        try{
            productService.deleteProduct(productId);
            responseDTO.setMessage("Product with id " + productId + " deleted successfully");
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        }catch (Exception e) {
            responseDTO.setMessage(e.getMessage());
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDTO;
    }
}
