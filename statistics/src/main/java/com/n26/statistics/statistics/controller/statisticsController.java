package com.n26.statistics.statistics.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.n26.statistics.statistics.Interface.StatisticsServiceInterface;

@RestController
public class statisticsController {
	
	@Autowired
	StatisticsServiceInterface service;

	@RequestMapping(value = "/statistics", method = RequestMethod.GET)
	public Map<String, Number> statistics() {
		Map<String, Number> statistics = service.getStatistics();
		return statistics;
	}
}
