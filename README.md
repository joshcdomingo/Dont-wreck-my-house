# Project Plan
### Create Repository Layer
* [ ] Create a Guest File Repository
    * Methods
        * findAll
        * findById
* [ ] Create a Host File Repository
    * Methods
        * findByName
        * findByLocation
        *  findById
* [ ] Create a Reservation File Repository
    * Methods
        * create
        * update
        * delete
        * findByReservation
        * writeAll
        * getFilePath
        * serialize
        * deserialize
* [ ] Create a Guest Repository
    * Methods
        * findAll
        * findById
        * findByName (Just in case)
* [ ] Create a Host Repository
    * Methods
        * findAll
        * findByName (just in case)
        * findById
* [ ] Create a Reservation Repository
    * Methods
        * findByReservation
        * create
        * update
        * delete
### Create Domain Layer
* [ ] Create a Guest Service
* Methods
    * findById
    * findByName
    * findAll
    * validate
* [ ] Create a Host Service
    * Methods
        * findByName
        * findByLocation
        * findById
        * validate
* [ ] Create a Reservation Service
    * Methods
        * findByReservation
        * create
        * findByName
        * validate
* [ ] Create A Result class
    * Methods
        * payLoad
        * setPayload
* [ ] Create A Response class
    * Methods
        * isSuccess
        * errorMessages
        * addErrorMessages
### Create Models Layer
* [ ] Create Guest class
* Methods
    * constructor
    * getters and setters
* [ ] Create Host class
    * Methods
        * constructor
        * getters and setters
* [ ] Create Reservation Class
    * Methods
        * constructor
        * getters and setters
### Create UI Layer
* [ ] Create a View Class
    * A Method to exit
    * A Method to View Reservation
    * A Method to Make Reservation
    * A method to Cancel Reservation
* [ ] Create a Controller Class
    * Should be a class to run everything within the UI Layer
    * Methods
        * RunApp (Do while with menu options that connect with the menu enum)
        * viewReservationByHost
        * createReservation
        * updateReservation
        * deleteReservation
* [ ] Create IO Class (For reading in certain types)
    * ReadRequiredStrings
    * ReadBoolean
    * readInt
    * For error checking specific user inputs
* [ ] Create Menu Class
    * Enum Class for specific options
### Serialize and Deserialize data depending on the data



## Tasks
### High-Level Requirements
The application user is an accommodation administrator. They pair guests to hosts to make reservations.

- The administrator may view existing reservations for a host.
- The administrator may create a reservation for a guest with a host.
- The administrator may edit existing reservations.
- The administrator may cancel a future reservation.
  There are four required scenarios.

### Requirements

### View Reservations for Host
Display all reservations for a host.

- The user may enter a value that uniquely identifies a host or they can search for a host and pick one out of a list.
- If the host is not found, display a message.
- If the host has no reservations, display a message.
- Show all reservations for that host.
- Show useful information for each reservation: the guest, dates, totals, etc.
- Sort reservations in a meaningful way.
### Make a Reservation
Books accommodations for a guest at a host.

- The user may enter a value that uniquely identifies a guest or they can search for a guest and pick one out of a list.
- The user may enter a value that uniquely identifies a host or they can search for a host and pick one out of a list.
- Show all future reservations for that host so the administrator can choose available dates.
- Enter a start and end date for the reservation.
- Calculate the total, display a summary, and ask the user to confirm. The reservation total is based on the host's standard rate and weekend rate. For each day in the reservation, determine if it is a weekday (Sunday, Monday, Tuesday, Wednesday, or Thursday) or a weekend (Friday or Saturday). If it's a weekday, the standard rate applies. If it's a weekend, the weekend rate applies.
- On confirmation, save the reservation.

#### Validation

- Guest, host, and start and end dates are required.
- The guest and host must already exist in the "database". Guests and hosts cannot be created.
- The start date must come before the end date.
- The reservation may never overlap existing reservation dates.
- The start date must be in the future.

### Edit a Reservation
Edits an existing reservation.

- Find a reservation.
- Start and end date can be edited. No other data can be edited.
- Recalculate the total, display a summary, and ask the user to confirm.
#### Validation

- Guest, host, and start and end dates are required.
- The guest and host must already exist in the "database". Guests and hosts cannot be created.
- The start date must come before the end date.
- The reservation may never overlap existing reservation dates.
### Cancel a Reservation
Cancel a future reservation.

