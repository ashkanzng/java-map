
## Getting Started

The task is to implement Rest-API for the electric vehicle charging station management system.

### Requirement

* java 8+
* Apache Maven 3+
  ```sh
  mvn --version
  java --version
  ```

### Installation

* Clone the repo
   ```sh
   git clone https://github.com/ashkanzng/virta-task.git
   ```
<!-- ABOUT THE PROJECT -->
## About The Project

[![Product Name Screen Shot][product-screenshot]](https://ashkan.pro/statics/img-1.jpg)

### Task 1

###### CRUD Api support stations and companies.

- Creating company with stations 
```JS
    request

    POST http://localhost:8080/api/company/add
    Content-Type:application/json
    Accept:application/json
   {
    'name':'Company A',
    'parentId': null,
    'stations': [
        {'name':'Station C','latitude':40.182873 , 'longitude' :44.499833}
        ]
    }
```


### Task 2

###### Implement endpoint which gets all stations.

4. Enter your API in `config.js`
   ```Java
   String name = "asas";