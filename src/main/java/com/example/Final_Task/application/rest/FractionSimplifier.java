package com.example.Final_Task.application.rest;

import com.example.Final_Task.domain.model.Fraction;
import com.example.Final_Task.domain.model.GCDCalculator;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Value
@RestController
public class FractionSimplifier {
    GCDCalculator primeFactorizationGCDCalculator;
    GCDCalculator euclideanGCDCalculator;

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
            return Fraction.of(numerator, denominator);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Неверный формат числителя или знаменателя");
        }
    }
    // Метод для обработки запроса GET
    @GetMapping("/simplify")
    public ResponseEntity<String> simplifyFractionGet(@RequestParam String fraction) {
        try {
            Fraction fractionObj = parseFraction(fraction);
            fractionObj.simplify(primeFactorizationGCDCalculator);
            return ResponseEntity.ok("Сокращенная дробь: " + fractionObj);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Метод для обработки запроса POST
    @PostMapping("/simplify")
    public ResponseEntity<List<String>> simplifyFractionPost(@RequestBody List<String> fractions) {
        var result = new ArrayList<String>();
        try {
            for (String fraction : fractions) {
                Fraction fractionObj = parseFraction(fraction);
                fractionObj.simplify(primeFactorizationGCDCalculator);
                result.add(fractionObj.toString());
            }
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
