package com.app.bookstoremanagementsystem.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class BookResponse {
    private Long id;
    private String title;
    private String author;
    private String publisher;
    private String description;
    private String category;
    private String isbn;
    private String imageUrl;
    private BigDecimal price;
    private Integer stockQuantity;
    private boolean active;
}
