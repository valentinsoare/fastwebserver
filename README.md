# FastWebServer with CI/CD

This project, named `FastWebServer with CI/CD`, is a simple yet powerful web server implemented in Java. It is designed with concurrency and asynchronous operations in mind, leveraging the capabilities of the Java NIO library.

## Technologies Used

The project is built using the following technologies:

- **Java**: The primary programming language used for development.
- **Spring Boot**: A popular framework used for building stand-alone, production-grade Spring-based applications.
- **Maven**: A software project management and comprehension tool.
- **GraalVM**: Used for compiling the application into a native image for faster startup times and lower runtime memory overhead.
- **GitLab CI/CD**: Used for continuous integration and continuous deployment.
- **Docker**: Used for creating a containerized version of the application.

## CI/CD Pipeline

The project includes a CI/CD pipeline that automates the processes of cloning the repository, testing the clone, building the application binary, and building the docker image of the application. This pipeline is defined in the `.gitlab-ci.yml` file and uses a custom Docker image (`vsoare/image-to-execute-pipelines:0.0.1`) to execute the pipeline stages.

## more to come...