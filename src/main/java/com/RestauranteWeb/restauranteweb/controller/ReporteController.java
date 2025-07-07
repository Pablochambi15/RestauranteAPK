package com.RestauranteWeb.restauranteweb.controller;

import com.RestauranteWeb.restauranteweb.model.Empleado;
import com.RestauranteWeb.restauranteweb.service.EmpleadoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ReporteController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping("/reportes")
    public String verReportes(Model model) {
        List<Empleado> empleados = empleadoService.listarEmpleados(); // usa el mismo método que ya tienes
        model.addAttribute("empleados", empleados);
        return "reportes";
    }
}
