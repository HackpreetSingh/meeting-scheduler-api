## Design for Entity Classes

```
+-----------------------------+
|           Meeting            |
+-----------------------------+
| - meetingId: Integer         |
| - hostId: Integer            |
| - noOfRooms: Integer         |
| - meetingHeading: String    |
| - fromTime: LocalDateTime   |
| - toTime: LocalDateTime     |
| - date: LocalDate           |
| - time: LocalTime           |
| - period: Long              |
| - peopleIncluded: List<Integer> |
+-----------------------------+
| + Meeting()                 |
| + Meeting(Meeting meeting) |
+-----------------------------+
```


```
+--------------------------------------+ 
| 	    Person 	      	       | 
+--------------------------------------+ 
| - meetingIndex: Integer              | 
| - personId: Integer 		       | 
| - fromTime: LocalDateTime 	       | 
| - toTime: LocalDateTime 	       | 
+--------------------------------------+ 
| 	+ Person() 		       | 
| + Person(personId, fromTime, toTime) | 
+--------------------------------------+
```

```
+-----------------------------------------------+
|           Response          			|
+-----------------------------------------------+
| - meeting: Meeting          			|
| - person: List<Person>      			|
| - httpStatus: Integer       			|
| - msg: String               			|
+-----------------------------------------------+
| + Response()                			|
| + Response(meeting, person, httpStatus, msg) |
+-----------------------------------------------+
```
