package org.adobe.interview.romannumeral.service;

/**
 * Interface for converting integers to Roman numerals.
 */
public interface RomanNumeralService {
    /**
     * Converts an integer to its Roman numeral representation.
     *
     * @param number The integer to convert.
     * @return The Roman numeral representation of the integer.
     */
    String convertNumberToRomanNumeral(long number);
}
