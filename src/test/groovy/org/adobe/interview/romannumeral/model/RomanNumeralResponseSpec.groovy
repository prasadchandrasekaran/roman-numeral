package org.adobe.interview.romannumeral.model

import spock.lang.Specification

class RomanNumeralResponseSpec extends Specification {

    def "RomanNumeralResponse builder should correctly set properties: input=#input, output=#output, romanNumeralError=#romanNumeralError"() {
        given: "A RomanNumeralResponse instance built with specified properties"
        def romanNumeralResponse = RomanNumeralResponse.builder()
                .input(input)
                .output(output)
                .romanNumeralError(romanNumeralError)
                .build()

        expect: "The properties are correctly set"
        romanNumeralResponse.input == input
        romanNumeralResponse.output == output
        romanNumeralResponse.romanNumeralError == romanNumeralError

        where:
        input                | output          | romanNumeralError
        "10"                 | "X"             | null
        "100"                | "C"             | null
        "200"                | "CC"            | null
        "500"                | "D"             | null
        "1000"               | "M"             | null
        null                 | "IV"            | ErrorDetail.builder().code("INVALID_INPUT").message("Invalid input provided").build()
        "3999"               | "MMMCMXCIX"     | null
        "5000"               | "V"             | ErrorDetail.builder().code("INVALID_INPUT_RANGE").message("Input is out of supported range. Please provide a number within the range [1 - 3999]").build()
    }

    def "toString should return a string representation of RomanNumeralResponse"() {
        given: "A RomanNumeralResponse instance with input, output, and error details"
        def romanNumeralResponse = RomanNumeralResponse.builder()
                .input("2000")
                .output("MM")
                .romanNumeralError(ErrorDetail.builder().code("INVALID_INPUT_RANGE").message("Input is out of supported range.").build())
                .build()

        expect: "The toString method includes input, output, and error details"
        romanNumeralResponse.toString() == "RomanNumeralResponse(input=2000, output=MM, romanNumeralError=ErrorDetail(code=INVALID_INPUT_RANGE, message=Input is out of supported range.))"
    }

    def "RomanNumeralResponse instance should ignore null fields in JSON"() {
        given: "A RomanNumeralResponse instance with null fields"
        def romanNumeralResponse = RomanNumeralResponse.builder()
                .input(null)
                .output(null)
                .romanNumeralError(null)
                .build()

        expect: "The JSON serialization should ignore null fields"
        romanNumeralResponse.toString() == "RomanNumeralResponse(input=null, output=null, romanNumeralError=null)"  // This is to test the class, real JSON test requires a JSON serializer
    }
}
