package com.example.Final_Task;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FinalTaskApplicationTests {

	@Test
	void testSimplifyFractionGetValidInput() {
		FractionSimplifier fractionSimplifier = new FractionSimplifier();
		ResponseEntity<String> response = fractionSimplifier.simplifyFractionGet("4/8");
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Сокращенная дробь: 1/2", response.getBody());
	}

	@Test
	void testSimplifyFractionGetInvalidInput() {
		FractionSimplifier fractionSimplifier = new FractionSimplifier();
		ResponseEntity<String> response = fractionSimplifier.simplifyFractionGet("4/0");
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertTrue(response.getBody().contains("Знаменатель не может быть равен 0"));
	}

	@Test
	void testSimplifyFractionPostValidInput() {
		FractionSimplifier fractionSimplifier = new FractionSimplifier();
		ResponseEntity<String> response = fractionSimplifier.simplifyFractionPost("4/8");
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Сокращенная дробь: 1/2", response.getBody());
	}

	@Test
	void testSimplifyFractionPostInvalidInput() {
		FractionSimplifier fractionSimplifier = new FractionSimplifier();
		ResponseEntity<String> response = fractionSimplifier.simplifyFractionPost("4/0");
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertTrue(response.getBody().contains("Знаменатель не может быть равен 0"));
	}

}
