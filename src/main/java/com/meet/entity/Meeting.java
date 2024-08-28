package com.meet.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Meeting {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer meetingId;
	
	private Integer hostId;
	
	private Integer noOfRooms;
	
	private String meetingHeading;
	
	private LocalDateTime fromTime;
	
	private LocalDateTime toTime;
	
	private LocalDate date;
	
	private LocalTime time;
	
	private Long period;
	
	private List<Integer> peopleIncluded;

	public Meeting(Meeting meeting) {
		super();
		this.meetingId = meeting.getMeetingId();
		this.hostId = meeting.getHostId();
		this.noOfRooms = meeting.getNoOfRooms();
		this.meetingHeading = meeting.getMeetingHeading();
		this.fromTime = meeting.getFromTime();
		this.toTime = meeting.getToTime();
		this.date = meeting.getDate();
		this.time = meeting.getTime();
		this.period = meeting.getPeriod();
		this.peopleIncluded = meeting.getPeopleIncluded();
	}
	
	
}
