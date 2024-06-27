package com.example.Final_Task;


import com.example.Final_Task.application.rest.FractionSimplifier;
import com.example.Final_Task.domain.business.PrimeFactorizationGCDCalculator;
import com.example.Final_Task.domain.business.EuclideanGCDCalculator;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@FieldDefaults(level = AccessLevel.PRIVATE)
class FinalTaskApplicationTests {

	@Autowired
	FractionSimplifier fractionSimplifier;

	@Test
	void testSimplifyFractionGetValidInput() {
		ResponseEntity<String> response = fractionSimplifier.simplifyFractionGet("4/8");
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Сокращенная дробь: 1/2", response.getBody());
	}

	@Test
	void testSimplifyFractionGetInvalidInput() {
		ResponseEntity<String> response = fractionSimplifier.simplifyFractionGet("4/0");
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertTrue(Objects.requireNonNull(response.getBody()).contains("Знаменатель не может быть равен 0"));
	}

	@Test
	void testSimplifyFractionPostValidInput() {
		ResponseEntity<List<String>> response = fractionSimplifier.simplifyFractionPost(Collections.singletonList("4/8"));
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(Collections.singletonList("1/2"), response.getBody());
	}

	@Test
	void testSimplifyFractionPostInvalidInput() {
		ResponseEntity<List<String>> response = fractionSimplifier.simplifyFractionPost(Collections.singletonList("4/0"));
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertTrue(Objects.requireNonNull(response.getBody()).contains("Знаменатель не может быть равен 0"));
	}

	@Test
	void testCalculateGCD_PositiveNumbers() {
		PrimeFactorizationGCDCalculator calculator = new PrimeFactorizationGCDCalculator();
		assertEquals(12, calculator.calculateGCD(24, 36));
		assertEquals(1, calculator.calculateGCD(17, 23));
		assertEquals(5, calculator.calculateGCD(15, 25));
		EuclideanGCDCalculator calculator2 = new EuclideanGCDCalculator();
		assertEquals(12, calculator2.calculateGCD(24, 36));
		assertEquals(1, calculator2.calculateGCD(17, 23));
		assertEquals(5, calculator2.calculateGCD(15, 25));
	}

	@Test
	void testCalculateGCD_NegativeNumbers() {
		PrimeFactorizationGCDCalculator calculator = new PrimeFactorizationGCDCalculator();
		assertEquals(12, calculator.calculateGCD(-24, 36));
		assertEquals(1, calculator.calculateGCD(17, -23));
		assertEquals(5, calculator.calculateGCD(-15, -25));
		EuclideanGCDCalculator calculator2 = new EuclideanGCDCalculator();
		assertEquals(12, calculator2.calculateGCD(-24, 36));
		assertEquals(1, calculator2.calculateGCD(17, -23));
		assertEquals(5, calculator2.calculateGCD(-15, -25));
	}

	@Test
	void testCalculateGCD_Zero() {
		PrimeFactorizationGCDCalculator calculator = new PrimeFactorizationGCDCalculator();
		assertEquals(16, calculator.calculateGCD(0, 16));
		assertEquals(24, calculator.calculateGCD(24, 0));
		assertEquals(0, calculator.calculateGCD(0, 0));
		EuclideanGCDCalculator calculator2 = new EuclideanGCDCalculator();
		assertEquals(16, calculator2.calculateGCD(0, 16));
		assertEquals(24, calculator2.calculateGCD(24, 0));
		assertEquals(0, calculator2.calculateGCD(0, 0));
	}

	@Test
	void testCalculateGCD_SpecialCases() {
		PrimeFactorizationGCDCalculator calculator = new PrimeFactorizationGCDCalculator();
		assertEquals(16, calculator.calculateGCD(-16, 16));
		assertEquals(50, calculator.calculateGCD(100, 150));
		assertEquals(10, calculator.calculateGCD(100, 10));
		EuclideanGCDCalculator calculator2 = new EuclideanGCDCalculator();
		assertEquals(16, calculator2.calculateGCD(-16, 16));
		assertEquals(50, calculator2.calculateGCD(100, 150));
		assertEquals(10, calculator2.calculateGCD(100, 10));
	}
}
