package com.example.bistro.campaign;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CampaignService {
	
	@Autowired
	CampaignRepository campaignRepo;
	
	// finaAll
	public List<Campaign> findAllCampaign() {
		return campaignRepo.findAll();
	}
	
	// findById
	public Campaign findCampaignById(Integer id) {
		Optional<Campaign> op = campaignRepo.findById(id);
		
		return op.isPresent() ? op.get() : null;
	}
	
	// insertNewCampaign
	public Campaign insertCampaign(Campaign campaign) {
		return campaignRepo.save(campaign);
	}
	
	// updateCampaign
	public Campaign updateCampaign(Campaign campaign) {
		return campaignRepo.save(campaign);
	}
	
	// deleteCampaign
	public void deleteCampaignById(Integer id) {
		campaignRepo.deleteById(id);
	}
	
}
