// filepath: c:\Users\jeffe\OneDrive\Documentos\Restaurante\RestauranteAPK\src\main\java\com\RestauranteWeb\restauranteweb\controller\PaginaPrincipalController.java
package com.RestauranteWeb.restauranteweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaginaPrincipalController {

    @GetMapping("/")
    public String home() {
        return "home"; // página principal
    }

    @GetMapping("/PaginaPrincipal")
    public String login() {
        return "PaginaPrincipal"; // la vista de login
    }
}