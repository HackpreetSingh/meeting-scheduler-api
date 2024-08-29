## SchedulerController

### Endpoints

- **POST /meeting/scheduleMeetingWithDateTime**
  - **Description**: Schedules a meeting using `Meeting` object with date and time.
  - **Request Body**: `Meeting` object
  - **Response**: `ResponseEntity<?>` with `HttpStatus.OK`
  - **Service**: `SchedulerService.scheduleMeetingWithDateTime(meeting)`

- **POST /meeting/scheduleMeetingWithFromToTime**
  - **Description**: Schedules a meeting using `Meeting` object with from and to time.
  - **Request Body**: `Meeting` object
  - **Response**: `ResponseEntity<?>` with `HttpStatus.OK`
  - **Service**: `SchedulerService.scheduleMeetingWithFromToTime(meeting)`

- **GET /meeting/getPersonAvailability/{personId}**
  - **Description**: Retrieves availability of a person by `personId`.
  - **Path Variable**: `personId` (Integer)
  - **Response**: `ResponseEntity<?>` with `HttpStatus.OK`
  - **Service**: `PersonService.checkPersonAvailability(personId)`

### Dependencies

- **SchedulerService**: Provides methods to schedule meetings.
- **PersonService**: Provides methods to check person availability.

### Annotations

- **@RestController**: Marks the class as a REST controller.
- **@RequestMapping("/meeting")**: Base URL for all endpoints in the controller.
- **@Autowired**: Injects dependencies for `SchedulerService` and `PersonService`.
- **@PostMapping**: Indicates that the method should handle POST requests.
- **@GetMapping**: Indicates that the method should handle GET requests.
- **@RequestBody**: Binds the HTTP request body to a method parameter.
- **@PathVariable**: Binds a method parameter to a URI template variable.
