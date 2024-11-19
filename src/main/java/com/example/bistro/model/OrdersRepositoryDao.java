package com.example.bistro.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepositoryDao extends JpaRepository<Orders, Integer> {

}
