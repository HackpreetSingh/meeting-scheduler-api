package com.meet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meet.entity.Meeting;
import com.meet.service.PersonService;
import com.meet.service.SchedulerService;

@RestController
@RequestMapping("/meeting")
public class SchedulerController {

    @Autowired
    private SchedulerService schedulerService;

    @Autowired
    private PersonService personService;

    /**
     * Schedules a meeting using date and time.
     * @param meeting The meeting details provided in the request body.
     * @return A ResponseEntity with the result of the scheduling operation.
     */
    @PostMapping("/scheduleMeetingWithDateTime")
    public ResponseEntity<?> scheduleMeetingWithDateTime(@RequestBody Meeting meeting) {
        return new ResponseEntity<>(schedulerService.scheduleMeetingWithDateTime(meeting), HttpStatus.OK);
    }

    /**
     * Schedules a meeting using from and to time.
     * @param meeting The meeting details provided in the request body.
     * @return A ResponseEntity with the result of the scheduling operation.
     */
    @PostMapping("/scheduleMeetingWithFromToTime")
    public ResponseEntity<?> scheduleMeetingWithFromToTime(@RequestBody Meeting meeting) {
        return new ResponseEntity<>(schedulerService.scheduleMeetingWithFromToTime(meeting), HttpStatus.OK);
    }

    /**
     * Retrieves the availability of a person.
     * @param personId The ID of the person whose availability is being queried.
     * @return A ResponseEntity with the availability information of the specified person.
     */
    @GetMapping("/getPersonAvailability/{personId}")
    public ResponseEntity<?> getPersonAvailability(@PathVariable("personId") Integer personId) {
        return new ResponseEntity<>(personService.checkPersonAvailability(personId), HttpStatus.OK);
    }
}
