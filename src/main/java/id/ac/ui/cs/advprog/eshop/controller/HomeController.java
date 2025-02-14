package id.ac.ui.cs.advprog.eshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;

@Controller
public class HomeController {

    @GetMapping("/")
    public String createHomePage() {
        return "homePage";
    }
}
