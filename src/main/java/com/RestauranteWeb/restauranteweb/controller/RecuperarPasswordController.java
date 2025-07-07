package com.RestauranteWeb.restauranteweb.controller;

import com.RestauranteWeb.restauranteweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RecuperarPasswordController {

    @Autowired
    private UserService userService;

    @GetMapping("/recuperar-password")
    public String mostrarFormularioRecuperar() {
        return "recuperar-password";
    }

    @PostMapping("/recuperar-password")
    public String procesarRecuperar(@RequestParam("email") String email, RedirectAttributes redirectAttributes) {
        // Redirige directamente al formulario de actualización con el correo como token
        return "redirect:/actualizar-password?token=" + email;
    }

    @GetMapping("/actualizar-password")
    public String mostrarActualizarPassword(@RequestParam(value = "token", required = false) String token, Model model) {
        model.addAttribute("token", token);
        return "actualizar-password";
    }

    @PostMapping("/actualizar-password")
    public String procesarActualizarPassword(@RequestParam("token") String token, @RequestParam("password") String password, RedirectAttributes redirectAttributes) {
        // Ahora el token es el correo
        boolean actualizado = userService.updatePasswordByCorreo(token, password);
        if (actualizado) {
            redirectAttributes.addAttribute("success", "true");
        } else {
            redirectAttributes.addAttribute("error", "true");
        }
        return "redirect:/actualizar-password?token=" + token;
    }
}
