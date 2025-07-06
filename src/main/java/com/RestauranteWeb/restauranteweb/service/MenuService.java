package com.RestauranteWeb.restauranteweb.service;

import com.RestauranteWeb.restauranteweb.model.Menu;
import com.RestauranteWeb.restauranteweb.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuService {
    @Autowired
    private MenuRepository menuRepository;

    public List<Menu> listarMenus() {
        return menuRepository.findAll();
    }

    public Menu guardarMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    public Optional<Menu> buscarPorId(Long id) {
        return menuRepository.findById(id);
    }

    public void eliminarMenu(Long id) {
        menuRepository.deleteById(id);
    }

    public Menu actualizarEstado(Long id, String estado) {
        Optional<Menu> menuOpt = menuRepository.findById(id);
        if (menuOpt.isPresent()) {
            Menu menu = menuOpt.get();
            menu.setEstado(estado);
            return menuRepository.save(menu);
        }
        return null;
    }
} 