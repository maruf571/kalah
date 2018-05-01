# Kalah Game
This is a java web application that runs the game of 6-stone Kalah. 
For the general rules of the game please refer to Wikipedia: https://en.wikipedia.org/wiki/Kalah.
The default implementation of this app for 6-stone. 

## About the game
* Each of the two players has six pits in front of him/her. 
* To the right of the six pits, each player has a larger pit, his Kalah or house.
* At the start of the game, six stones are put In each pit.
* The player who begins picks up all the stones in any of their own pits, and sows the stones on to the right, one in each of the following pits, including his own Kalah. 
* No stones are put in the opponent's' Kalah. If the players last stone lands in his own Kalah, he gets another turn. This can be repeated any number of times before it's the other player's turn.

### Limitation
* Two player have to play on the same screen. 

 
### Prerequisites
* [Java 1.8](http://www.oracle.com/technetwork/java/javase/downloads/index.html)  - Programming language
* [Maven 3.5.0](https://maven.apache.org/download.cgi) - Build tool

### Installing
A step by step series of examples that tell you have to get a development env running
Say what the step will be

```
$ git clone <repository_url>
```

And cd to the project root

```
$ cd kalah
```

### Running the server 

Running the test

```
$ mvn test
```

Running the web app in dev mode
```
$ mvn spring-boot:run
```

 Running the web app in prod mode
```
$ mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

### Running the App
* Api documentation browse [http://localhost:7070/swagger-ui.html](http://localhost:7070/swagger-ui.html)
* To Run application browse  [http://localhost:7070](http://localhost:7070)



## Built With
* [Spring boot 2.0.X](https://projects.spring.io/spring-boot/) -Backed Framework
* [Maven](https://maven.apache.org/) - Dependency Management
* [AngularJs](https://angularjs.org/) - Frontend Framework

## Authors

* **Maruf Hassan**

## License

This project is licensed under the MIT License

