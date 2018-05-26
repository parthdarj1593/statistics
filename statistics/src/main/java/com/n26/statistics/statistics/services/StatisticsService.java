package com.n26.statistics.statistics.services;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import com.n26.statistics.statistics.model.Transaction;

@Service
public class StatisticsService {

	private static List<Transaction> transcationList = new ArrayList<>();
	private static final Long ONE_MINUTE_MILLIS = 60 * 1000l;

	public Transaction addTransaction(Transaction t) {
		transcationList.add(t);
		return t;
	}

	public void deleteTransactions() {
		removeOldTransaction(transcationList.iterator(), isOld());
	}

	public static <T> void removeOldTransaction(final Iterator<T> it, final Predicate<T> predicate) {

		while (it.hasNext()) {
			final T t = it.next();
			if (predicate.test(t)) {
				it.remove();
			}
		}

	}

	private static Predicate<Transaction> isOld() {
		long timeInMillis = LocalDateTime.now(Clock.systemUTC()).toEpochSecond(ZoneOffset.UTC) * 1000;

		return t -> t.getTimestamp() < timeInMillis - ONE_MINUTE_MILLIS;
	}

	public Map<String, Number> getStatistics() {
		Map<String, Number> statisticsData = new LinkedHashMap<String, Number>();
		
		Double sum = transcationList.stream().mapToDouble(t -> t.getAmount()).sum();
		OptionalDouble average = transcationList.stream().mapToDouble(t -> t.getAmount()).average();
		OptionalDouble max = transcationList.stream().mapToDouble(t -> t.getAmount()).max();
		OptionalDouble min = transcationList.stream().mapToDouble(t -> t.getAmount()).min();
		Integer count = transcationList.size();
		
		statisticsData.put("sum", sum);
		statisticsData.put("avg", average.isPresent() ? average.getAsDouble() : 0.0);
		statisticsData.put("max", max.isPresent() ? max.getAsDouble() : 0.0);
		statisticsData.put("min", min.isPresent() ? min.getAsDouble() : 0.0);
		statisticsData.put("count", count);
		return statisticsData;
	}
}
