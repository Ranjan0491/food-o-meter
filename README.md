# food-o-meter
This is a food order and delivery system built with **Spring Boot** and **Angular** with **Mongo** serving the backend database. Even though there is no actual payment system implemented, it is a great example for microservice implementation along with asynchronous messaging and **Spring State Machine** transitions.

The whole architecture could be supported by containerized platforms and scaled based on need.


## 💻 Built with
- Java (11 or higher)
- NodeJs (LTS version)
- Docker
- MongoDB
- Active MQ Artemis (https://activemq.apache.org/components/artemis/download/)


## 🛠️ Installation Steps
###### 1. Clone the repository

```bash
git clone https://github.com/Ranjan0491/food-o-meter.git
```


###### 2. Install angular libraries 

```bash
cd <REPO_ROOT>/code/frontend/food-o-gui
npm install
```


###### 3. Start UI

```bash
cd <REPO_ROOT>/code/frontend/food-o-gui
ng serve --open
```


###### 4. If MongoDB is not registered as service on startup then start mongo db service.

```bash
mongod --dbpath <PATH_TO_STORE_MONGO_DATA> 
```


###### 5. Artemis setup

 - Create broker: 
```bash
artemis create food-o-meter-broker --user <USERNAME> --password <PASSWORD> --require-login
```


###### 6. Start Active MQ Artemis service. A batch file has been created for windows.
```bash
<REPO_ROOT>\docs\MQ
```

###### 7. Start micro services in order
    - food-o-meter-service-discovery
    - foodometer-api-gateway
    - foodometer-item-service / foodometer-user-service
    - foodometer-order-service


## 🙇 Useful Links and Settings
 - https://github.com/sfg-beer-works/sfg-beer-works-bom/blob/master/pom.xml
 - https://github.com/sfg-beer-works/sfg-brewery-bom/blob/master/pom.xml
 - https://github.com/spring-cloud/spring-cloud-gateway/issues/840
 - For MapStruct NullPointerException: In your Intellij IDEA go to `File | Settings | Build, Execution, Deployment | Compiler | user-local build process vm options` and set this value `-Djps.track.ap.dependencies=false`
