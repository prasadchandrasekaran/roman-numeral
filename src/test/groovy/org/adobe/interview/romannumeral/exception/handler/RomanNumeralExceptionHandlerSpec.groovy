//package org.adobe.interview.romannumeral.exception.handler
//
//import org.adobe.interview.romannumeral.enumerations.RomanNumeralError
//import org.adobe.interview.romannumeral.model.ErrorDetail
//import org.adobe.interview.romannumeral.model.RomanNumeralResponse
//import org.springframework.beans.TypeMismatchException
//import org.springframework.http.HttpStatus
//import org.slf4j.Logger
//import org.springframework.mock.web.MockHttpServletRequest;
//import org.springframework.web.bind.MissingServletRequestParameterException
//import org.springframework.web.context.request.ServletWebRequest
//import org.springframework.web.context.request.WebRequest
//import spock.lang.Specification
//import  jakarta.servlet.http.HttpServletRequest
//import spock.lang.Unroll
//
//class RomanNumeralExceptionHandlerSpec extends Specification {
//
//    // Mock logger to avoid actual logging during tests
//    def logger = Mock(Logger.class)
//
//
//    // Instantiate the handler
//    def handler = new RomanNumeralExceptionHandler()
//
//    def setup() {
//        // Inject the mock logger into the handler
//    }
//
//    @Unroll
//    def "handleNumberConverterException should return error response for IllegalArgumentException with message: #message"() {
//        given: "A mocked web request"
//
//
//        and: "An IllegalArgumentException is thrown"
//        MockHttpServletRequest mockRequest = new MockHttpServletRequest()
//        mockRequest.requestURI = "/test-uri"
//
//        ServletWebRequest servletWebRequest = new ServletWebRequest(mockRequest)
//
//        WebRequest mockWebRequest = Mock(WebRequest)
//        mockWebRequest instanceof ServletWebRequest >> true
//        mockWebRequest.getRequest() >> servletWebRequest.getRequest()
//        def ex = new IllegalArgumentException();
//
//        when: "The exception handler is invoked"
//
//        def response = handler.handleNumberConverterException(ex, mockWebRequest)
//
//        then: "The response contains the expected error details"
//        response.statusCode == HttpStatus.BAD_REQUEST
//        response.body.romanNumeralError.code == RomanNumeralError.INVALID_INPUT_RANGE.errorCode
//        response.body.romanNumeralError.message == RomanNumeralError.INVALID_INPUT_RANGE.errorMessage
//
//        and: "The error is logged"
//        1 * logger.error(*_)
//
//        where:
//        message << ["Input is not within range", "Provided input is invalid"]
//    }
//
//    @Unroll
//    def "handleMissingServletRequestParameterException should return error response for missing query parameter"() {
//        given: "A mocked web request"
//        def webRequest = Mock(ServletWebRequest)
//
//        when: "The exception handler is invoked"
//        def response = handler.handleMissingServletRequestParameterException(webRequest)
//
//        then: "The response contains the expected error details"
//        response.statusCode == HttpStatus.BAD_REQUEST
//        response.body.romanNumeralError.code == RomanNumeralError.INVALID_INPUT.errorCode
//        response.body.romanNumeralError.message == RomanNumeralError.INVALID_INPUT.errorMessage
//
//        and: "The error is logged"
//        1 * logger.error(*_)
//    }
//
//    @Unroll
//    def "handleTypeMismatchException should return error response for TypeMismatchException with value: #value"() {
//        given: "A mocked web request"
//        def webRequest = Mock(ServletWebRequest)
//
//        and: "A TypeMismatchException is thrown"
//        def exception = new TypeMismatchException(value, String)
//
//        when: "The exception handler is invoked"
//        def response = handler.handleTypeMismatchException(exception, webRequest)
//
//        then: "The response contains the expected error details"
//        response.statusCode == HttpStatus.BAD_REQUEST
//        response.body.romanNumeralError.code == RomanNumeralError.INVALID_INPUT.errorCode
//        response.body.romanNumeralError.message == RomanNumeralError.INVALID_INPUT.errorMessage
//
//        and: "The error is logged"
//        1 * logger.error(*_)
//
//        where:
//        value << ["abc", 1234, 56.78]
//    }
//
//    @Unroll
//    def "handleGenericException should return error response for generic exception with message: #message"() {
//        given: "A mocked web request"
//        def webRequest = Mock(ServletWebRequest)
//
//        and: "A generic exception is thrown"
//        def exception = new Exception(message)
//
//        when: "The exception handler is invoked"
//        def response = handler.handleGenericException(exception, webRequest)
//
//        then: "The response contains the expected error details"
//        response.statusCode == HttpStatus.INTERNAL_SERVER_ERROR
//        response.body.romanNumeralError.code == RomanNumeralError.INTERNAL_SERVER_ERROR.errorCode
//        response.body.romanNumeralError.message == RomanNumeralError.INTERNAL_SERVER_ERROR.errorMessage
//
//        and: "The error is logged"
//        1 * logger.error(*_)
//
//        where:
//        message << ["An unknown error occurred", "Server encountered an unexpected error"]
//    }
//}
