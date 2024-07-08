package org.adobe.interview.romannumeral.endpoint


import org.adobe.interview.romannumeral.service.RomanNumeralService
import spock.lang.Specification
import spock.lang.Unroll

/**
 * Unit tests for RomanNumeral controller using Spock framework.
 */
class RomanNumeralSpec extends Specification {

    // Create a mock of the RomanNumeralService to simulate service behavior
    def romanNumeralService = Mock(RomanNumeralService)

    // Instantiate the controller to be tested
    def romanNumeralController = new RomanNumeral()

    /**
     * Sets up the test environment before each test.
     * Injects the mock RomanNumeralService into the RomanNumeral controller.
     */
    def setup() {
        // Inject the mock service into the controller
        romanNumeralController.romanNumeralService = romanNumeralService
    }

    /**
     * Tests the RomanNumeralConverter endpoint with valid integer inputs.
     * Uses the `@Unroll` annotation to run the test for each set of input and expected output.
     *
     * The `where` block provides various test cases:
     * - Converts integers to their expected Roman numeral responses.
     */
    @Unroll()
    def 'RomanNumeralConverter should return Roman numeral #output for valid integer #input'() {
        given: "A valid integer input"
        // Mock the service to return the expected output for the given input
        and: "The service returns the expected Roman numeral"
        romanNumeralService.convertNumberToRomanNumeral(input) >> output

        when: "The controller is called with the integer"
        // Call the controller's method with the input
        def response = romanNumeralController.RomanNumeralConverter(input)

        then: "The response contains the correct Roman numeral"
        // Validate the response contains the expected output and input
        response != null
        response.output == output
        response.input == input.toString()

        where:
        // Provide test data for valid inputs and their expected outputs
        input | output
        10    | "X"  // Tests conversion of 10 to "X"
        100   | "C"  // Tests conversion of 100 to "C"
        200   | "CC" // Tests conversion of 200 to "CC"
    }
}
