package org.adobe.interview.romannumeral

import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import spock.lang.Shared
import spock.lang.Specification
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import spock.lang.Unroll

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RomanNumeralApplicationIntegrationSpec extends Specification {

    @LocalServerPort
    int port

    @Shared
    TestRestTemplate restTemplate = new TestRestTemplate()

    def HttpHeaders createHttpHeaders(String key, String value) {
        HttpHeaders headers = new HttpHeaders()
        headers.set(key, value)
        return headers
    }

    def HttpHeaders headers = createHttpHeaders("x-api-key", "secret-api-key")
    def HttpEntity<String> entity = new HttpEntity<>("parameters", headers)

    @Unroll
    def "test Roman numeral conversion endpoint with input #number"() {
        when:
        ResponseEntity<Map> response = restTemplate.exchange(
                "http://localhost:$port/romannumeral?number=$number", HttpMethod.GET, null, Map)

        then:
        response.statusCode.value() == expectedStatus

        if (expectedOutput != null) {
            assert response.body['output'] == expectedOutput
            assert response.body['input'] == number.toString()
        } else {
            assert response.body['error'] != null
        }

        where:
        number         | expectedOutput | expectedStatus
        1              | "I"            | 200
        4              | "IV"           | 200
        9              | "IX"           | 200
        40             | "XL"           | 200
        90             | "XC"           | 200
        400            | "CD"           | 200
        900            | "CM"           | 200
        1000           | "M"            | 200
        1987           | "MCMLXXXVII"   | 200
        3999           | "MMMCMXCIX"    | 200
        0              | null           | 400  // Invalid input
        -10            | null           | 400  // Invalid input
        2200000001     | null           | 400  // Out of range
        "invalid"      | null           | 400  // Invalid type
    }
}
