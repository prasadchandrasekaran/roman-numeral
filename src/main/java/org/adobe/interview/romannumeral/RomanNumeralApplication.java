package org.adobe.interview.romannumeral;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot application class for Roman Numeral Conversion.
 */
@SpringBootApplication
@Slf4j
public class RomanNumeralApplication {
    /**
     * Main method to start the Spring Boot application.
     *
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        log.info("Starting Roman Numeral Conversion Application...");
        SpringApplication.run(RomanNumeralApplication.class, args);
        log.info("Roman Numeral Conversion Application started successfully.");
    }
}
