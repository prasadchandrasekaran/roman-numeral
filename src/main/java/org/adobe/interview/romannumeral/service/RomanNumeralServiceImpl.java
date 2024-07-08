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

    private static final long MAX_SMALL_NUMBER = 3999;
    private static final long MAX_NUMBER = 3999999999L;
    private static final String TOO_BIG_MESSAGE = "Too big (over 3,999,999,999)";

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
            if (number > MAX_NUMBER) {
                return TOO_BIG_MESSAGE;
            }
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
            if (number < 1 || number > MAX_NUMBER) {
                log.error("Invalid number: {}. Number out of range (1-3999999999)", number);
                throw new IllegalArgumentException(String.format("number %d out of range (1-3999999999)", number));
            }

            StringBuilder resultString = new StringBuilder();
            String numberStr = Long.toString(number);
            int length = numberStr.length();

            for (int i = 0; i < length; i++) {
                int currNumber = Character.getNumericValue(numberStr.charAt(length - 1 - i));
                if (currNumber == 0) continue;

                if (i <= 2) {
                    resultString.insert(0, processSmallNumber(currNumber, i));
                } else if (number > MAX_SMALL_NUMBER) {
                    resultString.insert(0, processLargeNumberVinculum(currNumber, i));
                } else {
                    if (i == 3) resultString.insert(0, "M".repeat(currNumber));
                }
            }

            return resultString.toString();
        } finally {
            span.end();
        }
    }

    private String processSmallNumber(int currNumber, int position) {
        String[] romanChars = getRomanCharacters(position);
        return individualRomanCharConverter(currNumber, romanChars[0], romanChars[1], romanChars[2]);
    }

    private String processLargeNumberVinculum(int currNumber, int position) {
        String[] romanChars = getRomanCharacters(position % 3);
        if ((position == 6 || position == 9) && currNumber < 4) {
            romanChars[0] = "M";
        }
        String currRomanChars = individualRomanCharConverter(currNumber, romanChars[0], romanChars[1], romanChars[2]);
        String line = position >= 6 ? "\u0305\u0305" : "\u0305";
        StringBuilder result = new StringBuilder();
        for (char c : currRomanChars.toCharArray()) {
            result.append(c).append(line);
        }
        return result.toString();
    }

    private String[] getRomanCharacters(int position) {
        switch (position) {
            case 0: return new String[]{"I", "V", "X"};
            case 1: return new String[]{"X", "L", "C"};
            case 2: return new String[]{"C", "D", "M"};
            default: return new String[]{"", "", ""};
        }
    }

    private String individualRomanCharConverter(int currNumber, String currSingularChar, String currHalfChar, String currTenChar) {
        switch (currNumber) {
            case 0: return "";
            case 1: return currSingularChar;
            case 2: return currSingularChar.repeat(2);
            case 3: return currSingularChar.repeat(3);
            case 4: return currSingularChar + currHalfChar;
            case 5: return currHalfChar;
            case 6: return currHalfChar + currSingularChar;
            case 7: return currHalfChar + currSingularChar.repeat(2);
            case 8: return currHalfChar + currSingularChar.repeat(3);
            case 9: return currSingularChar + currTenChar;
            default: return "";
        }
    }
}
