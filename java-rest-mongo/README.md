# Java REST Mongo

This is the code of the application `java-rest-mongo`, a sample Book Store application that allows to perform CRUD operations over the `books` collection through a REST API.

This is a classical Java web application packaged as a WAR file. I selected this technology to test different implementations of MongoDB because it's a very widely adopted Java web technology.

Each branch on this repository has a different implementation type of MongoDB:

| Branch                                                                                         | Implementation type   |
| ---------------------------------------------------------------------------------------------- | --------------------- |
| [mongodb-java-driver](https://gitlab.com/java-samples/java-rest-mongo/tree/mongo-java-driver/) | Using only the driver |
