//package org.adobe.interview.romannumeral.service
//
//import spock.lang.Specification
//import spock.lang.Unroll
//
///**
// * Unit tests for RomanNumeralServiceImpl using Spock framework.
// */
//class RomanNumeralServiceImplSpec extends Specification {
//
//    // Instantiate the service to be tested
//    def romanNumeralService = new RomanNumeralServiceImpl()
//
//    /**
//     * Tests the conversion of integers to Roman numerals.
//     * Uses the `@Unroll` annotation to run the test for each set of input and expected output.
//     *
//     * The `where` block provides various test cases:
//     * - Converts integers to their Roman numeral equivalents.
//     */
//    @Unroll
//    def "convertIntegerToRomanNumeral should convert #number to Roman numeral #expected"() {
//        expect: // Verifies that the result of the conversion matches the expected value
//        romanNumeralService.convertNumberToRomanNumeral(number) == expected
//
//        where: // Provides test data for the conversion
//        number | expected
//        1      | "I"             // Tests the conversion of 1 to "I"
//        4      | "IV"            // Tests the conversion of 4 to "IV"
//        9      | "IX"            // Tests the conversion of 9 to "IX"
//        40     | "XL"            // Tests the conversion of 40 to "XL"
//        90     | "XC"            // Tests the conversion of 90 to "XC"
//        400    | "CD"            // Tests the conversion of 400 to "CD"
//        900    | "CM"            // Tests the conversion of 900 to "CM"
//        1000   | "M"             // Tests the conversion of 1000 to "M"
//        1987   | "MCMLXXXVII"    // Tests the conversion of 1987 to "MCMLXXXVII"
//        3999   | "MMMCMXCIX"     // Tests the conversion of 3999 to "MMMCMXCIX"
//    }
//
//    /**
//     * Tests that an IllegalArgumentException is thrown for numbers outside the valid range (1-3999).
//     * Uses the `@Unroll` annotation to run the test for each invalid input.
//     *
//     * The `where` block provides various out-of-range numbers:
//     * - Tests invalid numbers to ensure proper exception handling.
//     */
//    @Unroll
//    def "convertIntegerToRomanNumeral should throw IllegalArgumentException for out of range number #number"() {
//        when: // Executes the conversion method with an invalid input
//        romanNumeralService.convertNumberToRomanNumeral(number)
//
//        then: // Verifies that an IllegalArgumentException is thrown and checks the exception message
//        def e = thrown(IllegalArgumentException)
//        e.message == "number $number out of range (1-3999)"
//
//        where: // Provides invalid test data
//        number | _
//        0      | _ // Tests the conversion of 0, which is out of range
//        4000   | _ // Tests the conversion of 4000, which is out of range
//        -1     | _ // Tests the conversion of -1, which is out of range
//    }
//}
