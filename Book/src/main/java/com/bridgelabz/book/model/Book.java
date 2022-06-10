package com.bridgelabz.book.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.bridgelabz.book.dto.BookDTO;

import lombok.Data;
@Entity
@Table(name = "book_store")
public @Data  class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_Id")
    private Integer bookId;
    private String authorName;

    public String bookDescription;
    private String bookName;
    private String bookImg;
    private int price;
    private int quantity;


    public Book(BookDTO dto) {
        super();
        this.authorName = dto.getAuthorName();
        this.bookDescription=dto.getBookDescription();
        this.bookName = dto.getBookName();
        this.bookImg = dto.getBookImg();
        this.price = dto.getPrice();
        this.quantity = dto.getQuantity();
    }

    public Book(Integer bookId, BookDTO dto) {
        super();
        this.bookId = bookId;
        this.authorName = dto.getAuthorName();
        this.bookDescription=dto.getBookDescription();
        this.bookName = dto.getBookName();
        this.bookImg = dto.getBookImg();
        this.price = dto.getPrice();
        this.quantity = dto.getQuantity();
    }

    public Book() {
        super();
    }

}
