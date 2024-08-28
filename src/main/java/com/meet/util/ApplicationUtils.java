package com.meet.util;

import java.time.LocalDateTime;
import java.util.List;

import com.meet.entity.Meeting;
import com.meet.entity.Person;
import com.meet.entity.Response;

public final class ApplicationUtils {

	public static String checkMeetingColliding(List<Meeting> meetings, Meeting currentMeeting) {	    
	    System.out.println("Meetings fetched: "+meetings);
	    
	    return meetings.stream()
	        .filter(meeting -> isOverlapping(currentMeeting.getFromTime(), currentMeeting.getToTime(), meeting.getFromTime(), meeting.getToTime()))
	        .findFirst()
	        .map(meeting -> String.format("Meeting Colliding between time: %s and %s",
	                                      meeting.getFromTime(), meeting.getToTime()))
	        .orElse("");
	}

	public static boolean isOverlapping(LocalDateTime currentFromTime, LocalDateTime currentToTime, LocalDateTime referenceFromTime, LocalDateTime referenceToTime) {
	    return currentFromTime.isBefore(referenceToTime) &&
	    		currentToTime.isAfter(referenceFromTime);
	}
	
	public static Response returnFailureResponse(Meeting meeting, List<Person> person, String msg) { 
		return new Response(meeting, person, 400, msg);
	}
	
	public static Response returnSuccessResponse(Meeting meeting, List<Person> person, String msg) { 
		return new Response(meeting, person, 201, msg);
	}
}
