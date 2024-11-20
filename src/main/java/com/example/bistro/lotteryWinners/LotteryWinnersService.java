package com.example.bistro.lotteryWinners;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LotteryWinnersService {
	
	
	@Autowired
	private LotteryWinnersRepository lotteryWinnersRepo;
	
	public List<Object[]> findAllWinner(){
		return lotteryWinnersRepo.findAllWinners();
	}
	
	public LotteryWinners findById(Integer id) {
		Optional<LotteryWinners> op = lotteryWinnersRepo.findById(id);
		return op.isPresent() ? op.get() : null;
	}
	
	public LotteryWinners insertLotteryChance(LotteryWinners lotteryWinners) {
		return lotteryWinnersRepo.save(lotteryWinners);
	}
	
	public LotteryWinners updateLotteryChance(LotteryWinners lotteryWinners) {
		return lotteryWinnersRepo.save(lotteryWinners);
	}
	
	
	public void deleteChanceById(Integer id) {
		lotteryWinnersRepo.deleteById(id);
		
	}
}
