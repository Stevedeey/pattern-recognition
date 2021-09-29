# Pattern Recognition

This is a simple REST API for [WellD](https://www.welld.ch/).

Given a set of N feature points in the plane, determine every line that contains N or more of the points, and
return all points involved. You should also expose a REST API that will allow the caller to:

- Add a point to the space
- Get all points in the space
- Get all line segments passing through at least N points. Note that a line segment should be a set of
points.
- Remove all points from the space

### Technologies
- Java
- Springboot (v2.5.5)
- Swagger

### Requirements

You need the following to build and run the application:

- [JDK 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Maven 3.8.1](https://maven.apache.org) (Optional as code already contains maven wrapper)

<br/>

## How to run 
#### step 1 - clone project with from [here](https://github.com/firsthus/pattern-recognition)

```
    git clone https://github.com/firsthus/pattern-recognition.git
```


#### step 2 - move into the project directory
```
    cd pattern-recognition/
```

#### step 3 - run the program
```
    mvnw spring-boot:run
```

#### step 4 - use endpoints as described below.

<br/>


- ### Add a point to the space

```http request
POST /point with body { "x": ..., "y": ... }
```
#### Demonstration



RequestBody
```json

        { 
          "x": 5.0, 
          "y": 1.0 
        }
```
Output (JSON)
```json
        
        {
          "message": "Point has been added to space",
          "point": {
                      "x": 5.0,
                      "y": 1.0
                    }
        }
```


- ### Get all points in the space

```http request
GET /space
```
#### Demonstration



Let's prepare the scene by adding some points to the space before making a request to get all points
```json lines
    POST /point{ "x": 1.0, "y": 1.0 }
    POST /point{ "x": 2.0, "y": 1.0 }
    POST /point{ "x": -4.0, "y": 2.0 }
    POST /point{ "x": 1.0, "y": 2.0 }
    POST /point{ "x": 4.0, "y": 2.0 }
    
```
Now, we can make a GET request
```http request
GET /space
```

Output (JSON)

```json

    [
          {
              "x": 1.0,
              "y": 1.0
          },
          {
              "x": 2.0,
              "y": 1.0
          },
          {
              "x": -4.0,
              "y": 2.0
          },
          {
              "x": 1.0,
              "y": 2.0
          },
          {
              "x": 4.0,
              "y": 2.0
          }
    ]
```


- ### Get all line segments passing through at least N points.

```http request
GET /lines/{N}
```
#### Demonstration

Let's prepare the scene by adding some points to the space before making a request to get lines
```json lines
    POST /point{ "x": 1.0, "y": 1.0 }
    POST /point{ "x": 2.0, "y": 1.0 }
    POST /point{ "x": -4.0, "y": 2.0 }
    POST /point{ "x": 1.0, "y": 2.0 }
    POST /point{ "x": 4.0, "y": 2.0 }
```
Now, we can make a GET request
```http request
GET /lines/3
```

Output (JSON)

```json

    [
        [
            {
                "x": -4.0,
                "y": 2.0
            },
            {
                "x": 1.0,
                "y": 2.0
            },
            {
                "x": 4.0,
                "y": 2.0
            }
        ]
    ]
```

There is also an endpoint to get all straight lines in the space
```http request
GET /lines
```

Output (JSON)

```json
    
    [
          [
                {
                  "x": 1.0,
                  "y": 1.0
                },
                {
                  "x": 1.0,
                  "y": 2.0
                }
          ],
          [
                {
                  "x": 2.0,
                  "y": 1.0
                },
                {
                  "x": 1.0,
                  "y": 2.0
                }
          ],
          [
                {
                  "x": 1.0,
                  "y": 1.0
                },
                {
                  "x": 2.0,
                  "y": 1.0
                }
          ],
          [
                {
                  "x": 2.0,
                  "y": 1.0
                },
                {
                  "x": -4.0,
                  "y": 2.0
                }
          ],
          [
                {
                  "x": -4.0,
                  "y": 2.0
                },
                {
                  "x": 1.0,
                  "y": 2.0
                },
                {
                  "x": 4.0,
                  "y": 2.0
                }
          ],
          [
                {
                  "x": 2.0,
                  "y": 1.0
                },
                {
                  "x": 4.0,
                  "y": 2.0
                }
          ],
          [
                {
                  "x": 1.0,
                  "y": 1.0
                },
                {
                  "x": 4.0,
                  "y": 2.0
                }
          ],
          [
                {
                  "x": 1.0,
                  "y": 1.0
                },
                {
                  "x": -4.0,
                  "y": 2.0
                }
          ]
    ]
```


- ### Remove all points from the space

```http request
DELETE /space
```

#### Output
```json lines
  All points on space has been cleared
```

## How to run test
#### Be sure to be in the project directory, then use this command:

```shell
   ./mvnw test
```
<br/>



>##### NOTE: Program by default will run on `port 8080`.

> While running the program, you can check out the swagger documentation at [http://localhost:8080/swagger-ui/#/](http://localhost:8080/swagger-ui/#/)


## Contact

>Festus Audu - [audufirsthus@gmail.com](mailto:audufirsthus@gmail.com)

>Project Repo Link: [https://github.com/firsthus/pattern-recognition](https://github.com/firsthus/pattern-recognition)

<br>

<h3 align="center">
      DREAM. &nbsp; &nbsp; DO. &nbsp;&nbsp; DEVELOP.
    <br />
</h3>
