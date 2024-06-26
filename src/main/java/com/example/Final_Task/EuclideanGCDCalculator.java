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
        return a;
    }
}
