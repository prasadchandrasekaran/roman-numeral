package org.adobe.interview.romannumeral.mapping;

import java.util.NavigableMap;
import java.util.TreeMap;

public class RomanNumeralMappings {

    public static final NavigableMap<Integer, String> ROMAN_NUMERALS = new TreeMap<>();

    static {
        ROMAN_NUMERALS.put(1, "I");
        ROMAN_NUMERALS.put(4, "IV");
        ROMAN_NUMERALS.put(5, "V");
        ROMAN_NUMERALS.put(9, "IX");
        ROMAN_NUMERALS.put(10, "X");
        ROMAN_NUMERALS.put(40, "XL");
        ROMAN_NUMERALS.put(50, "L");
        ROMAN_NUMERALS.put(90, "XC");
        ROMAN_NUMERALS.put(100, "C");
        ROMAN_NUMERALS.put(400, "CD");
        ROMAN_NUMERALS.put(500, "D");
        ROMAN_NUMERALS.put(900, "CM");
        ROMAN_NUMERALS.put(1000, "M");
        ROMAN_NUMERALS.put(4000, "I\u0305V\u0305");
        ROMAN_NUMERALS.put(5000, "V\u0305");
        ROMAN_NUMERALS.put(9000, "I\u0305X\u0305");
        ROMAN_NUMERALS.put(10000, "X\u0305");
        ROMAN_NUMERALS.put(40000, "X\u0305L\u0305");
        ROMAN_NUMERALS.put(50000, "L\u0305");
        ROMAN_NUMERALS.put(90000, "X\u0305C\u0305");
        ROMAN_NUMERALS.put(100000, "C\u0305");
        ROMAN_NUMERALS.put(400000, "C\u0305D\u0305");
        ROMAN_NUMERALS.put(500000, "D\u0305");
        ROMAN_NUMERALS.put(900000, "C\u0305M\u0305");
        ROMAN_NUMERALS.put(1000000, "M\u0305");
        ROMAN_NUMERALS.put(4000000, "I\u0305\u0305V\u0305\u0305");
        ROMAN_NUMERALS.put(5000000, "V\u0305\u0305");
        ROMAN_NUMERALS.put(9000000, "I\u0305\u0305X\u0305\u0305");
        ROMAN_NUMERALS.put(10000000, "X\u0305\u0305");
    }
}
