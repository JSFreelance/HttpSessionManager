
## Http Session Manager
A http API session management.
Practical use case: A ecommerce site

#### Project main components:
* Play Framework
* SBT

### Endpoints:
POST /login 

#### User info endpoint:

GET  /index/"user_name" 

#### Get user current basket:

GET  /basket/"user_name"


#### Home page endpoint:
GET  /home #home page


## Running

```
sbt run
```

And then go to http://localhost:9000 to see the running web application.

