package org.adobe.interview.romannumeral.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * Response for Roman numeral conversion.
 */
@Getter
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RomanNumeralResponse {

    /**
     * Original input value.
     */
    @JsonProperty("input")
    private final String input;

    /**
     * Converted Roman numeral output.
     */
    @JsonProperty("output")
    private final String output;

    /**
     * Error details, if any, during the conversion.
     */
    @JsonProperty("error")
    private final ErrorDetail romanNumeralError;
}