- Find a reservation.
- Only future reservations are shown.
- On success, display a message.
#### Validation

- You cannot cancel a reservation that's in the past.

### Technical Requirements
- Must be a Maven project.
- Spring dependency injection configured with either XML or annotations.
- All financial math must use BigDecimal.
- Dates must be LocalDate, never strings.
- All file data must be represented in models in the application.
- Reservation identifiers are unique per host, not unique across the entire application. Effectively, the combination of a reservation identifier and a host identifier is required to uniquely identify a reservation.
### File Format
The data file format is bad, but it's required. You may not change the file formats or the file delimiters. You must use the files provided. Guests are stored in their own comma-delimited file, guests.csv, with a header row. Hosts are stored in their own comma-delimited file, hosts.csv, with a header row.

Reservations are stored across many files, one for each host. A host reservation file name has the format: {host-identifier}.csv.

### Examples
Reservations for host c6567347-6c57-4658-a2c7-50040eeeb80f are stored in ```c6567347-6c57-4658-a2c7-50040eeeb80f.csv```

Reservations for host 54508cfa-4f67-4de8-9437-6f27d65b0af0 are stored in ```54508cfa-4f67-4de8-9437-6f27d65b0af0.csv```

Reservations for host 6a3ef437-289c-40a9-b88a-dd70fad3fdbc are stored in ```6a3ef437-289c-40a9-b88a-dd70fad3fdbc.csv```

### Testing
All data components must be thoroughly tested. Tests must always run successfully regardless of the data starting point, so it's important to establish known good state. Never test with "production" data.

All domain components must be thoroughly tested using test doubles. Do not use file repositories for domain testing.

User interface testing is not required.

## Schedule
* [ ] Start on back pertaining to the domain, models and data
    * [ ] Make sure the data files are in
    * [ ] Test Methods to ensure no bugs
    * [ ] Create Data Exception
* [ ] Start working on the front of the code pertaining to the UI
    * [ ] Create the Main Menu using enum
    * [ ] create the console that'll run the app into App.java
    * [ ] Work and ensure features of the code works in the View
* [ ] Manual Testing and Testing
    * [ ] Test Features Work
    * [ ] Test that the error checks work (everything in the domain is doing its job)
    * [ ] Ensure that data aligns with one another
    * [ ] Meets Clients expectation
## Approach
Plan before you write code. This is a large project. It will be difficult to achieve success without a plan.

Ask questions. The specification isn't perfect. You must ask questions to clarify. Don't make assumptions when things aren't clear.

Test as you go. If you wait to test until the end, three things happen:

1. Your tests aren't as good.
2. The last bit of development becomes a slog. No one loves 100% testing. Spread the testing out over several days.
3. You gain false confidence because you don't see domain failures that are prevented in the UI. Remember, if we throw away the UI, the domain should still prevent all invalid transactions.

## Architecture
```
src
├───main
│   ├───java
│   │   └───learn
│   │       └───mastery
│   │           │   App.java
│   │           │
│   │           ├───data
│   │           │       DataException.java
│   │           │       GuestFileRepository.java
│   │           │       HostFileRepository.java
│   │           │       ReservationFileRepository.java
│   │           │       GuestRepository.java
│   │           │       HostRepository.java
│   │           │       ReservationRepository.java
│   │           │
│   │           ├───domain
│   │           │       GuestService.java
│   │           │       HostService.java
│   │           │       ReservationService.java
│   │           │       Result.java
│   │           │       Response.java
│   │           │
│   │           ├───models
│   │           │       Guest.java
│   │           │       Host.java
│   │           │       Reservation.java
│   │           │
│   │           └───ui
│   │                   ConsoleIO.java
│   │                   Controller.java
│   │                   MainMenuOption.java
│   │                   View.java
│   │
│   └───resources
└───test
    └───java
        └───learn
            └───mastery
                ├───data
                │       GuestFileRepositoryTest.java
                │       HostFileRepositoryTest.java
                │       ReservationFileRepositoryTest.java
                │       GuestFileRepositoryDouble.java
                │       HostFileRepositoryDouble.java
                │       ReservationFileRepositoryDouble.java
                │
                └───domain
                        GuestServiceTest.java
                        HostServiceTest.java
                        ReservationServiceTest.java
```


