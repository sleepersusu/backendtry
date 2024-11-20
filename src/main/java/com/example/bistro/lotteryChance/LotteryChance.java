package com.example.bistro.lotteryChance;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.bistro.orders.Orders;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "LotteryChance")
public class LotteryChance {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "lotteryChances")
	private Integer lotteryChances;
	
	@Column(name = "status")
	private String status;	

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") 
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdAt")	
	private Date createdAt;
	
	@ManyToOne
	@JoinColumn(name = "orderId")
	private Orders orders;
	
	@PrePersist 
	public void onCreate() {
		if(createdAt == null) {
			createdAt = new Date();
		}		
	}
	

	public LotteryChance() {
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLotteryChances() {
		return lotteryChances;
	}

	public void setLotteryChances(Integer lotteryChances) {
		this.lotteryChances = lotteryChances;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

}
