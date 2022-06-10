package com.bridgelabz.cartservice.service;


import com.bridgelabz.cartservice.dto.CartDTO;
import com.bridgelabz.cartservice.model.Cart;

import javax.validation.Valid;
import java.util.List;

public interface ICartService {
    Cart insertToCart(@Valid CartDTO dto);

    List<Cart> getAllCarts();

    Cart getCartByID(Integer cartId);

    Cart updateById(Integer cartId, @Valid CartDTO dto);

    Cart deleteById(Integer cartId);

}


