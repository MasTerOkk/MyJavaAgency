package com.example.agency.service;

import com.example.agency.entity.Cart;
import com.example.agency.repositories.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    public void saveInCart(Cart cart) {
        cartRepository.save(cart);
    }
}
