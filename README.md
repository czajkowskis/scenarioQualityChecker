# scenarioQualityChecker

Scenario quality checker is being created as a part of Software Engineering course on PUT. The main purpose of the project is to apply the agile workflow to develop an application in groups.

It is a program that will provide quantitative information and enable detection of problems in functional requirements written in the form of scenarios. The application will be available via GUI and also as a remote API, thanks to which it can be integrated with existing tools.

## Information about scenario format:

- The scenario includes a header specifying its title and actors (external and system)
- The scenario consists of steps (each step contains text)
- Steps can contain sub-scenarios (any level of nesting)
- The steps may start with the keywords IF, ELSE, FOR EACH

### Example:

__Title__: Book addition

__Actors__:  Librarian

__System actor__: System

- Librarian selects options to add a new book item
- A form is displayed.
- Librarian provides the details of the book.
- IF: Librarian wishes to add copies of the book
    - Librarian chooses to define instances 
    - System presents defined instances 
    - FOR EACH: instance:
        - Librarian chooses to add an instance 
        - System prompts to enter the instance details 
        - Librarian enters the instance details and confirms them. 
        - System informs about the correct addition of an instance and presents the updated list of instances. 
- Librarian confirms book addition. 
- System informs about the correct addition of the book.

## Project menagement
The project is managed using trello. It is a private board available only to the group and the teacher.

[Project Trello](https://trello.com/b/CqRI8VDn/scenarioqualitychecker)
