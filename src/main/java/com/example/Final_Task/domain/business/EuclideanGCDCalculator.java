package com.example.Final_Task.domain.business;

import com.example.Final_Task.domain.model.GCDCalculator;
import org.springframework.stereotype.Service;

// Реализация класса, использующего метод Евклида
@Service
class EuclideanGCDCalculator implements GCDCalculator {
    @Override
    public int calculateGCD(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
