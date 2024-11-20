package com.example.bistro.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
