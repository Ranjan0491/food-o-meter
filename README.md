<h1 align="center">
  food-o-meter
</h1>


## 💻 Built with
- Java (11 or higher)
- NodeJs (LTS version)
- Docker
- MongoDB
- Active MQ Artemis (https://activemq.apache.org/components/artemis/download/)


## 🛠️ Installation Steps
1. Clone the repository

```bash
git clone https://github.com/Ranjan0491/food-o-meter.git
```
<br>

2. Install angular libraries 

```bash
cd <REPO_ROOT>/code/frontend/food-o-gui
npm install
```
<br>

3. Start UI

```bash
cd <REPO_ROOT>/code/frontend/food-o-gui
ng serve --open
```
<br>

4. If MongoDB is not registered as service on startup then start mongo db service.
 - Windows

```bash
mongod --dbpath <PATH_TO_STORE_MONGO_DATA> 
```
<br>

5. Artemis setup
 - Create broker
```bash
artemis create food-o-meter-broker --user <USERNAME> --password <PASSWORD> --require-login
```
<br>

6. Start Active MQ Artemis service. A batch file has been created for windows.
```bash
<REPO_ROOT>\docs\MQ
```
<br>

7. Start micro services in order
 - food-o-meter-service-discovery
 - foodometer-api-gateway
 - foodometer-item-service / foodometer-user-service
 - foodometer-order-service
<br>


## 🙇 Useful Links and Settings
 - (https://github.com/sfg-beer-works/sfg-beer-works-bom/blob/master/pom.xml)
 - (https://github.com/sfg-beer-works/sfg-brewery-bom/blob/master/pom.xml)
 - For MapStruct NullPointerException: In your Intellij IDEA go to `File | Settings | Build, Execution, Deployment | Compiler | user-local build process vm options` and set this value `-Djps.track.ap.dependencies=false`