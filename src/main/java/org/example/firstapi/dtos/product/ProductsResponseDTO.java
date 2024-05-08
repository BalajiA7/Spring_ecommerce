package org.example.firstapi.dtos.product;

import lombok.Data;
import org.example.firstapi.dtos.ResponseStatus;
import org.example.firstapi.models.Product;

import java.util.List;

@Data
public class ProductsResponseDTO {
    private List<Product> products;
    private String message;
    private ResponseStatus responseStatus;
}
