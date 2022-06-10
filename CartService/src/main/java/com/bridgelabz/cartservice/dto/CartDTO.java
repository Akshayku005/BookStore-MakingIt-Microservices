package com.bridgelabz.cartservice.dto;

import lombok.Data;

public @Data class CartDTO {
    private int userId;
    private int bookId;
    private int quantity;
    private int cartID;
}

