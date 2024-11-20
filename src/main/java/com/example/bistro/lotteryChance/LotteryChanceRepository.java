package com.example.bistro.lotteryChance;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LotteryChanceRepository extends JpaRepository<LotteryChance, Integer> {

	@Query(value = "SELECT " +
            "    l.ID AS '抽獎ID', " +
            "    o.memberId AS '會員編號', " +
            "    o.ID AS '訂單編號', " +            
            "    SUM(l.lotteryChances) AS '獲得抽獎次數', " +
            "    SUM(SUM(l.lotteryChances)) OVER (PARTITION BY o.memberId) AS '總抽獎次數', " +
            "    l.status AS '使用期限' " +
            "FROM lotteryChance l " +
            "JOIN Orders o ON o.ID = l.orderId " +
            "GROUP BY o.memberId, o.ID, l.ID, l.status", nativeQuery = true)
List<Object[]> findMembersAllChance();

}
