# Job Application Tracker API

- Deals with the job applications.
- to run the project use: ./mvnw spring-boot:run -Dspring-boot.run.profiles=dev

curl commands: 
For GET:
- all job applications:  
  - ```bash 
    curl localhost:8081/api/applications
    ```
- job applications by id:
  - ```bash 
    curl localhost:8081/api/applications/2
    ```
  - 2 can be replaced with any id(int)
  

- job applications by status: 
  - ```bash 
    curl localhost:8081/api/applications/status/INTERVIEW 
    ```
  - INTERVIEW can be replaced with any status(String)
  

- job applications by company: 
  - ```bash 
    curl -i localhost:8081/api/applications/company/Swiggy 
    ```
  - Swiggy can be replaced with any company(String)

For POST:
- Don't send an id. The server picks a new id and sends it back in the answer.
- Status must be APPLIED, INTERVIEW, OFFER or REJECTED. Any other word gives `400`.

  - ```bash 
    curl -i -X POST localhost:8081/api/applications \
    -H "Content-Type: application/json" \
    -d '{"company":"Ola","role":"SDE-1","status":"APPLIED"}'
    ```

Update one application (the id in the URL picks which one; send all three fields):

- A blank company, role, or status gives `400`. Nothing is saved.
```bash
curl -i -X PUT localhost:8081/api/applications/33 \
  -H "Content-Type: application/json" \
  -d '{"company":"Swiggy","role":"SDE-1","status":"INTERVIEW"}'
```

Answer: `200` with the new record, or `404` if no application has this id.

Delete one application by id:

```bash
curl -i -X DELETE localhost:8081/api/applications/33
```

Answer: `204` (deleted, no body), or `404` if no application has this id.


