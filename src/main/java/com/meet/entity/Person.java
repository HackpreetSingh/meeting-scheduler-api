package com.meet.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer meetingIndex;
	
	private Integer personId;
	
	private LocalDateTime fromTime;
	
	private LocalDateTime toTime;
	

	public Person(Integer personId, LocalDateTime fromTime, LocalDateTime toTime) {
		this.personId = personId;
		this.fromTime = fromTime;
		this.toTime = toTime;
	}
}
