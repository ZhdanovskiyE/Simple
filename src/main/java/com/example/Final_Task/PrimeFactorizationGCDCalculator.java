package com.example.Final_Task;

// Реализация класса, использующего метод разложения на простые множители
class PrimeFactorizationGCDCalculator implements GCDCalculator {
    @Override
    public int calculateGCD(int a, int b) {
        int gcd = 1;
        for (int i = 2; i <= Math.min(Math.abs(a), Math.abs(b)); i++) {
            while (a % i == 0 && b % i == 0) {
                gcd *= i;
                a /= i;
                b /= i;
            }
        }
        // Сохранение знака НОД
        if (a < 0 || b < 0) { // Изменение условия if
            gcd *= -1;
        }
        return gcd;
    }
}