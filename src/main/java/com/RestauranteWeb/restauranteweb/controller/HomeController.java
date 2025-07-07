package com.RestauranteWeb.restauranteweb.controller;

import com.RestauranteWeb.restauranteweb.model.MenuItem;
import com.RestauranteWeb.restauranteweb.model.User;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.RestauranteWeb.restauranteweb.service.MenuItemService;
import com.RestauranteWeb.restauranteweb.service.MesaService;
import com.RestauranteWeb.restauranteweb.service.UserService;


@Controller
public class HomeController {

    private final MesaService mesaService;


        private final UserService userService;
    
      private final MenuItemService menuItemService; 

    public HomeController(UserService userService, MenuItemService menuItemService, MesaService mesaService) {
        this.userService = userService;
        this.menuItemService = menuItemService;
        this.mesaService = mesaService;
    }
    
    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }
    
    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }
    
@PostMapping("/register")
public String registerUser(@ModelAttribute User user) {
    System.out.println("Registrando usuario: " + user.getUsername());

    // Verificar si el usuario ya existe usando Optional
    if (userService.findByUsername(user.getUsername()).isPresent()) {
        System.out.println("Error: El nombre de usuario ya está en uso.");
        return "redirect:/register?error"; // Redirige con mensaje de error
    }

    User savedUser = userService.save(user);
    System.out.println("Usuario guardado: " + savedUser.getUsername());

    return "redirect:/login?success";
}


@GetMapping("/empleadosHome")
public String empleados() {
    return "empleados"; 
}

    
     @GetMapping("/home")
    public String home(Model model) {
    long totalPlatos = menuItemService.obtenerTodosLosItems().size(); 
    long disponibles = menuItemService.obtenerTodosLosItems()
                                      .stream()
                                      .filter(MenuItem::isDisponible)
                                      .count(); 

    model.addAttribute("totalMenuItems", totalPlatos);
    model.addAttribute("availableMenuItems", disponibles);

    int totalMesas = mesaService.obtenerTodasLasMesas().size();
    int mesasOcupadas = mesaService.contarMesasPorEstado("Ocupada");
    int mesasDisponibles = mesaService.contarMesasDisponibles(); 

    // Agrega al modelo
    model.addAttribute("totalMenuItems", totalPlatos);
    model.addAttribute("availableMenuItems", disponibles);
    model.addAttribute("totalTables", totalMesas);
    model.addAttribute("occupiedTables", mesasOcupadas);
    model.addAttribute("availableTables", mesasDisponibles);

    return "home";
}


}
