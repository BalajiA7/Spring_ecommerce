package org.example.firstapi.dtos.product;

import lombok.Data;
import org.example.firstapi.dtos.ResponseStatus;
import org.example.firstapi.models.Product;

@Data
public class ProductResponseDTO {
    private Product product;
    private String message;
    private ResponseStatus responseStatus;
}
