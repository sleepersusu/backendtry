package com.example.bistro.orders;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepositoryDao extends JpaRepository<Orders, Integer> {

}
