package com.RestauranteWeb.restauranteweb.controller;

import com.RestauranteWeb.restauranteweb.model.Mesa;
import com.RestauranteWeb.restauranteweb.service.MesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/mesas")
public class MesaController {
    @Autowired
    private MesaService mesaService;

    @GetMapping
    public String listarMesas(Model model) {
        model.addAttribute("mesas", mesaService.listarMesas());
        return "mesas";
    }

    @GetMapping("/nueva")
    public String mostrarFormularioNuevaMesa(Model model) {
        model.addAttribute("mesa", new Mesa());
        return "form_mesa";
    }

    @PostMapping
    public String guardarMesa(@ModelAttribute Mesa mesa) {
        mesaService.guardarMesa(mesa);
        return "redirect:/mesas";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarMesa(@PathVariable Long id, Model model) {
        Optional<Mesa> mesa = mesaService.buscarPorId(id);
        if (mesa.isPresent()) {
            model.addAttribute("mesa", mesa.get());
            return "form_mesa";
        }
        return "redirect:/mesas";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarMesa(@PathVariable Long id, @ModelAttribute Mesa mesa) {
        mesa.setId(id);
        mesaService.guardarMesa(mesa);
        return "redirect:/mesas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarMesa(@PathVariable Long id) {
        mesaService.eliminarMesa(id);
        return "redirect:/mesas";
    }

    @PostMapping("/estado/{id}")
    public String cambiarEstado(@PathVariable Long id, @RequestParam String estado) {
        mesaService.actualizarEstado(id, estado);
        return "redirect:/mesas";
    }
} 