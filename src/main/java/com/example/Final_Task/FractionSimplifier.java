package com.example.Final_Task;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@SpringBootApplication
@RestController
public class FractionSimplifier {

    // Метод для преобразования строки дроби в объект Fraction
    private static Fraction parseFraction(String fractionString) {
        String[] parts = fractionString.split("/");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Неправильный формат дроби");
        }
        try {
            int numerator = Integer.parseInt(parts[0]);
            int denominator = Integer.parseInt(parts[1]);
            if (denominator == 0) {
                throw new IllegalArgumentException("Знаменатель не может быть равен 0");
            }
            return new Fraction(numerator, denominator);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Неверный формат числителя или знаменателя");
        }
    }
    // Метод для обработки запроса GET
    @GetMapping("/simplify")
    public ResponseEntity<String> simplifyFractionGet(@RequestParam String fraction) {
        try {
            Fraction fractionObj = parseFraction(fraction);
            GCDCalculator gcdCalculator = new PrimeFactorizationGCDCalculator();
            fractionObj.simplify(gcdCalculator);
            return ResponseEntity.ok("Сокращенная дробь: " + fractionObj);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Метод для обработки запроса POST
    @PostMapping("/simplify")
    public ResponseEntity<String> simplifyFractionPost(@RequestBody String fraction) {
        try {
            Fraction fractionObj = parseFraction(fraction);
            GCDCalculator gcdCalculator = new PrimeFactorizationGCDCalculator();
            fractionObj.simplify(gcdCalculator);
            return ResponseEntity.ok("Сокращенная дробь: " + fractionObj);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
