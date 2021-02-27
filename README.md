## Task Description

The task is to implement Rest-API for the electric vehicle charging station management system.

# Crossref REST API

- Crossref REST API
    - [Requirement](#requirement)
    - [Installation](#installation)
    - [Create Company](#create-company)
    - [Get Company](#get-company)
    - [Update Company](#update-company)
    - [Delete Company](#delete-company)


### Requirement
* java 8+

### Installation  

#### Option 1
    
    1. git clone https://github.com/ashkanzng/virta-task.git
    2. cd virta-task
    3. mvn spring-boot:run
    4. open browser to http://localhost:8080

#### Option 2 
    1. Download jar file and run the app
    2. java -jar xxx.jar

### Create Company.

```JS
    Request

    POST http://localhost:8080/api/company/add
    Content-Type:application/json
    {
      "name":"Company A",
            "parentId": null,
            "stations": [
            {"name":"Station A1","latitude":40.18490133093378 , "longitude" :44.51706974521536},
            {"name":"Station A2","latitude":40.18211902611194 , "longitude" :44.521060034562424}
          ]
    }
```
```JS
    Response

    {
      "id": 1,
      "name": "Company A",
      "parentId": null,
      "stations": [
              {
                "id": 1,
                "name": "Station A1",
                "latitude": 40.18490133093378,
                "longitude": 44.51706974521536,
                "companyName": "Company A"
              },
              {
                "id": 2,
                "name": "Station A2",
                "latitude": 40.18211902611194,
                "longitude": 44.521060034562424,
                "companyName": "Company A"
              }
            ],
    "created_at": "2021-02-27T21:50:23.682+00:00",
    "updated_at": "2021-02-27T21:50:23.682+00:00"
    }
```
### Get Company.

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

```JS
    request

    POST http://localhost:8080/api/company/update/{company_id}
    Content-Type:application/json
    
```


