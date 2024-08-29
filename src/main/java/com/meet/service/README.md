## PersonService

### Methods

- **createMeetingForAudience(Meeting meeting)**
  - **Description**: Creates a meeting for the audience and saves their availability.
  - **Parameters**:
    - `meeting`: `Meeting` object to be scheduled.
  - **Returns**: `Response` object indicating success or failure.
  - **Logic**:
    - Checks for time collisions with existing meetings using `personTimeColliding()`.
    - Saves new `Person` records to the database.
    - Returns success or failure response using `ApplicationUtils.returnSuccessResponse()` or `ApplicationUtils.returnFailureResponse()`.

- **personTimeColliding(Meeting meeting)**
  - **Description**: Checks if the meeting time collides with existing meetings for the included persons.
  - **Parameters**:
    - `meeting`: `Meeting` object to check for collisions.
  - **Returns**: String describing the collision or an empty string if no collision.
  - **Logic**:
    - Fetches all persons included in the meeting.
    - Checks for overlapping times using `ApplicationUtils.isOverlapping()`.

- **checkPersonAvailability(Integer personId)**
  - **Description**: Checks availability of a person by their ID.
  - **Parameters**:
    - `personId`: ID of the person to check.
  - **Returns**: `Response` object with success or failure status.
  - **Logic**:
    - Fetches person schedule by ID.
    - Returns success or failure response based on existence of the person.

### Dependencies

- **PersonRepository**: Provides methods to interact with the database for `Person` entities.
- **ApplicationUtils**: Utility class for checking collisions and generating responses.


## SchedulerService

### Methods

- **scheduleMeetingWithDateTime(Meeting currentMeeting)**
  - **Description**: Schedules a meeting using date and time information provided in the `Meeting` object.
  - **Parameters**:
    - `currentMeeting`: `Meeting` object with date, time, and period.
  - **Returns**: `Response` object indicating success or failure.
  - **Logic**:
    - Calculates `fromTime` and `toTime` from date, time, and period.
    - Checks for collisions with existing meetings using `ApplicationUtils.checkMeetingColliding()`.
    - Creates meetings for all audience and host.
    - Returns appropriate response.

- **scheduleMeetingWithFromToTime(Meeting currentMeeting)**
  - **Description**: Schedules a meeting using from and to time information provided in the `Meeting` object.
  - **Parameters**:
    - `currentMeeting`: `Meeting` object with from and to time.
  - **Returns**: `Response` object indicating success or failure.
  - **Logic**:
    - Calculates date, time, and period from `fromTime` and `toTime`.
    - Checks for collisions with existing meetings.
    - Creates meetings for all audience and host.
    - Returns appropriate response.

- **addPersonToExistingMeeting(Meeting currentMeeting)**
  - **Description**: Adds new persons to an existing meeting.
  - **Parameters**:
    - `currentMeeting`: `Meeting` object with updated list of people.
  - **Returns**: `Response` object indicating success or failure.
  - **Logic**:
    - Fetches existing meeting by ID.
    - Updates the list of people included in the meeting.
    - Creates meetings for all audience and updates database.
    - Returns success or failure response.

### Dependencies

- **SchedulerRepository**: Provides methods to interact with the database for `Meeting` entities.
- **PersonService**: Used to create meetings for audience.
- **ApplicationUtils**: Utility class for checking collisions and generating responses.

