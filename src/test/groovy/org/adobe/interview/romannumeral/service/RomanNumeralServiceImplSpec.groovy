package org.adobe.interview.romannumeral.service

import io.micrometer.tracing.Span
import io.micrometer.tracing.Tracer
import spock.lang.Specification
import spock.lang.Unroll

/**
 * Unit tests for RomanNumeralServiceImpl using Spock framework.
 */
class RomanNumeralServiceImplSpec extends Specification {

    def tracer = Mock(Tracer)
    def span = Mock(Span)
    def romanNumeralService = new RomanNumeralServiceImpl(tracer)

    def setup() {
        // Setup mock behavior
        tracer.nextSpan() >> span
        span.name(_) >> span
        span.start() >> span
        span.end() >> null
    }

    @Unroll
    def "convertNumberToRomanNumeral should convert #number to Roman numeral #expected"() {
        expect:
        romanNumeralService.convertNumberToRomanNumeral(number) == expected

        where:
        number       | expected
        1            | "I"
        4            | "IV"
        9            | "IX"
        40           | "XL"
        90           | "XC"
        400          | "CD"
        900          | "CM"
        1000         | "M"
        1987         | "MCMLXXXVII"
        3999         | "MMMCMXCIX"
        4000         | "I̅V̅"
        5000         | "V̅"
        10000        | "X̅"
        1000000      | "M̅̅"
    }

}
