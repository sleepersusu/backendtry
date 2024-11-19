package com.example.bistro.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepositoryDao extends JpaRepository<Orders, Integer> {
}
