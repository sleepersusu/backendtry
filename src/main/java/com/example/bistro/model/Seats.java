package com.example.bistro.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "Seats")
public class Seats {
    //PK
        //座位號碼
            @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            private Integer ID;
    //欄位
        //座位類型
            private String seatType;


    public Seats() {};
}
