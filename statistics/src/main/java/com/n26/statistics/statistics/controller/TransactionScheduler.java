package com.n26.statistics.statistics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.n26.statistics.statistics.services.StatisticsService;

@RestController
public class TransactionScheduler {

	@Autowired
	StatisticsService service;

	@RequestMapping(value = "/transactionclean")
	@Scheduled(cron = "${batch.fixedrate}")
	public void oldTransactionCleaner() {
		service.deleteTransactions();
	}
}
