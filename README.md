
## Getting Started

The task is to implement Rest-API for the electric vehicle charging station management system.

### Requirement

* java 8+
* Apache Maven 3+

### Installation  

* Clone the repo
   ```sh
   git clone https://github.com/ashkanzng/virta-task.git
   ```

### About The Project

![Screen Shot](https://ashkan.pro/statics/img-1.jp "Screen Shot")


### CRUD Api Usage.

- Create company with stations
```JS
    request

    POST http://localhost:8080/api/company/add
    Content-Type:application/json
    Accept:application/json
    {
        "name":"Name Of Company",
        "parentId": 0, // or null
        "stations": [
            {"name":"Station 1","latitude":41.692815 , "longitude" :44.874423}
            {"name":"Station 2","latitude":41.692815 , "longitude" :44.874423}
        ]
    }
```
- Update company with stations
```JS
    request

    POST http://localhost:8080/api/company/update/{company_id}
    Content-Type:application/json
    Accept:application/json
    {
        "name":"New Name Of Company",
        "parentId": 2, // or null
        "stations": [
            {"name":"Station 3","latitude":41.692815 , "longitude" :44.874423},
            {"name":"Station 4","latitude":41.692815 , "longitude" :44.874423}
        ]
    }
```
    
    
    
### Task 1

### Task 2

###### Implement endpoint which gets all stations.

4. Enter your API in `config.js`
   ```Java
   String name = "asas";

### Usage

Use this space to show useful examples of how a project can be used. Additional screenshots, code examples and demos work well in this space. You may also link to more resources.

