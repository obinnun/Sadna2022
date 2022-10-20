package com.example.demogame.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("product")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/getAll")
    public ResponseEntity getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/getCartProductsByCart")
    public  ResponseEntity getCartProductsByCart(HttpServletRequest request, HttpServletResponse response, @RequestHeader Object authorization){
        String email = request.getAttribute("username").toString();
         return ResponseEntity.ok(productService.getCartProductsByUser(email));
    }

    @PostMapping("/insert")
    public ResponseEntity addProduct(@RequestBody Product product){
        return ResponseEntity.ok(productService.addProduct(product));
    }

    @GetMapping()
    public ResponseEntity getProduct(@RequestParam String name){
        return ResponseEntity.ok(productService.getProductByName(name));
    }
}
