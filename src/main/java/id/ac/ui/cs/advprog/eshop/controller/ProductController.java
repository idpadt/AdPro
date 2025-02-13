package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.exception.ProductNotFoundException;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;

import jakarta.validation.Valid;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    private int productId = 0;

    @Autowired
    private ProductService service;

    @GetMapping("/create")
    public String createProductPage(Model model){
        Product product = new Product();
        model.addAttribute("product", product);
        return "createProduct";
    }

    @PostMapping("/create")
    public String createProductPost(@ModelAttribute @Valid Product product, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "createProduct";
        }
        product.setProductId(Integer.toString(productId));
        productId++;
        service.create(product);
        return "redirect:list";
    }

    @GetMapping("/list")
    public String productListPage(Model model){
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "productList";
    }

    @GetMapping("/update/{id}")
    public String updateProductPost(@PathVariable int id, Model model){
        try {
            Product product = service.findById(id);
            model.addAttribute("product", product);
            return "updateProduct";
        } catch (ProductNotFoundException ex) {
            return "redirect:list";
        }
    }

    @PostMapping("/update")
    public String updateProductPost(@ModelAttribute @Valid Product product, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "updateProduct";
        }
        try {
            service.update(product);
            return "redirect:list";
        } catch (ProductNotFoundException ex) {
            return "redirect:list";
        }
    }
}