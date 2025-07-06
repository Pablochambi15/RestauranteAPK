package com.RestauranteWeb.restauranteweb.repository;

import com.RestauranteWeb.restauranteweb.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
} 