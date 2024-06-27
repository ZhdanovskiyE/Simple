package com.example.Final_Task.domain.business;

import com.example.Final_Task.domain.model.GCDCalculator;
import org.springframework.stereotype.Service;

// Реализация класса, использующего метод разложения на простые множители
@Service
public class PrimeFactorizationGCDCalculator implements GCDCalculator {
    @Override
    public int calculateGCD(int a, int b) {
        if (a == 0) {
            return Math.abs(b);
        }
        if (b == 0) {
            return Math.abs(a);
        }
        int gcd = 1;
        for (int i = 2; i <= Math.min(Math.abs(a), Math.abs(b)); i++) {
            while (a % i == 0 && b % i == 0) {
                gcd *= i;
                a /= i;
                b /= i;
            }
        }
        // Сохранение знака НОД
        if (gcd < 0) {
            gcd *= -1;
        }
        return gcd;
    }
}