package com.meet.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meet.entity.Meeting;
import com.meet.entity.Person;
import com.meet.entity.Response;
import com.meet.repository.PersonRepository;
import com.meet.util.ApplicationUtils;

/**
 * Service class for managing {@link Person} entities and interactions with meetings.
 * Handles business logic related to person availability and meeting scheduling.
 */
@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    /**
     * Creates meetings for all audience members included in the given meeting.
     * Checks for any time collisions with existing meetings before creating new entries.
     *
     * @param meeting the meeting details to be created.
     * @return {@link Response} indicating success or failure of the operation.
     */
    public Response createMeetingForAudience(Meeting meeting) {

        // Check for time collisions with existing meetings
        String msg = personTimeColliding(meeting);
        if (!msg.isBlank()) {
            return ApplicationUtils.returnFailureResponse(meeting, null, msg);
        }

        // Create and save new Person entities for each person included in the meeting
        List<Integer> personIds = meeting.getPeopleIncluded();

        for (Integer personId : personIds) {
            Person newPerson = new Person(personId, meeting.getFromTime(), meeting.getToTime());
            personRepository.saveAndFlush(newPerson);
        }

        return ApplicationUtils.returnSuccessResponse(meeting, null, "Meeting created for all audience");
    }

    /**
     * Checks if the meeting times overlap with existing meetings for the included persons.
     *
     * @param meeting the meeting to be checked for time collisions.
     * @return a message indicating any time collisions found; empty string if no collisions.
     */
    private String personTimeColliding(Meeting meeting) {
        List<Integer> personIds = meeting.getPeopleIncluded();

        // Fetch all people at once to reduce database queries
        List<Person> persons = personRepository.findAllByPersonIds(personIds);

        for(Person person: persons) {
            if(ApplicationUtils.isOverlapping(meeting.getFromTime(), meeting.getToTime(), person.getFromTime(), person.getToTime())) {
                return "Meeting time overlapping for audience: " + person.getPersonId() + ", Existing meeting: " + person.getFromTime() + " - " + person.getToTime();
            }
        }

        return "";
    }
    
    /**
     * Checks the availability of a person based on their ID.
     *
     * @param personId the ID of the person whose availability is to be checked.
     * @return {@link Response} indicating the availability status of the person.
     */
    public Response checkPersonAvailability(Integer personId) {
        List<Person> personSchedule = personRepository.findByPersonId(personId);
        if (personSchedule.isEmpty()) {
            return ApplicationUtils.returnFailureResponse(null, null, "No such person exists!");
        } else {
            return ApplicationUtils.returnSuccessResponse(null, personSchedule, null);
        }
    }
}
