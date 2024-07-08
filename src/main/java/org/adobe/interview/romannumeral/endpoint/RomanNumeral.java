package org.adobe.interview.romannumeral.endpoint;


import lombok.extern.slf4j.Slf4j;
import org.adobe.interview.romannumeral.model.RomanNumeralResponse;
import org.adobe.interview.romannumeral.service.RomanNumeralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

/**
 * REST controller for converting integers to Roman numerals.
 */
@RestController
@RequestMapping(name = "roman-numeral", path = "/romannumeral")
@Slf4j
public class RomanNumeral {


    @Autowired
    private RomanNumeralService romanNumeralService;

    /**
     * Endpoint to convert an integer to a Roman numeral.
     *
     * @param number integer input parameter from the request query.
     * @return a RomanNumeralResponse containing the converted Roman numeral and input number.
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public RomanNumeralResponse RomanNumeralConverter(@RequestParam("number") long number) {
        log.info("Received request to convert number: {}", number);
        try {
            // Call service to convert integer to Roman numeral
            String romanNumeral = romanNumeralService.convertNumberToRomanNumeral(number);
            log.debug("Converted number {} to Roman numeral {}", number, romanNumeral);

            // Build response object using Lombok builder
            RomanNumeralResponse response = RomanNumeralResponse.builder()
                    .output(romanNumeral) // Set the converted Roman numeral
                    .input(Long.toString(number)) // Set the original input number as string
                    .build();

            log.info("Returning response for number {}: {}", number, response);
            return response;
        } catch (IllegalArgumentException e) {
            log.error("Error converting number {}: {}", number, e.getMessage(), e);
            throw e;  // Re-throwing the exception will cause the global exception handler to handle it
        }
    }
}
