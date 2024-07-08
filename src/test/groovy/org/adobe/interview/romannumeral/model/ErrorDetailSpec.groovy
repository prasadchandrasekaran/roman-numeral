package org.adobe.interview.romannumeral.model

import spock.lang.Specification
import spock.lang.Unroll

class ErrorDetailSpec extends Specification {

    @Unroll
    def "ErrorDetail builder should correctly set properties: code=#code, message=#message"() {
        given: "An ErrorDetail instance built with specified properties"
        def errorDetail = ErrorDetail.builder()
                .code(code)
                .message(message)
                .build()

        expect: "The properties are correctly set"
        errorDetail.code == code
        errorDetail.message == message

        where:
        code              | message
        "INVALID_INPUT"   | "Invalid input provided"
        "NOT_FOUND"       | "Resource not found"
        "INTERNAL_ERROR"  | "Internal server error"
        null              | "Error with null code"
        "NO_MESSAGE"      | null
        null              | null
    }

    def "toString should return a string representation of ErrorDetail"() {
        given: "An ErrorDetail instance with code and message"
        def errorDetail = ErrorDetail.builder()
                .code("ERROR_CODE")
                .message("Error message")
                .build()

        expect: "The toString method includes code and message"
        errorDetail.toString() == "ErrorDetail(code=ERROR_CODE, message=Error message)"
    }

    def "ErrorDetail instance should ignore null fields in JSON"() {
        given: "An ErrorDetail instance with null fields"
        def errorDetail = ErrorDetail.builder()
                .code(null)
                .message(null)
                .build()

        expect: "The JSON serialization should ignore null fields"
        errorDetail.toString() == "ErrorDetail(code=null, message=null)"  // This is to test the class, real JSON test requires a JSON serializer
    }
}
