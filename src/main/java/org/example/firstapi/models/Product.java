package org.example.firstapi.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "products")
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String title;
  private double price;
  private String description;
  private String image;
  @ManyToOne
  private Category category;
}
