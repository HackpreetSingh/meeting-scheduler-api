## PersonRepository

**Interface**: `PersonRepository`  
**Package**: `com.meet.repository`  
**Inheritance**: Extends `JpaRepository<Person, Integer>`  
**Purpose**: Provides methods for database operations related to `Person` entities.

### Methods

- **findAllByPersonIds(List<Integer> personIds)**
  - **Description**: Retrieves a list of `Person` entities whose `personId` is in the provided list of IDs.
  - **Parameters**:
    - `personIds`: List of `Integer` IDs to search for.
  - **Returns**: `List<Person>` - List of `Person` entities matching the provided IDs.
  - **Query**: `SELECT p FROM Person p WHERE p.personId IN ?1`

- **findByPersonId(Integer personId)**
  - **Description**: Retrieves a list of `Person` entities by a specific `personId`.
  - **Parameters**:
    - `personId`: ID of the `Person` to search for.
  - **Returns**: `List<Person>` - List of `Person` entities matching the provided ID.
  - **Query**: `SELECT p FROM Person p WHERE p.personId = ?1`

### Annotations

- **@Repository**: Marks the interface as a Spring Data repository.
- **@Query**: Custom query annotations to define specific queries for the repository methods.

### Dependencies

- **Person**: Entity class representing a person.
- **JpaRepository**: Provides standard CRUD operations and additional query methods.


## SchedulerRepository

**Interface**: `SchedulerRepository`  
**Package**: `com.meet.repository`  
**Inheritance**: Extends `JpaRepository<Meeting, Integer>`  
**Purpose**: Provides methods for database operations related to `Meeting` entities.

### Methods

- **findByHostId(Integer hostId)**
  - **Description**: Retrieves a list of `Meeting` entities associated with a specific host ID.
  - **Parameters**:
    - `hostId`: `Integer` ID of the host.
  - **Returns**: `List<Meeting>` - List of `Meeting` entities for the given host ID.
  - **Query**: `SELECT m FROM Meeting m WHERE m.hostId = ?1`

- **updatePeopleInMeeting(List<Integer> peopleInMeeting, Integer meetingId)**
  - **Description**: Updates the list of people included in a specific meeting.
  - **Parameters**:
    - `peopleInMeeting`: `List<Integer>` - Updated list of people to be included in the meeting.
    - `meetingId`: `Integer` - ID of the meeting to be updated.
  - **Returns**: `Integer` - Number of records updated.
  - **Query**: `UPDATE Meeting m SET m.peopleIncluded = :peopleInMeeting WHERE m.meetingId = :meetingId`
  - **Annotations**: 
    - `@Modifying`: Indicates that this query is an update/delete operation.

### Annotations

- **@Repository**: Marks the interface as a Spring Data repository.
- **@Query**: Custom query annotations to define specific queries.
- **@Modifying**: Marks the query method as a modifying query (insert/update/delete).
- **@Param**: Specifies parameter names for queries.

### Dependencies

- **Meeting**: Entity class representing a meeting.
- **JpaRepository**: Provides standard CRUD operations and additional query methods.
