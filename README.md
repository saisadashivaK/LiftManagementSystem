# Lift Management System in Java

## Acknowledgements
I have implemented a system discussed with Sai Abhishek BH, Debasis Dash and others. I have included their names since their ideas and discussion with them helped me to solve difficulties related to the project

## Building the project
To build the main LiftManagementSystem project navigate to the downloaded repository - 
```console
  LiftManagementSystem$ mvn package
```
To build the LiftClient
```console
  LiftManagementSystem$ cd LiftClient
  LiftManagementSystem/LiftClient$ javac LiftClient.java
```

## Running the project
To run the project, first you need to run the LiftManagementSystem client - 
```console
  LiftManagementSystem$ java -jar target/LiftManagementSystem-1.0-SNAPSHOT.jar
  WELCOME TO THE LIFT MANAGEMENT SYSTEM
  Lift  0 at floor:    0
  Lift  1 at floor:    0
```
Next, you can run multiple instances of the LiftClient program on different shells.
```console
  LiftManagementSystem$ cd LiftClient
  LiftManagementSystem/LiftClient $ java LiftClient  
```
The shell of LiftClient will wait for user input.
There are three types of requests - 
- SUMMON_UP:
  Request is of the form ```SUMMON_UP <floorNumber>``` eg: ```SUMMON_UP 2```.
- SUMMON_DOWN
  Request is of the form ```SUMMON_DOWN <floorNumber>``` eg: ```SUMMON_DOWN 1```.
- FLOOR
  Request is of the form ```FLOOR <floorNumber> <liftNumber>``` eg: ```SUMMON_DOWN 1 0```. Lift numbers start from ```0``` to ```numLifts - 1```(see ```App.java``` for the number of lifts).


