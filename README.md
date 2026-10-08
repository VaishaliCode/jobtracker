# Job Application Tracker API

- Deals with the job applications.
- to run the project use: ./mvnw spring-boot:run -Dspring-boot.run.profiles=dev

curl commands: 
For GET:
- all job applications: curl localhost:8081/api/applications
- job applications by id: curl localhost:8081/api/applications/2    // 2 can be replaced with any id(int).
- job applications by status: curl localhost:8081/api/applications/status/INTERVIEW    // INTERVIEW this can be replaced by any status(String).
- job applications by company: curl -i localhost:8081/api/applications/company/Swiggy   // -i is optional & Swiggy can be replaced by any company(String).

For POST:
- curl -i -X POST localhost:8081/api/applications \
  -H "Content-Type: application/json" \
  -d '{"id":1,"company":"Ola","role":"SDE-1","status":"APPLIED"}'     // -d if for body so {...} is replaceable with new body{JSON}   