package com.n26.statistics.statistics.controller;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.n26.statistics.statistics.model.Transaction;
import com.n26.statistics.statistics.services.StatisticsService;

@RestController
public class TransactionsController {

	private static final Long ONE_MINUTE_MILLIS = 60 * 1000l;

	@Autowired
	StatisticsService service;

	@RequestMapping(value = "/transactions", method = RequestMethod.POST)
	public ResponseEntity<String> newTransaction(@RequestBody Transaction t) {
		System.out.println(ONE_MINUTE_MILLIS);
		
		long timeInMillis = LocalDateTime.now(Clock.systemUTC()).toEpochSecond(ZoneOffset.UTC) *1000;
		
		if (t.getTimestamp() < timeInMillis - ONE_MINUTE_MILLIS) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			service.addTransaction(t);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
	}

}
