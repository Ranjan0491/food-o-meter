# food-o-meter

Required infrastructures:
 - Java (11 or higher)
 - NodeJs (LTS version)
 - Docker
 - MongoDB
 - MySQL
 
Mongo DB windows service start: mongod --dbpath "C:\Users\ranja\Documents\Database_data\mongodb"


For MapStruct NullPointerException:

In your Intellij IDEA go to File | Settings | Build, Execution, Deployment | Compiler | user-local build process vm options
set this value : -Djps.track.ap.dependencies=false