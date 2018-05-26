package com.n26.statistics.statistics.controller;

import static org.junit.Assert.*;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.n26.statistics.statistics.model.Transaction;

@RunWith(SpringRunner.class)
@WebMvcTest(TransactionsController.class)
public class TransactionsControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private TransactionsController transactionsController;

	@Test
	public void newTranscationWithNoContentTest() throws Exception {
		long timeInMillis = LocalDateTime.now(Clock.systemUTC()).minusMinutes(2).toEpochSecond(ZoneOffset.UTC) * 1000;

		String transcationEx = "{\"amount\":125.3,\"timestamp\":" + timeInMillis + "}";

		Mockito.when(transactionsController.newTransaction(Mockito.any(Transaction.class)))
				.thenReturn(ResponseEntity.status(HttpStatus.NO_CONTENT).build());

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/transactions").accept(MediaType.APPLICATION_JSON)
				.content(transcationEx).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());

	}

	@Test
	public void newTranscationWithCreatedTest() throws Exception {
		long timeInMillis = LocalDateTime.now(Clock.systemUTC()).plusSeconds(10).toEpochSecond(ZoneOffset.UTC) * 1000;

		String transcationEx = "{\"amount\":125.3,\"timestamp\":" + timeInMillis + "}";

		Mockito.when(transactionsController.newTransaction(Mockito.any(Transaction.class)))
				.thenReturn(ResponseEntity.status(HttpStatus.CREATED).build());

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/transactions").accept(MediaType.APPLICATION_JSON)
				.content(transcationEx).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

	}
}
