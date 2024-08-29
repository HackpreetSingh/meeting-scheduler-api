## Meeting Scheduler REST API

## Table of Contents

- [Functionality](#functionality)
- [Scheduling Inputs](#scheduling-inputs)
- [Collision Detection](#collision-detection)
- [Meeting Rooms](#meeting-rooms)
- [Participants](#participants)
- [Availability Check](#availability-check)
- [API Endpoints](#api-endpoints)
    [i.   Schedule a Meeting with From-To Time](#1-schedule-a-meeting-with-from-to-time)
    [ii.  Get Person Availability](#2-get-person-availability)
    [iii. Check Meeting Collision (Alternative Example)](#3-check-meeting-collision-alternative-example)
    [iv.  Schedule a Meeting with Date and Time (Alternative Example 1)](#4-schedule-a-meeting-with-date-and-time-alternative-example-1)
    [v.   Schedule a Meeting with Date and Time (Alternative Example 2)](#5-schedule-a-meeting-with-date-and-time-alternative-example-2)
- [Controller Documentation](src/main/java/com/meet/controller/README.md)
- [Service Documentation](src/main/java/com/meet/service/README.md)
- [Repository Documentation](src/main/java/com/meet/repository/README.md)
- [Utility Documentation](src/main/java/com/meet/util/README.md)
- [Entity Documentation](src/main/java/com/meet/entity/README.md)


- **Functionality**: Meeting scheduler REST API.

- **Scheduling Inputs**:
  - Schedule meetings using:
    - Day, time, and period.
    - From time to to time.

- **Collision Detection**:
  - Detect scheduling conflicts for both:
    - The host.
    - Participants.

- **Meeting Rooms**:
  - Configure the number of meeting rooms as a parameter.

- **Participants**:
  - Add new participants to an existing meeting.
  - Collision detection for newly added participants.

- **Availability Check**:
  - Check availability based on a person’s existing meetings.


## Sample Postman Curls to test the API

### 1. Schedule a Meeting with From-To Time

```bash
curl --location 'http://localhost:8080/meeting/scheduleMeetingWithFromToTime' \
--header 'Content-Type: application/json' \
--data '{
    "hostId": "1",
    "meetingHeading": "ABC",
    "noOfRooms": 4,
    "fromTime": "2024-08-30T10:00:00.000",
    "toTime": "2024-08-30T12:00:00.000",
    "date": "",
    "time": "",
    "period": "",
    "peopleIncluded": [1,2,3]
}'
```

### 2. Get Person Availability

```bash
curl --location 'http://localhost:8080/meeting/getPersonAvailability/1'
```

### 3. Check Meeting Collision

```bash
curl --location 'http://localhost:8080/meeting/scheduleMeetingWithFromToTime' \
--header 'Content-Type: application/json' \
--data '{
    "hostId": "1",
    "meetingHeading": "ABC",
    "noOfRooms": 4,
    "fromTime": "2024-08-30T09:00:00.000",
    "toTime": "2024-08-30T10:05:00.000",
    "date": "",
    "time": "",
    "period": "",
    "peopleIncluded": [1,2,3]
}'
```

### 4. Check Meeting Collision

```bash
curl --location 'http://localhost:8080/meeting/scheduleMeetingWithDateTime' \
--header 'Content-Type: application/json' \
--data '{
    "hostId": "2",
    "meetingHeading": "ABC",
    "noOfRooms": 4,
    "fromTime": "",
    "toTime": "",
    "date": "2024-08-30",
    "time": "12:00:00",
    "period": 120,
    "peopleIncluded": [1,2,3]
}'
```

### 5. Check Meeting Collision

```bash
curl --location 'http://localhost:8080/meeting/scheduleMeetingWithDateTime' \
--header 'Content-Type: application/json' \
--data '{
    "hostId": "1",
    "meetingHeading": "ABC",
    "noOfRooms": 4,
    "fromTime": "",
    "toTime": "",
    "date": "2024-08-30",
    "time": "11:45:00",
    "period": 120,
    "peopleIncluded": [1,2,3]
}'
```