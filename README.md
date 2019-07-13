# ASGWebApp

This was a group project from the first semester of the second year of university based on Spring Boot and MySQL databases. For this project, we met with a client who requested a web server and a relational database to replace his previous booking and progress system that was handled in Excel. We opted to stick with a standard server-side rendering solution using Thymeleaf, and utilizing various modules like Spring Security and Mail. 

After the first semester, the class continued to use these projects for various assessments in semester two. Each student had their own fork of the project for these, and it was no longer a team product. For DevOps, we needed to configure a Jenkins server to pull the project, run the tests and Checkstyle, then host the running server. These changes have been merged in, however, the remaining modules have not. For our security module, we were tasked with fixing 6 security risks. For performance an scalability, we had to analyze performance, and improve 3 performance bottlenecks. 

This project was very much a 'learn as you go' sort of experience for the class, so some items may be inconsistent, as we eventually found better ways to do some items as we went through.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

What things you need to install the software and how to install them

```
Java 11
MySQL Server
```

### Running

To run the web server on localhost, you will need a local MySQL server running and Java 11 installed. By default, the Spring Boot profiles are configured for a database server running on port 3306. Simply create a table called 'asg', and run in the root project folder:

```
./gradlew bootRun -Pprofiles=development
```

Navigate in to https://localhost:8080/login in your browser of choice to begin using the system. There are 6 default accounts in the system, 2 admin, 4 candidate, and all use the password "pass". Login with admin@asg.com, admin2@asg.com, candidate@asg.com, ..., candidate4@asg.com. Navigate the site from there.

## Authors

* **Ryan Christian** - *All Around Developer* - [Ryan Christian](https://gitlab.cs.cf.ac.uk/c1717381)
* **James Apps** - *All Around Developer* - [James Apps](https://gitlab.cs.cf.ac.uk/c1769423)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Thanks to Aviation Systems Group for giving us this project to work on, and allowing us to use this as part of our portfolios.
