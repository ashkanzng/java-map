## Task Description

The task is to implement Rest-API for the electric vehicle charging station management system.

## Crossref REST API

- Quick Start
  - [Requirement](#requirement)
  - [Installation](#install)
  
- Company API
    - [Create Company](#create-a-new-company)
    - [Get Company (include children stations)](#get-company)
    - [Get list of Companies](#Get-list-of-Companies)
    - [Find a Company (singel company)](#Find-a-Company)
    - [Update Company](#update-company)
    - [Delete Company](#delete-company)
- Station API
    - [Add Station](#add-station)
    - [Get list of Stations](#Get-list-of-stations)
    - [Find all stations Within the radius of n kilometers by distance](#find-all-stations-close-to-given-point))
    - [Update Station](#update-station)
    - [Delete Station](#delete-station)

### Requirement
    java 8+

### Install

    Option 1

        1. git clone https://github.com/ashkanzng/virta-task.git
        2. cd virta-task
        3. mvn spring-boot:run
        4. open browser to http://localhost:8080

    Option 2 

        1. unzip devlon-task.zip => db.mv.db  devlon-Task.jar  
        2. java -jar devlon-Task.jar 


### Demo of home page http://localhost:8080:
![Map of stations](https://github.com/ashkanzng/virta-task/blob/master/src/main/resources/static/imgs/mapbox.PNG "Map of stations")


### Create a new company

#### Request

`POST /api/company/add`

    curl --request POST --url http://localhost:8080/api/company/add --header 'Content-Type: application/json' --data '{"name":"Company A","parentId": null,"stations": [{"name":"Station A1","latitude":40.18490133093378 , "longitude" :44.51706974521536},{"name":"Station A2","latitude":40.18211902611194 , "longitude" :44.521060034562424}]}'

#### Response
```JS
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


### Get Company 
Including all the children stations in the tree, for the given *company_id* .

#### Request
`GET /api/company/get/{company_id}`

    curl --request GET --url http://localhost:8080/api/company/get/1 --header 'Content-Type: application/json'

#### Response

```JS
{
  "name": "Company A",
  "child": [
    {
      "name": "Company B",
      "child": [
        {
          "name": "Company C",
          "child": [],
          "stations": [
            {
              "id": 6,
              "name": "Station C2",
              "latitude": 40.176787750697,
              "longitude": 44.506651828385,
              "companyName": "Company C"
            },
            {
              "id": 5,
              "name": "Station C1",
              "latitude": 40.179553515829,
              "longitude": 44.50602072514,
              "companyName": "Company C"
            }
          ]
        }
      ],
      "stations": [
        {
          "id": 4,
          "name": "Station B2",
          "latitude": 40.182009857438,
          "longitude": 44.506056447965,
          "companyName": "Company B"
        },
        {
          "id": 3,
          "name": "Station B1",
          "latitude": 40.185102901848,
          "longitude": 44.509402485928,
          "companyName": "Company B"
        }
      ]
    }
  ],
  "stations": [
    {
      "id": 6,
      "name": "Station C2",
      "latitude": 40.176787750697,
      "longitude": 44.506651828385,
      "companyName": "Company C"
    },
    {
      "id": 2,
      "name": "Station A2",
      "latitude": 40.182119026112,
      "longitude": 44.521060034562,
      "companyName": "Company A"
    },
    {
      "id": 1,
      "name": "Station A1",
      "latitude": 40.184901330934,
      "longitude": 44.517069745215,
      "companyName": "Company A"
    },
    {
      "id": 4,
      "name": "Station B2",
      "latitude": 40.182009857438,
      "longitude": 44.506056447965,
      "companyName": "Company B"
    },
    {
      "id": 5,
      "name": "Station C1",
      "latitude": 40.179553515829,
      "longitude": 44.50602072514,
      "companyName": "Company C"
    },
    {
      "id": 3,
      "name": "Station B1",
      "latitude": 40.185102901848,
      "longitude": 44.509402485928,
      "companyName": "Company B"
    }
  ]
}
```


### Get list of Companies

#### Request
`GET /api/company/all`
  
    curl --request GET --url http://localhost:8080/api/company/all --header 'Content-Type: application/json' 


### Find a Company

#### Request
`GET /api/company/{company_id}`
  
    curl --request GET --url http://localhost:8080/api/company/1 --header 'Content-Type: application/json' 


### Update Company

#### Request

  `POST /api/company/update/{company_id}`

      curl --request POST --url http://localhost:8080/api/company/update/3 --header 'Content-Type: application/json' --data '{"name": "Company C ","parentId":2}'

#### Request 
  **you can also add or remove stations on update request**

`POST /api/company/update/{company_id}`

      curl --request POST --url http://localhost:8080/api/company/update/3 --header 'Content-Type: application/json' --data '{"name": "Company C","parentId":2,"stations": [{"name": "Station C1","latitude": 40.179553515829,"longitude": 44.50602072514},{"name": "Station C2","latitude": 40.176787750697,"longitude": 44.506651828385},{"name": "Station C23","latitude": 40.173548750850095,"longitude": 44.50640176860866}]}'


### Delete Company

#### Request
  
`DELETE /api/company/delete/{company_id}`

    curl --request DELETE   --url http://localhost:8080/api/company/delete/3


### Add Station

#### Request
`POST /api/station/add/{company_id}`

    curl --request POST --url http://localhost:8080/api/station/add/2 --header 'Content-Type: application/json' --data '{"name":"Station B2","latitude": 40.173548750850095,"longitude":44.50900953485069}'

#### Response
```JS
{
  "id": 161,
  "name": "Station D 6",
  "latitude": 40.17949170829966,
  "longitude": 44.51391698378367,
  "companyName": "D"
}
```

### Get list of stations

#### Request
`GET /api/station/all`


### Find all stations close to given point
#### Request

`GET /api/station/find?lat={lat}&lon={lon}&rad={rad}`

    curl --request GET --url 'http://localhost:8080/api/station/find?lat=40.18341857687639&lon=44.513950912512826&rad=2' --header 'Content-Type: application/x-www-form-urlencoded'
#### Response
```JS
  [
    {
      "id": 1,
      "name": "Station A1",
      "distance": 0.312
    },
    {
      "id": 2,
      "name": "Station A2",
      "distance": 0.621
    },
    {
      "id": 4,
      "name": "Station B2",
      "distance": 0.689
    },
    {
      "id": 40,
      "name": "Station C1",
      "distance": 0.799
    },
    {
      "id": 42,
      "name": "Station C2",
      "distance": 0.963
    },
    {
      "id": 65,
      "name": "Station B3",
      "distance": 1.178
    },
    {
      "id": 41,
      "name": "Station C23",
      "distance": 1.271
    },
    {
      "id": 67,
      "name": "Station B4",
      "distance": 1.383
    }
  ]
```


### Update Station

#### Request
`POST /api/station/update/{station_id}`

      curl --request POST --url http://localhost:8080/api/station/update/67 --header 'Content-Type: application/json' --data '{"name": "Gas Station","latitude": 40.17949171,"longitude": 44.81391699}'

### Delete Station

#### Request
`DELETE /api/station/delete/{station_id}`

    curl --request DELETE --url http://localhost:8080/api/station/delete/3
