
## Http Session Manager
A http API session management.

Practical use case: A ecommerce site


# To-do:

- [ ] Add session expiration feature (Paused; first, we need a real service layer)
- [ ] Update basket feature
- [ ] Add Pagination for all API endpoints (We can't display large data sets in only one request)
- [ ] Production and Development environments built in Docker/Docker compose (We want to implement a real datastore based in PostgreSQL, using Docker)

# In process:


# Done:
- [x] Add Slick support in service layer (We want to remove mockups objects and implement real services)
- [x] Add Tests (Could be changed in a future)
- [x] Add logout feature
- [x] Add login feature
- [x] Private endpoints available only for authenticated users
- [x] Retrieve basket feature





#### Project main components:
* Play Framework
* SBT

#### Recommended for testing:
https://www.getpostman.com/


### Endpoints:

#### Login:
POST /login 

#### Logout:
GET /logout

#### User info endpoint:

GET  /index/"user_name" 

#### Get user current basket:

GET  /basket/"user_name"


#### Home page endpoint:
GET  /home


## Running
tested using java 8

```
sbt run
```

And then go to http://localhost:9000 to see the running web application.

