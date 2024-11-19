package com.example.bistro.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "OrderDetails")
public class OrderDetails {

    @EmbeddedId
    private OrderDetailsId orderDetailsPFKId; // 嵌入的複合主鍵

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("ordersId")
    private Orders orders;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("menuId")
    private Menu menu;


    private Integer odQuantity ;
    private Integer odPrice;
    private Integer odDiscount;

    private Integer commentRating;
    private String commentMessage ;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp commentDate ;

    public OrderDetails() {};

}
