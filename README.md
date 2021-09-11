# food-o-meter

Required infrastructures:
 - Java (11 or higher)
 - NodeJs (LTS version)
 - Docker
 - MongoDB
 - Active MQ Artemis (https://activemq.apache.org/components/artemis/download/)
 
Install angular libraries: npm install
 
Mongo DB windows service start: mongod --dbpath "C:\Users\ranja\Documents\Database_data\mongodb"


For MapStruct NullPointerException:

In your Intellij IDEA go to File | Settings | Build, Execution, Deployment | Compiler | user-local build process vm options
set this value : -Djps.track.ap.dependencies=false


Useful Links:
 - https://github.com/sfg-beer-works/sfg-beer-works-bom/blob/master/pom.xml
 - https://github.com/sfg-beer-works/sfg-brewery-bom/blob/master/pom.xml

Artemis setup:
 - Create broker:
	- artemis create food-o-meter-broker --user artemis --password admin --require-login