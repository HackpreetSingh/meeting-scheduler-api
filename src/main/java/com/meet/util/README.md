## ApplicationUtils

### Methods

- **checkMeetingColliding(List<Meeting> meetings, Meeting currentMeeting)**
  - **Description**: Checks if `currentMeeting` collides with any of the provided meetings.
  - **Parameters**:
    - `meetings`: List of `Meeting` objects to check for collisions.
    - `currentMeeting`: The `Meeting` object to check for collisions.
  - **Returns**: String describing the collision or an empty string if no collision.
  - **Logic**: Filters meetings to find overlaps using `isOverlapping()`.

- **isOverlapping(LocalDateTime currentFromTime, LocalDateTime currentToTime, LocalDateTime referenceFromTime, LocalDateTime referenceToTime)**
  - **Description**: Determines if two time periods overlap.
  - **Parameters**:
    - `currentFromTime`: Start time of the current meeting.
    - `currentToTime`: End time of the current meeting.
    - `referenceFromTime`: Start time of the reference meeting.
    - `referenceToTime`: End time of the reference meeting.
  - **Returns**: `boolean` indicating whether the periods overlap.

- **returnFailureResponse(Meeting meeting, List<Person> person, String msg)**
  - **Description**: Creates a `Response` object with failure status.
  - **Parameters**:
    - `meeting`: The `Meeting` object associated with the response.
    - `person`: List of `Person` objects involved.
    - `msg`: Failure message.
  - **Returns**: `Response` object with status code 400 (Bad Request).

- **returnSuccessResponse(Meeting meeting, List<Person> person, String msg)**
  - **Description**: Creates a `Response` object with success status.
  - **Parameters**:
    - `meeting`: The `Meeting` object associated with the response.
    - `person`: List of `Person` objects involved.
    - `msg`: Success message.
  - **Returns**: `Response` object with status code 201 (Created).

### Notes

- **Final Class**: This class is declared as `final` and cannot be subclassed.
- **Utility Methods**: Provides static utility methods for checking meeting collisions and generating responses.
