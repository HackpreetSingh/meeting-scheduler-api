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

    /**
     * Unique identifier for the Person entity.
     * Auto-generated by the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer meetingIndex;

    /**
     * Unique ID for the person.
     */
    private Integer personId;

    /**
     * The start time of the person's availability or meeting.
     */
    private LocalDateTime fromTime;

    /**
     * The end time of the person's availability or meeting.
     */
    private LocalDateTime toTime;

    /**
     * Constructs a new Person with the specified personId, fromTime, and toTime.
     * @param personId The unique identifier for the person.
     * @param fromTime The start time of the person's availability or meeting.
     * @param toTime The end time of the person's availability or meeting.
     */
    public Person(Integer personId, LocalDateTime fromTime, LocalDateTime toTime) {
        this.personId = personId;
        this.fromTime = fromTime;
        this.toTime = toTime;
    }
}
