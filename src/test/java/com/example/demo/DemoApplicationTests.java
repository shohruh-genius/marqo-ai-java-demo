package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.marqo.Document;
import com.example.demo.marqo.MarqoService;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private MarqoService marqoService;

	@Test
	void createIndex() {
		Object response = marqoService.createIndex("my-second-index");
		assertNotNull(response);
		assertTrue((Boolean) ((HashMap) response).get("acknowledged"));
	}

	@Test
	void addSomeDocuments() {
		List<Document> documents = new ArrayList<>();
		documents.add(new Document("The Travels of Marco Polo",
				"A 13th-century travelogue describing the travels of Marco Polo", null));
		documents.add(new Document("Extravehicular Mobility Unit (EMU)",
				"The EMU is a spacesuit that provides environmental protection, mobility, life support, and communications for astronauts",
				"article_591"));

		Object response = marqoService.addDocuments("my-first-index", documents);
		assertNotNull(response);
		assertTrue((Boolean) ((HashMap) response).get("errors"));
	}

	@Test
	void searchQuery() {
		String query = "What is the best outfit to wear on the moon?";

		Object result = marqoService.search("my-first-index", query);

		assertNotNull(result);
		assertEquals(((HashMap) result).get("query"), query);
	}

}
