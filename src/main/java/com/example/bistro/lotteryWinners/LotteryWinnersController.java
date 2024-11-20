package com.example.bistro.lotteryWinners;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bistro.campaign.Campaign;
import com.example.bistro.campaign.CampaignService;
import com.example.bistro.campaignPrize.CampaignPrizeService;
import com.example.bistro.campaignPrize.CampaignPrizes;
import com.example.bistro.lotteryChance.LotteryChance;
import com.example.bistro.lotteryChance.LotteryChanceService;
import com.example.bistro.orders.Orders;
import com.example.bistro.orders.OrdersRepository;

@Controller
public class LotteryWinnersController {
	
	@Autowired
	private LotteryWinnersService lotteryWinnersService;
	
	@Autowired
	private LotteryChanceService lotteryChanceService;
	
	@Autowired
	private CampaignService campaignService;
	
	@Autowired
	private CampaignPrizeService campaignPrizeService;
	
	
	@GetMapping("/Bistro/campaign/winner/findAll")
	public String findAllWinner(Model model) {		
		List<Object[]> results = lotteryWinnersService.findAllWinner();
		List<LotteryChance> allChance = lotteryChanceService.findAllChance();
		List<CampaignPrizes> allCampaignPrizes = campaignPrizeService.findAllCampaignPrizes();
		
		model.addAttribute("allWinners", results);
		model.addAttribute("allChance", allChance);
		model.addAttribute("allCampaignPrizes", allCampaignPrizes);
		return "campaign/lotteryWinnersView";
	}
	
	@PostMapping("/Bistro/campaign/winner/create")
	public String insertWinner(@RequestParam Integer campaignId,
								@RequestParam Integer prizeId,
								@RequestParam Integer chanceId) {
		Campaign campaign = campaignService.findCampaignById(campaignId);
		CampaignPrizes prize = campaignPrizeService.findPrizeById(prizeId);
		LotteryChance chance = lotteryChanceService.findById(chanceId);
		
		LotteryWinners lotteryWinner = new LotteryWinners();
		lotteryWinner.setCampaign(campaign);
		lotteryWinner.setCampaignPrizes(prize);
		lotteryWinner.setLotteryChance(chance);
		
		lotteryWinnersService.insertLotteryChance(lotteryWinner);
		
		
		return "redirect:/Bistro/campaign/winner/findAll";
	}
	
	@PostMapping("/Bistro/campaign/winner/delete")
	public String deleteWinner(@RequestParam Integer id) {
		lotteryWinnersService.deleteChanceById(id);
		
		return "redirect:/Bistro/campaign/winner/findAll";
	}
	
	
	

}
