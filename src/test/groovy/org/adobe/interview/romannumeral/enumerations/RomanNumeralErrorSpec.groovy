package org.adobe.interview.romannumeral.enumerations

import spock.lang.Specification
import spock.lang.Unroll

/**
 * Unit tests for RomanNumeralError enum using Spock framework.
 */
class RomanNumeralErrorSpec extends Specification {

    /**
     * Tests the error codes and messages for each enum constant.
     * Uses the `@Unroll` annotation to run the test for each enum constant and its expected values.
     *
     * The `where` block provides various test cases:
     * - Validates the error code and message for each enum constant.
     */
    @Unroll
    def 'RomanNumeralError #error should have code "#expectedCode" and message "#expectedMessage"'() {
        expect:
        // Validate that the enum constant has the correct code and message
        error.errorCode == expectedCode
        error.errorMessage == expectedMessage

        where:
        // Provide test data for each enum constant with its expected code and message
        error                     | expectedCode                | expectedMessage
        RomanNumeralError.INVALID_INPUT       | "INVALID_INPUT"       | "Input is invalid. Please provide a valid integer within the range [1 - 3999999999]"
        RomanNumeralError.INVALID_INPUT_RANGE | "INVALID_INPUT_RANGE" | "Input is out of supported range. Please provide a number within the range [1 - 3999999999]"
        RomanNumeralError.INTERNAL_SERVER_ERROR | "INTERNAL_SERVER_ERROR" | "Something went wrong. Please try again later"
    }
}
