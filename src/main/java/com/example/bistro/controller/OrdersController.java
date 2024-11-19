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

    // 新增訂單的 POST 請求處理
        @PostMapping("/addOrdersCount")
        public ResponseEntity<Orders> addOrderCount
            (@RequestParam String ordersName, @RequestParam String ordersTel,
             @RequestParam String eatStatus, @RequestParam Integer pointGetted,
             @RequestParam String ordersStatus, @RequestParam String paymentWay,
             @RequestParam String paymentStatus, @RequestParam Integer memberId,
             @RequestParam Integer seatsId, @RequestParam Integer employeeId) {
        // 創建訂單並返回結果
                Orders newOrder = ordersService.addOrdersCount(
                        ordersName, ordersTel, eatStatus, pointGetted, ordersStatus,
                        null, paymentWay, paymentStatus, null, memberId, seatsId, employeeId);

                return ResponseEntity.ok(newOrder);
    }







}
