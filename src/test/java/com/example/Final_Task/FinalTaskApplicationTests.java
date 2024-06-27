package com.example.Final_Task;

import com.example.Final_Task.application.rest.FractionSimplifier;
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

}
