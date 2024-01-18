# User service

Test exercise for a job interview.

A simple service with two endpoints:

- `POST /user` - create a user. Accepts user info and returns created user info
- `GET /user/{id}` - fetch info about a registered user (or error 404 if there is no user with such id)

You can find examples of requests in [Postman collection](user_service.postman_collection.json).

User registration request body example:

    {
        "userName": "test",
        "birthdate": "1995-06-06",
        "countryOfResidence": "France",
        "phoneNumber": "33123456789",
        "gender": "FEMALE"
    }

User registration request and fetch user info request responses example:

    {
        "id": 1,
        "userName": "test",
        "birthdate": "1995-06-06",
        "countryOfResidence": "France",
        "phoneNumber": "33123456789",
        "gender": "FEMALE"
    }

There is some validation for input data (HTTP error 400 returned if validation was failed):

- `userName`, `birthdate` and `countryOfResidence` are required fields
- `userName` and `countryOfResidence` cannot be blank
- `userName` must be unique (if you attempt to register same-named user the second time you'll receive HTTP error 409)
- `phoneNumber` validated against regex
- `gender` must be either `MALE`, `FEMALE` or `OTHER`

Also service allows creation only of adult (18+) users residing in France
(HTTP error 403 returned if it is not the case).

Project uses embedded in-memory H2 database, so it can be easily launched without any prior configuration.

Project has several unit tests and integration tests.

## Project structure

- `.controller` - REST controllers
- `.service` - Business logic services
- `.model.enumeration` - Enumerations
- `.model.dto` - DTOs
- `.model.entity` - JPA entities
- `.model.repository` - JPA repositories
- `.model.mapper` - Entity to DTO and DTO to entity mappers
- `.exception` - Exception classes
- `.config` - Configuration classes and helpers
