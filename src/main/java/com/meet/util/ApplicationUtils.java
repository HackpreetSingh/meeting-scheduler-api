package com.meet.util;

import java.time.LocalDateTime;
import java.util.List;

import com.meet.entity.Meeting;
import com.meet.entity.Person;
import com.meet.entity.Response;

public final class ApplicationUtils {

    /**
     * Checks if the current meeting collides with any of the given meetings.
     * @param meetings List of existing meetings to check against.
     * @param currentMeeting The meeting to check for collisions.
     * @return A message indicating the collision if any, otherwise an empty string.
     */
    public static String checkMeetingColliding(List<Meeting> meetings, Meeting currentMeeting) {	    
        System.out.println("Meetings fetched: "+meetings);
        
        return meetings.stream()
            .filter(meeting -> isOverlapping(currentMeeting.getFromTime(), currentMeeting.getToTime(), meeting.getFromTime(), meeting.getToTime()))
            .findFirst()
            .map(meeting -> String.format("Meeting Colliding between time: %s and %s",
                                          meeting.getFromTime(), meeting.getToTime()))
            .orElse("");
    }

    /**
     * Determines if two time periods overlap.
     * @param currentFromTime Start time of the current meeting.
     * @param currentToTime End time of the current meeting.
     * @param referenceFromTime Start time of the reference meeting.
     * @param referenceToTime End time of the reference meeting.
     * @return True if the time periods overlap, false otherwise.
     */
    public static boolean isOverlapping(LocalDateTime currentFromTime, LocalDateTime currentToTime, LocalDateTime referenceFromTime, LocalDateTime referenceToTime) {
        return currentFromTime.isBefore(referenceToTime) &&
                currentToTime.isAfter(referenceFromTime);
    }
    
    /**
     * Creates a failure response.
     * @param meeting The meeting associated with the response.
     * @param person List of people associated with the response.
     * @param msg Message describing the failure.
     * @return A Response object with status 400 (Bad Request).
     */
    public static Response returnFailureResponse(Meeting meeting, List<Person> person, String msg) { 
        return new Response(meeting, person, 400, msg);
    }
    
    /**
     * Creates a success response.
     * @param meeting The meeting associated with the response.
     * @param person List of people associated with the response.
     * @param msg Message describing the success.
     * @return A Response object with status 201 (Created).
     */
    public static Response returnSuccessResponse(Meeting meeting, List<Person> person, String msg) { 
        return new Response(meeting, person, 201, msg);
    }
}
