package com.bridgelabz.book.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class BookDTO {
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Invalid First Letter should be upperCase in BookName!!")
    private String authorName;
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Invalid First Letter should be upperCase in AuthorName!!")
    private String bookName;
    @NotBlank(message = "Please write Book description !!")
    public String bookDescription;
    private String bookImg;
    @NotNull(message = "price cant be empty")
    private int price;
    @NotNull(message = "price cant be empty")
    private int quantity;
}
