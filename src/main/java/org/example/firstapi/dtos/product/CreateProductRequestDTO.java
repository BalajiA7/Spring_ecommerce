package org.example.firstapi.dtos.product;

import lombok.Data;

@Data
public class CreateProductRequestDTO {
    private String image;
    private String title;
    private String description;
    private double price;
    private String categoryName;
}
