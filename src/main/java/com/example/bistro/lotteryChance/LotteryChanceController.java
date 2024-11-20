package com.example.bistro.lotteryChance;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bistro.orders.Orders;
import com.example.bistro.orders.OrdersRepository;

@Controller
public class LotteryChanceController {
	
	@Autowired
	LotteryChanceService lotteryChanceService;
	
	@Autowired
	OrdersRepository ordersRepo;
	
	@GetMapping("/Bistro/campaign/chance/findAll")
	public String findAll(Model model) {
	    List<Object[]> results = lotteryChanceService.findMembersAllChance();
	    model.addAttribute("lotteryChances", results);
	    
	    return "campaign/lotteryChanceView";
	}
	
	@PostMapping("/Bistro/campaign/chance/create")
	public String insertChanceInfo(@RequestParam Integer ordersId,
								@RequestParam Integer lotteryChance,
								@RequestParam String status ) {
		LotteryChance lotteryChanceBean = new LotteryChance();
		Optional<Orders> orders = ordersRepo.findById(ordersId);
		
		lotteryChanceBean.setOrders(orders.get());
		lotteryChanceBean.setLotteryChances(lotteryChance);
		lotteryChanceBean.setStatus(status);
		
		lotteryChanceService.insertLotteryChance(lotteryChanceBean);
		
		return "redirect:/Bistro/campaign/chance/findAll";
	}
	
	@PostMapping("/Bistro/campaign/chance/update")
	public String updateChanceInfo(@RequestParam Integer lotteryChance,
									@RequestParam String status,
									@RequestParam Integer id) {
		LotteryChance lotteryChanceBean = lotteryChanceService.findById(id);
		lotteryChanceBean.setStatus(status);
		lotteryChanceBean.setLotteryChances(lotteryChance);
		
		lotteryChanceService.updateLotteryChance(lotteryChanceBean);
		
		return "redirect:/Bistro/campaign/chance/findAll";
	}
	
	@PostMapping("/Bistro/campaign/chance/delete")
	public String deleteChance(@RequestParam Integer id) {
		lotteryChanceService.deleteChanceById(id);
		
		return "redirect:/Bistro/campaign/chance/findAll";
	}
	
	
	
	

}
