# Job Application Tracker API

- Deals with the job applications.
## Start the database
- Data is saved in PostgreSQL, so it stays after a restart. The list is empty at first. Add applications with POST.

  Open Docker Desktop first. Then start the database:
  
  ```bash
  docker start jobtracker-db
  ```
  
  First time only (this makes the database):
  
  ```bash
  docker run -d --name jobtracker-db -e POSTGRES_USER=jobtracker -e POSTGRES_PASSWORD=jobtracker -e POSTGRES_DB=jobtracker -p 127.0.0.1:5434:5432 postgres:18
  ```

- to run the project use: ./mvnw spring-boot:run -Dspring-boot.run.profiles=dev

curl commands: 
For GET:
- all job applications, one page at a time:
  - ```bash
    curl -s "localhost:8081/api/applications?page=0&size=2&sort=company"
    ```
  - page starts at 0. size = rows per page (default 20). sort = a field name, add `,desc` for Z to A (default: id).
  - Put the URL in double quotes: the terminal cuts it at `&`.
  - Gives `content` (the rows) and `page` (size, number, totalElements, totalPages).

- job applications by id:
  - ```bash 
    curl localhost:8081/api/applications/2
    ```
  - 2 can be replaced with any id(int)
  

- job applications by status, one page at a time:
  - ```bash
    curl -s "localhost:8081/api/applications/status/INTERVIEW?page=0&size=2"
    ```
  - INTERVIEW can be replaced with any status(String). Same page, size and sort as above.
  

- job applications by company:
  - ```bash 
    curl -i localhost:8081/api/applications/company/Swiggy 
    ```
  - Gives a list. An empty list `[]` if no application has this company.
  - Swiggy can be replaced with any company(String)
- job applications of one user:
  - ```bash
    curl -i localhost:8081/api/applications/user/1
    ```
  - Gives a list. An empty list `[]` if this user has no applications.
  - 1 can be replaced with any user id(int)
  - Users are added in psql for now: `INSERT INTO app_user (name) VALUES ('Akash');`

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
curl -i -X PUT localhost:8081/api/applications/1 \
  -H "Content-Type: application/json" \
  -d '{"company":"Swiggy","role":"SDE-1","status":"INTERVIEW"}'
```

Answer: `200` with the new record, or `404` if no application has this id.

Change only the status (the id in the URL picks which one):

- Send only `status`. Company, role and owner stay the same.
- Status must be APPLIED, INTERVIEW, OFFER or REJECTED. Any other word gives `400`.
- `404` if no application has this id.
```bash
curl -i -X PATCH localhost:8081/api/applications/1/status \
  -H "Content-Type: application/json" \
  -d '{"status":"OFFER"}'
```

Delete one application by id:

```bash
curl -i -X DELETE localhost:8081/api/applications/1
```

Answer: `204` (deleted, no body), or `404` if no application has this id.

## Database changes (Flyway)

- Every change to the tables is a SQL file in `src/main/resources/db/migration`.
- Name: `V<number>__<description>.sql` (two underscores). Flyway runs new files at startup, once, in order.
- Never edit a file that already ran. Add a new one (V3, V4, ...).
- Hibernate only checks the tables (`ddl-auto=validate`). It does not change them.
- See what ran, in psql: `SELECT * FROM flyway_schema_history;`

