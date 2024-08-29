package com.meet.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a standardized response object used for API responses.
 * Includes optional information about a meeting, a list of people, HTTP status, and a message.
 */
@Data
@JsonPropertyOrder({"msg", "httpStatus", "meeting", "person"})
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    /**
     * Details of the meeting associated with the response.
     * Can be null if not applicable.
     */
    private Meeting meeting;
    
    /**
     * List of people related to the response.
     * Can be null if not applicable.
     */
    private List<Person> person;
    
    /**
     * HTTP status code representing the outcome of the request.
     */
    private Integer httpStatus;
    
    /**
     * Message providing additional information about the response.
     * Can be null if not applicable.
     */
    private String msg;
}
