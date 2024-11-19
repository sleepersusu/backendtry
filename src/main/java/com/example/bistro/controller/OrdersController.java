package com.example.bistro.controller;

import com.example.bistro.model.Orders;
import com.example.bistro.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import java.util.Date;
import java.util.List;

@Controller
public class OrdersController {

    @Autowired
    private OrdersService ordersService;
    @Autowired
    private StandardServletMultipartResolver standardServletMultipartResolver;

    @PostMapping("/addPost")
    public String addOrder(@RequestParam("ordersName") String ordersName,
                           @RequestParam("ordersTel") String ordersTel,
                           @RequestParam("ordersSumPrice") Integer ordersSumPrice,
                           @RequestParam("pointGetted") Integer pointGetted,
                           @RequestParam("ordersStatus") String ordersStatus,
                           @RequestParam("paymentWay") String paymentWay,
                           @RequestParam("paymentStatus") String paymentStatus,
                           @RequestParam("paymentTime") Date paymentTime,
                           @RequestParam("memberId") Integer memberId,
                           @RequestParam("seatsId") Integer seatsId,
                           @RequestParam("employeeId") Integer employeeId) {
        // 呼叫服務層方法來處理新增訂單邏輯
        ordersService.insertOrders(ordersName, ordersTel, ordersSumPrice, pointGetted, ordersStatus,
                new Date(), paymentWay, paymentStatus, paymentTime,
                memberId, seatsId, employeeId);

        // 返回處理後的視圖，這裡假設返回的是訂單新增成功頁面
        return "orders/addOrderView";  // 不需要加 .html，Spring Boot 自動處理視圖解析
    }

    // 所有訂單
    @GetMapping("/showAllOrders")
    public String showAllOrders(Model model) {
        List<Orders> allOrders = ordersService.showAllOrders();
        model.addAttribute("ordersList", allOrders);

        return "orders/showAllOrdersView";
    }


}
