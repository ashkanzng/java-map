## Task Description

The task is to implement Rest-API for the electric vehicle charging station management system.

# Crossref REST API

- Crossref REST API
    - [Requirement](#requirement)
    - [Installation](#installation)
    - [Create Company](#create-company)
    - [Update Company](#update-company)
    - [Delete Company](#meta)
    - [Get Company](#meta)


### Requirement
* java 8+

### Installation  

#### Option 1
- Clone the repo
   ```sh
        git clone https://github.com/ashkanzng/virta-task.git
        cd virta-task
        mvn spring-boot:run 
   ```
  **open browser to http://localhost:8080**

#### Option 2
- Download jar file and run the app
   ```sh
        java -jar xxx.jar
   ```


### Create Company.

```JS
    request

    POST http://localhost:8080/api/company/add
    Content-Type:application/json


```

### Update Company.
```JS
    request

    POST http://localhost:8080/api/company/update/{company_id}
    Content-Type:application/json
    
```
    
### Delete Company.
    



### Task 2

###### Implement endpoint which gets all stations.

4. Enter your API in `config.js`
   ```Java
   String name = "asas";

### Usage

Use this space to show useful examples of how a project can be used. Additional screenshots, code examples and demos work well in this space. You may also link to more resources.

