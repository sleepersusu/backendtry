package com.example.bistro.service;

import com.example.bistro.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrdersService {

    //依賴注入將要用到的Dao
        @Autowired
        private OrdersRepositoryDao ordersRepositoryDao;
        @Autowired
        private static SeatsRepositoryDao seatsRepositoryDao;
        @Autowired
        private static EmployeeRepositoryDao employeeRepositoryDao;
        @Autowired
        private static MembersRepositoryDao membersRepositoryDao;
        @Autowired
        private OrderDetailsService orderDetailsService; // 注入 OrderDetailsService

    //CURD
        //Read List 列出全部訂單
            public List<Orders> findAllOrders() {
                return ordersRepositoryDao.findAll();
            }
        //Read ID 每張表根據ID找尋
            public static Employee findEmpById(Integer ID) {
                Optional<Employee> optional = employeeRepositoryDao.findById(ID);
                if(optional.isPresent()) {
                return optional.get();
                }
                return null;
            }
            public static Members findMembersById(Integer ID) {
                Optional<Members> optional = membersRepositoryDao.findById(ID);
                if(optional.isPresent()) {
                return optional.get();
                }
                return null;
            }
            public static Seats findSeatsById(Integer ID) {
                Optional<Seats> optional = seatsRepositoryDao.findById(ID);
                if(optional.isPresent()) {
                    return optional.get();
                }
                return null;
            }




        //Create 新增訂單
            public Orders addOrders(String  ordersName,String  ordersTel,
                                    String  eatStatus,Integer ordersSumPrice,
                                    String ordersStatus,Integer pointGetted,
                                    Date createdAt,String paymentWay,String paymentStatus,
                                    Date paymentTime,Integer memberId,Integer seatsId,Integer employeeId) {

                Members member = OrdersService.findMembersById(memberId);
                Seats seats = OrdersService.findSeatsById(seatsId);
                Employee employee = OrdersService.findEmpById(employeeId);

                Orders orders = new Orders();
                orders.setOrdersName(ordersName);
                orders.setOrdersTel(ordersTel);
                orders.setEatStatus(eatStatus);
                orders.setOrdersSumPrice(ordersSumPrice);
                orders.setPointGetted(pointGetted);
                orders.setOrdersStatus(ordersStatus);
                orders.setCreatedAt(createdAt);
                orders.setPaymentWay(paymentWay);
                orders.setPaymentStatus(paymentStatus);
                orders.setPaymentTime(paymentTime);

                orders.setEmployee(employee);
                orders.setMembers(member);
                orders.setSeats(seats);

                return ordersRepositoryDao.save(orders);
            }

        //新增訂單並計算總金額
        public Orders addOrdersCount(String ordersName, String ordersTel, String eatStatus,
                                Integer pointGetted, String ordersStatus, Date createdAt,
                                String paymentWay, String paymentStatus, Date paymentTime,
                                Integer memberId, Integer seatsId, Integer employeeId){
            // 根據 memberId, seatsId, 和 employeeId 查找相關實體
                Members member = OrdersService.findMembersById(memberId);
                Seats seats = OrdersService.findSeatsById(seatsId);
                Employee employee = OrdersService.findEmpById(employeeId);
            // 查詢 orderdetails 來計算訂單總金額
                List<OrderDetails> orderDetails = orderDetailsService.findByOrderDetailsId(OrderDetailsId);
                Integer ordersSumPrice = calculateTotalPrice(orderDetails);
            // 創建新的訂單實體
                Orders orders = new Orders();
                orders.setOrdersName(ordersName);
                orders.setOrdersTel(ordersTel);
                orders.setEatStatus(eatStatus);
                orders.setOrdersSumPrice(ordersSumPrice);
                orders.setPointGetted(pointGetted);
                orders.setOrdersStatus(ordersStatus);

            // 自動設置 createdAt 和 paymentTime 為當前時間
                orders.setCreatedAt(createdAt != null ? createdAt : new Date());
                orders.setPaymentTime(paymentTime != null ? paymentTime : new Date());
                orders.setPaymentWay(paymentWay);
                orders.setPaymentStatus(paymentStatus);

            // 設置關聯
                orders.setMembers(member);
                orders.setSeats(seats);
                orders.setEmployee(employee);

            // 儲存訂單並返回
                return ordersRepositoryDao.save(orders);
        }
        // 計算訂單總金額
            private Integer calculateTotalPrice(List<OrderDetails> orderDetails) {
                int total = 0;
                for (OrderDetails detail : orderDetails) {
                    int quantity = detail.getOdQuantity();
                    int price = detail.getOdPrice();
                    int discount = detail.getOdDiscount();
                        total += (int) (quantity * price * (1 - (discount / 100.0)));
                }
                return total;
            }


        // 根據訂單 ID 計算總金額
            public Integer calculateTotalPrice(OrderDetailsId orderDetailsId) {
                List<OrderDetails> orderDetailsList = orderDetailsService.findByOrderDetailsId(orderDetailsId);
                int totalPrice = 0;
                for (OrderDetails orderDetails : orderDetailsList) {
                    int quantity = orderDetails.getOdQuantity();
                    int price = orderDetails.getOdPrice();
                    int discount = orderDetails.getOdDiscount();
                    // 計算每個商品的總金額，折扣按比例計算
                    totalPrice += (int) (quantity * price * (1 - (discount / 100.0)));
                }
                return totalPrice;
            }
        // 在新增訂單時計算總金額並設置
            public Orders addOrderWithTotalPrice(Orders orders, OrderDetailsId orderDetailsId) {
                Integer totalPrice = calculateTotalPrice(orderDetailsId);
                orders.setOrdersSumPrice(totalPrice);
                return orders;  // 保存訂單
            }


}

