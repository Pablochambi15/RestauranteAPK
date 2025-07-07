package com.RestauranteWeb.restauranteweb.repository;

import com.RestauranteWeb.restauranteweb.model.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Long> {

    // ✅ Cuenta cuántas mesas tienen un estado específico (sin importar mayúsculas)
    int countByEstadoIgnoreCase(String estado);
}
