package com.meet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.meet.entity.Meeting;

/**
 * Repository interface for managing {@link Meeting} entities.
 * Extends {@link JpaRepository} to provide basic CRUD operations.
 */
@Repository
public interface SchedulerRepository extends JpaRepository<Meeting, Integer> {

    /**
     * Retrieves a list of {@link Meeting} entities based on a specific host ID.
     *
     * @param hostId ID of the host whose meetings are to be retrieved.
     * @return List of {@link Meeting} entities associated with the provided host ID.
     */
    @Query("Select m from Meeting m where m.hostId=?1")
    List<Meeting> findByHostId(Integer hostId);

    /**
     * Updates the list of people included in a specific meeting.
     *
     * @param peopleInMeeting List of IDs of people to be included in the meeting.
     * @param meetingId ID of the meeting to be updated.
     * @return Number of records updated.
     */
    @Modifying
    @Query("UPDATE Meeting m SET m.peopleIncluded = :peopleInMeeting WHERE m.meetingId = :meetingId")
    Integer updatePeopleInMeeting(@Param("peopleInMeeting") List<Integer> peopleInMeeting, @Param("meetingId") Integer meetingId);

}
