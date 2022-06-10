package com.bridgelabz.orderservice.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BookDTO {

    @NotNull(message = "book name cant be null")
    private String bookName;
    @NotNull (message = "price cant be empty")
    private Integer price;
    @NotNull(message = "Quantity cant be empty")
    private Integer quantity;

}
