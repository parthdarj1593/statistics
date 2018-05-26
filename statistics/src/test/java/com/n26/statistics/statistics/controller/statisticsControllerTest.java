package com.n26.statistics.statistics.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(statisticsController.class)
public class statisticsControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private statisticsController statisticsController;

	@Test
	public void statisticsNotDefaultValTest() throws Exception {
		Map<String, Number> map = new LinkedHashMap<>();
		map.put("sum", 0);
		map.put("avg", 1.0);
		map.put("max", 3.0);
		map.put("min", 3.0);
		map.put("count", 3);
		when(statisticsController.statistics()).thenReturn(map);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/statistics").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mvc.perform(requestBuilder).andReturn();
		verify(statisticsController, times(1)).statistics();
		JSONAssert.assertEquals(map.toString(), result.getResponse().getContentAsString(), false);

	}

	@Test
	public void statisticsDefaultValTest() throws Exception {

		Map<String, Number> map = new LinkedHashMap<>();
		map.put("sum", 0);
		map.put("avg", 0.0);
		map.put("max", 0.0);
		map.put("min", 0.0);
		map.put("count", 0);
		when(statisticsController.statistics()).thenReturn(map);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/statistics").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mvc.perform(requestBuilder).andReturn();
		verify(statisticsController, times(1)).statistics();
		JSONAssert.assertEquals(map.toString(), result.getResponse().getContentAsString(), false);

	}
}
