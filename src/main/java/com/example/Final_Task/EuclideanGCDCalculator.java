package com.example.Final_Task;

// Реализация класса, использующего метод Евклида
class EuclideanGCDCalculator implements GCDCalculator {
    @Override
    public int calculateGCD(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        // Сохранение знака НОД
        if (a < 0 || b < 0) { // Изменение условия if
            a *= -1;
        }
        return a;
    }
}
