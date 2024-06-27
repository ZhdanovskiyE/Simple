package com.example.Final_Task.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

public interface Fraction {
    static Fraction of(int numerator, int denominator) {
        return new FractionImpl(numerator, denominator);
    }

    // Метод для сокращения дроби
    void simplify(GCDCalculator gcdCalculator);
}
// Класс, представляющий дробь
@Data
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@FieldDefaults(level = AccessLevel.PRIVATE)
class FractionImpl implements Fraction {
    int numerator;
    int denominator;

    // Метод для сокращения дроби
    @Override
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