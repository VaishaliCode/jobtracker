# Job Application Tracker API

- Deals with the job applications.
- to run the project use: ./mvnw spring-boot:run -Dspring-boot.run.profiles=dev

curl commands: 
- all job applications: curl localhost:8081/api/applications
- job applications by id: curl localhost:8081/api/applications/2    // 2 can be replaced with any id(int)
- job applications by status: curl localhost:8081/api/applications/status/INTERVIEW    // INTERVIEW this can be replaced by any status(String)
