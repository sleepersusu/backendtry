package com.example.bistro.campaignPrize;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CampaignPrizeService {
	
	@Autowired
	CampaignPrizesRepository campaignPrizesRepo;
	
	
	public List<CampaignPrizes> findAllCampaignPrizes(){
		return campaignPrizesRepo.findAll();
	}
	
	public CampaignPrizes findPrizeById(Integer id) {
		Optional<CampaignPrizes> op = campaignPrizesRepo.findById(id);
		return op.isPresent() ? op.get() : null;
	}
	
	public CampaignPrizes insertPrize(CampaignPrizes campaignPrizes) {
		return campaignPrizesRepo.save(campaignPrizes);
	}
	
	public CampaignPrizes updatePrize(CampaignPrizes campaignPrizes) {
		return campaignPrizesRepo.save(campaignPrizes);
	}
	
	public void deletePrizeById(Integer id) {
		campaignPrizesRepo.deleteById(id);
	}
}
