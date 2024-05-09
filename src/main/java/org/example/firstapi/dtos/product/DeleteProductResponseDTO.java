package org.example.firstapi.dtos.product;

import lombok.Data;
import org.example.firstapi.dtos.ResponseStatus;

@Data
public class DeleteProductResponseDTO {
    private String message;
    private ResponseStatus responseStatus;
}
