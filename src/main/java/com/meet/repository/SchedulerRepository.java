package com.meet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.meet.entity.Meeting;

@Repository
public interface SchedulerRepository extends JpaRepository<Meeting, Integer> {

	@Query("Select m from Meeting m where m.hostId=?1")
	List<Meeting> findByHostId(Integer hostId);

	@Modifying
	@Query("UPDATE Meeting m SET m.peopleIncluded = :peopleInMeeting WHERE m.meetingId = :meetingId")
	Integer updatePeopleInMeeting(@Param("peopleInMeeting") List<Integer> peopleInMeeting, @Param("meetingId") Integer meetingId);

}