## Sample UI
##### Samples are for inspiration only. They are not requirements.

##### For example, the samples use email addresses to find both guests and hosts. This is not a requirement. The only requirement is that your user interface is pleasant and easy to use.

Main Menu
```
Main Menu
=========
0. Exit
1. View Reservations for Host
2. Make a Reservation
3. Edit a Reservation
4. Cancel a Reservation
   Select [0-4]:
```
View Reservations for Host
```
   View Reservations for Host
   ==========================
   Host Email: bcharon56@storify.com

Charon: Tampa, FL
=================
ID: 8, 08/12/2020 - 08/18/2020, Guest: Carncross, Tremain, Email: tcarncross2@japanpost.jp
ID: 2, 09/06/2020 - 09/07/2020, Guest: Rogliero, Kayley, Email: kroglieroo@goo.gl
ID: 12, 10/12/2020 - 10/15/2020, Guest: Dodimead, Natal, Email: ndodimead7@yellowbook.com
ID: 7, 10/19/2020 - 10/20/2020, Guest: Nozzolii, Rebecka, Email: rnozzoliid7@webmd.com
ID: 4, 11/27/2020 - 11/28/2020, Guest: Ferminger, Carl, Email: cfermingerfr@bbb.org
ID: 5, 01/14/2021 - 01/18/2021, Guest: Garlee, Raina, Email: rgarlee9j@indiatimes.com
ID: 10, 01/24/2021 - 01/29/2021, Guest: Gaish, Bonnibelle, Email: bgaish3q@mediafire.com
ID: 1, 02/11/2021 - 02/16/2021, Guest: Carefull, Kendall, Email: kcarefullge@cnbc.com
ID: 11, 03/23/2021 - 03/25/2021, Guest: Cadreman, Marjorie, Email: mcadremanmv@nps.gov
ID: 6, 03/26/2021 - 03/31/2021, Guest: Dodimead, Natal, Email: ndodimead7@yellowbook.com
ID: 9, 06/04/2021 - 06/08/2021, Guest: Rossin, Vince, Email: vrossin68@miibeian.gov.cn
ID: 3, 06/23/2021 - 06/28/2021, Guest: Fraczak, Raymond, Email: rfraczakb5@ifeng.com
```
Make a Reservation
```
Make a Reservation
==================
Guest Email: spawlowiczhj@opera.com
Host Email: bcharon56@storify.com

Charon: Tampa, FL
=================
ID: 8, 08/12/2020 - 08/18/2020, Guest: Carncross, Tremain, Email: tcarncross2@japanpost.jp
ID: 2, 09/06/2020 - 09/07/2020, Guest: Rogliero, Kayley, Email: kroglieroo@goo.gl
ID: 12, 10/12/2020 - 10/15/2020, Guest: Dodimead, Natal, Email: ndodimead7@yellowbook.com
ID: 7, 10/19/2020 - 10/20/2020, Guest: Nozzolii, Rebecka, Email: rnozzoliid7@webmd.com
ID: 4, 11/27/2020 - 11/28/2020, Guest: Ferminger, Carl, Email: cfermingerfr@bbb.org
ID: 5, 01/14/2021 - 01/18/2021, Guest: Garlee, Raina, Email: rgarlee9j@indiatimes.com
ID: 10, 01/24/2021 - 01/29/2021, Guest: Gaish, Bonnibelle, Email: bgaish3q@mediafire.com
ID: 1, 02/11/2021 - 02/16/2021, Guest: Carefull, Kendall, Email: kcarefullge@cnbc.com
ID: 11, 03/23/2021 - 03/25/2021, Guest: Cadreman, Marjorie, Email: mcadremanmv@nps.gov
ID: 6, 03/26/2021 - 03/31/2021, Guest: Dodimead, Natal, Email: ndodimead7@yellowbook.com
ID: 9, 06/04/2021 - 06/08/2021, Guest: Rossin, Vince, Email: vrossin68@miibeian.gov.cn
ID: 3, 06/23/2021 - 06/28/2021, Guest: Fraczak, Raymond, Email: rfraczakb5@ifeng.com
Start (MM/dd/yyyy): 07/31/2020
End (MM/dd/yyyy): 08/05/2020

Summary
=======
Start: 07/31/2020
End 08/05/2020
Total: $2090.00
Is this okay? [y/n]: y

Success
=======
Reservation 13 created.
```