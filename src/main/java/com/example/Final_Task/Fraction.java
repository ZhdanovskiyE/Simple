package com.example.Final_Task;

// Класс, представляющий дробь
class Fraction {
    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    // Метод для сокращения дроби
    public void simplify(GCDCalculator gcdCalculator) {
        int gcd = gcdCalculator.calculateGCD(numerator, denominator);
        numerator /= gcd;
        denominator /= gcd;
    }

    // Метод для получения сокращенной дроби в виде строки
    public String toString() {
        if (denominator == 1) { // Проверка, является ли знаменатель равным 1
            return String.valueOf(numerator); // Вывод только числителя
        } else {
            return numerator + "/" + denominator;
        }
    }
}