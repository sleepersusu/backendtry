package com.example.bistro.service;

import com.example.bistro.model.*;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrdersService {

    @Autowired
    private OrdersRepositoryDao ordersRepositoryDao;

    @Autowired
    private SeatsRepositoryDao seatsRepositoryDao;

    @Autowired
    private EmployeeRepositoryDao employeeRepositoryDao;

    @Autowired
    private MembersRepositoryDao membersRepositoryDao;



    //新增訂單
        public Orders insertOrders(
                String ordersName, String ordersTel, Integer ordersSumPrice,
                Integer pointGetted, String ordersStatus, Date createdAt, String paymentWay,
                String paymentStatus,Date paymentTime,
                Integer memberId,Integer seatsId,Integer employeeId) {
            //oders
                Orders orders = new Orders();
                        orders.setOrdersName(ordersName);
                        orders.setOrdersTel(ordersTel);
                        orders.setOrdersSumPrice(ordersSumPrice);
                        orders.setPointGetted(pointGetted);
                        orders.setOrdersStatus(ordersStatus);
                        orders.setCreatedAt(createdAt);
                        orders.setPaymentWay(paymentWay);
                        orders.setPaymentStatus(paymentStatus);
                        orders.setPaymentTime(paymentTime);
            //members
                Members member = membersRepositoryDao.findById(memberId).orElse(null);
                orders.setMembers(member);
            //seats
                Seats seats = seatsRepositoryDao.findById(seatsId).orElse(null);
                orders.setSeats(seats);
            //employee
                Employee employee = employeeRepositoryDao.findById(employeeId).orElse(null);
                orders.setEmployee(employee);

            return ordersRepositoryDao.save(orders);
        }
    //讀取所有訂單
        public List<Orders> showAllOrders() {
            return ordersRepositoryDao.findAll();
        }


    //編輯訂單


    
    //刪除訂單



}
