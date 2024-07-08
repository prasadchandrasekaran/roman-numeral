package org.adobe.interview.romannumeral.enumerations;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

/**
 * Enum representing various error types related to Roman numeral conversion.
 */
@Getter
@AllArgsConstructor
public enum RomanNumeralError {

    /**
     * Error when the input is not a valid integer within the range [1 - 3999].
     */
    INVALID_INPUT("INVALID_INPUT", "Input is invalid. Please provide a valid integer within the range [1 - 3999999999]"),

    /**
     * Error when the input is outside the supported range [1 - 3999].
     */
    INVALID_INPUT_RANGE("INVALID_INPUT_RANGE", "Input is out of supported range. Please provide a number within the range [1 - 3999999999]"),

    /**
     * Generic internal server error.
     */
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "Something went wrong. Please try again later");

    private String errorCode;
    private String errorMessage;
}
