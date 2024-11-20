package com.example.bistro.controller;

import com.example.bistro.model.Orders;
import com.example.bistro.service.OrdersService;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import java.util.Date;
import java.util.List;

@Controller
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @GetMapping("/orders/list")
    public String listOrders(Model model) {
       List<Orders> listOrders = ordersService.findAllOrders();
       model.addAttribute("listOrders", listOrders);
       return "orders/listOrders";
    }








}
