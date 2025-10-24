package online.threadly.order_and_cart_management.service;

import jakarta.ws.rs.BadRequestException;
import lombok.AllArgsConstructor;
import online.threadly.order_and_cart_management.dto.AddToCartRequest;
import online.threadly.order_and_cart_management.dto.AddToCartResponse;
import online.threadly.order_and_cart_management.model.Cart;
import online.threadly.order_and_cart_management.model.CartItem;
import online.threadly.order_and_cart_management.repository.CartItemRepository;
import online.threadly.order_and_cart_management.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public AddToCartResponse addToCart(UUID userId, AddToCartRequest request) {
        if(request.getQuantity() == null || request.getQuantity() <= 0) {
            throw new BadRequestException("Quantity must be greater than 0");
        }

        Cart cart = cartRepository.findByUserId(userId).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUserId(userId);
            return cartRepository.save(newCart);
        });

        CartItem cartItem = cartItemRepository.findByCartIdAndProductId(cart.getId(), request.getProductId()).orElseGet(() -> {
            CartItem newCartItem = new CartItem();
            newCartItem.setCart(cart);
            newCartItem.setProductId(request.getProductId());
            newCartItem.setQuantity(0);
            return newCartItem;
        });

        cartItem.setQuantity(cartItem.getQuantity() + request.getQuantity());
        cartItemRepository.save(cartItem);

        return new AddToCartResponse("Product added to cart successfully", cart.getId());
    }
}
