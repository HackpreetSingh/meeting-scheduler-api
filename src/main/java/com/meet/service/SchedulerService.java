package com.meet.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meet.entity.Meeting;
import com.meet.entity.Response;
import com.meet.repository.SchedulerRepository;
import com.meet.util.ApplicationUtils;

@Service
public class SchedulerService {

	@Autowired
	private SchedulerRepository schedulerRepository;

	@Autowired
	private PersonService personService;

	public Response scheduleMeetingWithDateTime(Meeting currentMeeting) {

		// Calculating From and To DateTime using the day, time and period specified
		currentMeeting.setFromTime(LocalDateTime.of(currentMeeting.getDate(), currentMeeting.getTime()));
		currentMeeting.setToTime(LocalDateTime.of(currentMeeting.getDate(),
				currentMeeting.getTime().plusMinutes(currentMeeting.getPeriod())));

		// Checking if any existing meeting collides with the current one
		List<Meeting> meetings = schedulerRepository.findByHostId(currentMeeting.getHostId());
		String checkCollide = ApplicationUtils.checkMeetingColliding(meetings, currentMeeting);

		if (!checkCollide.isEmpty()) {
			return ApplicationUtils.returnFailureResponse(currentMeeting, null, checkCollide);
		}

		// Creating meeting for all audience
		Response response = personService.createMeetingForAudience(currentMeeting);
		if (response.getHttpStatus() == 400) {
			return response;
		}

		// Creating meeting for host
		schedulerRepository.saveAndFlush(currentMeeting);

		return ApplicationUtils.returnSuccessResponse(currentMeeting, null, "Meeting Created");
	}

	public Response scheduleMeetingWithFromToTime(Meeting currentMeeting) {

		// Calculating Date, Time and Period using From and To DateTime as specified
		currentMeeting.setDate(currentMeeting.getFromTime().toLocalDate());
		currentMeeting.setTime(currentMeeting.getToTime().toLocalTime());
		long minutes = currentMeeting.getFromTime().until(currentMeeting.getToTime(), ChronoUnit.MINUTES);
		currentMeeting.setPeriod(minutes);

		// Checking if any existing meeting collides with the current one
		List<Meeting> meetings = schedulerRepository.findByHostId(currentMeeting.getHostId());
		String checkCollide = ApplicationUtils.checkMeetingColliding(meetings, currentMeeting);

		if (!checkCollide.isEmpty()) {
			return ApplicationUtils.returnFailureResponse(currentMeeting, null, checkCollide);
		}

		// Creating meeting for all audience
		Response response = personService.createMeetingForAudience(currentMeeting);
		if (response.getHttpStatus() == 400) {
			return response;
		}

		// Creating meeting for host
		schedulerRepository.saveAndFlush(currentMeeting);

		return ApplicationUtils.returnSuccessResponse(currentMeeting, null, "Meeting Created");
	}

	public Response addPersonToExistingMeeting(Meeting currentMeeting) {

		// Fetching all meetings present for the given meeting id
		Optional<Meeting> existingMeeting = schedulerRepository.findById(currentMeeting.getMeetingId());
		if (existingMeeting.isEmpty()) {
			ApplicationUtils.returnFailureResponse(currentMeeting, null, "No such meeting exists!");
		}

		// Adding all the people missing currently from the meeting
		List<Integer> peopleInMeeting = existingMeeting.get().getPeopleIncluded();
		for (Integer newPerson : currentMeeting.getPeopleIncluded()) {
			if (!peopleInMeeting.contains(newPerson)) {
				peopleInMeeting.add(newPerson);
			}
		}

		
		// Creating meeting for all audience
		Response response = personService.createMeetingForAudience(currentMeeting);
		if (response.getHttpStatus() == 400) {
			return response;
		}

		// Updating in database
		Integer value = schedulerRepository.updatePeopleInMeeting(peopleInMeeting, currentMeeting.getMeetingId());

		if (value == 1) {
			return ApplicationUtils.returnSuccessResponse(currentMeeting, null, "People Updated");
		} else {
			return ApplicationUtils.returnFailureResponse(currentMeeting, null, "People could not be updated");
		}
	}
}
