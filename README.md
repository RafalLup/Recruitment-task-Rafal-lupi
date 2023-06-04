# TaskRafalLup

TaskRafalLup is a Spring Boot application that retrieves repository information from the GitHub API.

## Features

- Retrieve repository information by providing a GitHub username.
- Get the repository name, owner login, branch name, and commit SHA for each repository.

## Technologies Used

- Java 17
- Spring Boot
- Spring WebFlux
- WebClient
- Junit
- WireMock

## Getting Started

### Prerequisites

- Java 17 installed on your system
- Maven build tool

### Running the Application

   ```shell
   git clone https://github.com/your-username/TaskRafalLup.git
   cd TaskRafalLup
   mvn clean install
   mvn spring-boot:run
   ```
 - The application will start running on http://localhost:8080.

## API Endpoints

- Get Repository Information
- Endpoint: /git
- Method: GET
- Query Parameter:
- name (required): GitHub username
- Example Request:

#### Example Request:

- GET http://localhost:8080/git?name=rafallup

#### Example Response:
```json
[
    {
        "repositoryName": "Rafal-Lupinski-Financial-controller",
        "ownerLogin": "RafalLup",
        "branchName": "master",
        "sha": "4291ae610e16783999772e4fd986dedb59764a5e"
    }
]
```

## Error Handling

- The API provides error handling for certain scenarios. If a GitHub username is not found, the API returns
  a `404 Not Found` error with the following response:

```json
{
  "status": 404,
  "message": "Username not found ."
}
```

- Additionally, if the Accept head in the request contains an unsupported media type, the API returns a 406 Not
  Acceptable error with the following response:

```json
{
  "status": 406,
  "message": "No acceptable response."
}
```