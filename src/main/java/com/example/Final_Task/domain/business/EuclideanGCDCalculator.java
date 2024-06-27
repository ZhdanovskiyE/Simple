package com.example.Final_Task.domain.business;

import com.example.Final_Task.domain.model.GCDCalculator;
import org.springframework.stereotype.Service;

// Реализация класса, использующего метод Евклида
@Service
public class EuclideanGCDCalculator implements GCDCalculator {
    @Override
    public int calculateGCD(int a, int b) {
        if (a == 0) {
            return Math.abs(b);
        }
        if (b == 0) {
            return Math.abs(a);
        }
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        // Сохранение знака НОД
        if (a < 0) {
            a *= -1;
        }
        return a;
    }
}
