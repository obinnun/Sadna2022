package com.example.demogame.product;

import com.example.demogame.cart.Cart;
import com.example.demogame.cart.CartRepository;
import com.example.demogame.cartProduct.CartProduct;
import com.example.demogame.cartProduct.CartProductRepository;
import com.example.demogame.user.User;
import com.example.demogame.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private CartProductRepository cartProductRepository;
    private CartRepository cartRepository;
    private UserRepository userRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, CartProductRepository cartProductRepository, CartRepository cartRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.cartProductRepository = cartProductRepository;
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public List<CartProduct> getCartProductsByUser(String mail){
        User user = userRepository.getUserByEmail(mail);
        Cart cart = cartRepository.getCartByUserId(user);

        return cartProductRepository.getCartProductsByCart(cart);
    }

    public Product addProduct(Product product){
        return productRepository.save(product);
    }

    public Product getProductByName(String productName){
//        Map<String, Object> query = new HashMap<>();
//        query.put("name", productName);
//        Product p = new Product("x",3,"");
        return productRepository.findByName(productName);
    }
}
