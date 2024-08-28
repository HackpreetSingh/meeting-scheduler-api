package com.meet.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meet.entity.Meeting;
import com.meet.entity.Person;
import com.meet.entity.Response;
import com.meet.repository.PersonRepository;
import com.meet.util.ApplicationUtils;

@Service
public class PersonService {

	@Autowired
	PersonRepository personRepository;

	public Response createMeetingForAudience(Meeting meeting) {

		String msg = personTimeColliding(meeting);
		if (!msg.isBlank()) {
			return ApplicationUtils.returnFailureResponse(meeting, null, msg);
		}

		List<Integer> personIds = meeting.getPeopleIncluded();

		for (Integer personId : personIds) {
			
			Person newPerson = new Person(personId, meeting.getFromTime(), meeting.getToTime());
			personRepository.saveAndFlush(newPerson);

		}

		return ApplicationUtils.returnSuccessResponse(meeting, null, "Meeting created for all audience");
	}

	private String personTimeColliding(Meeting meeting) {
	    List<Integer> personIds = meeting.getPeopleIncluded();

	    // Fetch all people at once to reduce database queries
	    List<Person> persons = personRepository.findAllByPersonIds(personIds);

	    for(Person person: persons) {
	    	if(ApplicationUtils.isOverlapping(meeting.getFromTime(), meeting.getToTime(), person.getFromTime(), person.getToTime())) {
	    		return "Meeting time overlapping for audience: " + person.getPersonId()+", Existing meeting: " + person.getFromTime()+" - " + person.getToTime();
	    	}
	    }
	    
	    return "";
	}
	
	public Response checkPersonAvailability(Integer personId) {
		List<Person> personSchedule = personRepository.findByPersonId(personId);
		if (personSchedule.isEmpty()) {
			return ApplicationUtils.returnFailureResponse(null, null, "No such person exists!");
		} else {
			return ApplicationUtils.returnSuccessResponse(null, personSchedule, null);
		}
	}
}
