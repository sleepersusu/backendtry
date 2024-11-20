package com.example.bistro.lotteryWinners;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.bistro.campaign.Campaign;
import com.example.bistro.campaignPrize.CampaignPrizes;
import com.example.bistro.lotteryChance.LotteryChance;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "LotteryWinners")
public class LotteryWinners {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	
	@OneToOne
	@JoinColumn(name = "campaignId")
	private Campaign campaign;
	
	@OneToOne
	@JoinColumn(name = "prizeId")
	private CampaignPrizes campaignPrizes;
	
	@OneToOne
	@JoinColumn(name = "lotteryChanceId")
	private LotteryChance lotteryChance;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") 
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdAt")
	private Date createdAt;
	
	@PrePersist 
	public void onCreate() {
		if(createdAt == null) {
			createdAt = new Date();
		}		
	}

	public LotteryWinners() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Campaign getCampaign() {
		return campaign;
	}

	public void setCampaign(Campaign campaign) {
		this.campaign = campaign;
	}

	public CampaignPrizes getCampaignPrizes() {
		return campaignPrizes;
	}

	public void setCampaignPrizes(CampaignPrizes campaignPrizes) {
		this.campaignPrizes = campaignPrizes;
	}

	public LotteryChance getLotteryChance() {
		return lotteryChance;
	}

	public void setLotteryChance(LotteryChance lotteryChance) {
		this.lotteryChance = lotteryChance;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

}
