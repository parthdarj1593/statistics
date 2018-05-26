package com.n26.statistics.statistics.Interface;

import java.util.Map;

import com.n26.statistics.statistics.model.Transaction;

public interface StatisticsServiceInterface {

	 Transaction addTransaction(Transaction t);
	 void deleteTransactions();
	 Map<String, Number> getStatistics();
}
