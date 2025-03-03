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

    private final String redirectList = "redirect:list";
    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

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
        service.create(product);
        return redirectList;
    }

    @GetMapping("/list")
    public String productListPage(Model model){
        List<Product> allProducts = service.getAll();
        model.addAttribute("products", allProducts);
        return "productList";
    }

    @GetMapping("/update/{id}")
    public String updateProductPage(@PathVariable String id, Model model){
        try {
            Product product = service.getById(id);
            model.addAttribute("product", product);
            return "updateProduct";
        } catch (ProductNotFoundException ex) {
            return redirectList;
        }
    }

    @PostMapping("/update")
    public String updateProductPost(@ModelAttribute @Valid Product product, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "updateProduct";
        }
        try {
            service.update(product);
            return redirectList;
        } catch (ProductNotFoundException ex) {
            return redirectList;
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteProductPost(@PathVariable String id, Model model){
        try{
            Product product = service.getById(id);
            service.delete(product);
            return "redirect:/product/list";
        } catch (ProductNotFoundException ex) {
            return redirectList;
        }
    }
}