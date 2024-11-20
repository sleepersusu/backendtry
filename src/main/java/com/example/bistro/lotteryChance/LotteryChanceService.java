package com.example.bistro.lotteryChance;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LotteryChanceService {
	
	@Autowired
	private LotteryChanceRepository lotteryChanceRepo;
	
	public List<LotteryChance> findAllChance() {
		return lotteryChanceRepo.findAll();
	}
	
	public List<Object[]> findMembersAllChance(){
		return lotteryChanceRepo.findMembersAllChance();
	}
	
	public LotteryChance findById(Integer id) {
		Optional<LotteryChance> op = lotteryChanceRepo.findById(id);
		return op.isPresent() ? op.get() : null;
	}
	
	public LotteryChance insertLotteryChance(LotteryChance lotteryChance) {
		return lotteryChanceRepo.save(lotteryChance);
	}
	
	public LotteryChance updateLotteryChance(LotteryChance lotteryChance) {
		return lotteryChanceRepo.save(lotteryChance);
	}
	
	
	public void deleteChanceById(Integer id) {
		lotteryChanceRepo.deleteById(id);
		
	}
}
