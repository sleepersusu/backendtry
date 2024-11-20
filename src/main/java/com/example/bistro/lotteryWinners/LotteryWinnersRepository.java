package com.example.bistro.lotteryWinners;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LotteryWinnersRepository extends JpaRepository<LotteryWinners, Integer> {
	
	@Query(value = "SELECT lw.ID, lc.orderId AS '訂單編號', " +
            "m.Id AS '會員ID', m.memberName AS '會員姓名', " +
            "c.campaignTitle, p.prizeName, lw.createdAt " +
            "FROM [LotteryWinners] lw " +
            "JOIN Campaign c ON c.ID = lw.campaignId " +
            "JOIN CampaignPrizes p ON p.ID = lw.prizeId " +
            "JOIN LotteryChance lc ON lc.ID = lw.lotteryChanceId " +
            "JOIN Orders o ON lc.orderId = o.ID " +
            "JOIN Members m ON o.memberId = m.ID", nativeQuery = true)
	List<Object[]> findAllWinners();
}
