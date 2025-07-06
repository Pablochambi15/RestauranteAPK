package com.RestauranteWeb.restauranteweb.controller;

import com.RestauranteWeb.restauranteweb.model.Menu;
import com.RestauranteWeb.restauranteweb.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/menus")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping
    public String listarMenus(Model model) {
        model.addAttribute("menus", menuService.listarMenus());
        return "menus";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoMenu(Model model) {
        model.addAttribute("menu", new Menu());
        return "form_menu";
    }

    @PostMapping
    public String guardarMenu(@ModelAttribute Menu menu) {
        menuService.guardarMenu(menu);
        return "redirect:/menus";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarMenu(@PathVariable Long id, Model model) {
        Optional<Menu> menu = menuService.buscarPorId(id);
        if (menu.isPresent()) {
            model.addAttribute("menu", menu.get());
            return "form_menu";
        }
        return "redirect:/menus";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarMenu(@PathVariable Long id, @ModelAttribute Menu menu) {
        menu.setId(id);
        menuService.guardarMenu(menu);
        return "redirect:/menus";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarMenu(@PathVariable Long id) {
        menuService.eliminarMenu(id);
        return "redirect:/menus";
    }

    @PostMapping("/estado/{id}")
    public String cambiarEstado(@PathVariable Long id, @RequestParam String estado) {
        menuService.actualizarEstado(id, estado);
        return "redirect:/menus";
    }
} 