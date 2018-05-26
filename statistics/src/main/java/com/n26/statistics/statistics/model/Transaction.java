package com.n26.statistics.statistics.model;

import java.io.Serializable;

public class Transaction implements Serializable{

	private static final long serialVersionUID = 1L;

	private Double amount;
	private Long timestamp;
	
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	
		
}
