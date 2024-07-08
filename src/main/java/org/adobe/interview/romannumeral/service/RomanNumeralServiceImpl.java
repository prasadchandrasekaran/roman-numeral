package org.adobe.interview.romannumeral.service;

import lombok.extern.slf4j.Slf4j;
import org.adobe.interview.romannumeral.mapping.RomanNumeralMappings;
import org.springframework.stereotype.Service;
import io.micrometer.core.annotation.Timed;
import io.micrometer.tracing.annotation.NewSpan;
import io.micrometer.tracing.Tracer;
import io.micrometer.tracing.Span;
import org.springframework.beans.factory.annotation.Autowired;

@Service
@Slf4j
public class RomanNumeralServiceImpl implements RomanNumeralService {

    private final Tracer tracer;

    @Autowired
    public RomanNumeralServiceImpl(Tracer tracer) {
        this.tracer = tracer;
    }

    @Override
    @Timed(value = "convert.integer.to.roman", description = "Time taken to convert integer to Roman numeral")
    @NewSpan(name = "convertIntegerToRomanNumeral")
    public String convertNumberToRomanNumeral(long number) {
        log.info("Converting number to Roman numeral: {}", number);
        Span newSpan = tracer.nextSpan().name("convertIntegerToRomanNumeral").start();
        try (Tracer.SpanInScope ws = tracer.withSpan(newSpan.start())) {
            String result = toRoman(number);
            log.info("Converted number {} to Roman numeral {}", number, result);
            return result;
        } catch (Exception e) {
            log.error("Error converting number to Roman numeral: {}", e.getMessage());
            throw e;
        } finally {
            newSpan.end();
        }
    }

    private String toRoman(long number) {
        Span span = tracer.nextSpan().name("toRoman").start();
        try (Tracer.SpanInScope ws = tracer.withSpan(span.start())) {
            if (number < 1 || number > 39999999) {
                log.error("Invalid number: {}. Number out of range (1-39999999)", number);
                throw new IllegalArgumentException(String.format("number %d out of range (1-39999999)", number));
            }
            return convert((int) number);
        } finally {
            span.end();
        }
    }

    private String convert(int number) {
        Span span = tracer.nextSpan().name("convert").start();
        try (Tracer.SpanInScope ws = tracer.withSpan(span.start())) {
            StringBuilder sb = new StringBuilder();
            while (number > 0) {
                int floored = RomanNumeralMappings.ROMAN_NUMERALS.floorKey(number);
                String numeral = RomanNumeralMappings.ROMAN_NUMERALS.get(floored);
                sb.append(numeral);
                number -= floored;
            }
            return sb.toString();
        } finally {
            span.end();
        }
    }
}
