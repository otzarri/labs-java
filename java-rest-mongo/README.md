# Java REST Mongo

This is the repository for the application `java-rest-mongo`, a sample Book Store application that allows to perform CRUD operations over the `books` collection through a REST API.

This is the branch `mongo-java-driver`, where MongoDB is implemented with the `mongo-java-driver` directly through `Document` class objects.

The only mapping in the application is made between JSON text and the `Document` class without using custom POJOs. As this is a dynamic mapping, it's possible to create database objects with any kind of structure. Anyway, is mandatory for this application to use JSON objects with a `isbn` property that is treated by MongoDB as `unique index`. Different sample objets and CRUD operations are set in the `insomnia-tests.json` file to use with the [Insomnia](https://insomnia.rest/) application.

This development aims to show how to work with MongoDB and Java, so you'll see source code that could be optimized, for example, there is some repeated code in the servlets. I didn't optimize this code for clarity, as this repository is focused on MongoDB, I wanted to left the rest of the application as simple and readable as posible.

## Compilation

Build the application:

```text
$ mvn compile war:war
```

The file `target/java-rest-mongo.war` will be created.

## Installation

Deploy `target/java-rest-mongo.war` file into a J2EE compliant application server.

This application has been tested in:
- [Apache Tomcat](https://tomcat.apache.org/) 9.0.8
- [Red Hat JBoss EAP](https://developers.redhat.com/products/eap/download/) 7.1.0

## CRUD for books

### List all books

When the application starts for the first time, no data exists yet and will return an empty response with a `404 Not found` code.

__Request__:

- Operation: `GET`
- URL: `http://localhost:8080/java-rest-mongo/api/books`

__Response__:

- Code: `404 Not found`
- Body:

```text
{}
```

### Create a book

__Request__:

- Operation: `POST`
- URL: `http://localhost:8080/java-rest-mongo/api/books`
- Body:

```text
{
  "isbn": "9780133591620",
  "year": "2014",
  "author": "Andrew Tanenbaum ",
  "title": "Modern Operating Systems",
  "tags": ["science", "mathematics"]
}
```

__Response__:

- Code: `201 Created`
- Body:

```text
{
	"_id": {
		"$oid": "5b29759c7b46936951926789"
	},
	"isbn": "9780133591620",
	"year": "2014",
	"author": "Andrew Tanenbaum ",
	"title": "Modern Operating Systems",
	"tags": [
		"science",
		"mathematics"
	]
}
```

### Get the book

__Request__:

- Operation: `GET`
- URL: `http://localhost:8080/java-rest-mongo/api/books/9780133591620`

__Response__:

- Code: `200 OK`
- Body:

```text
{
	"_id": {
		"$oid": "5b29759c7b46936951926789"
	},
	"isbn": "9780133591620",
	"year": "2014",
	"author": "Andrew Tanenbaum ",
	"title": "Modern Operating Systems",
	"tags": [
		"science",
		"mathematics"
	]
}
```

### Update book

__Request__:

- Operation: `PUT`
- URL: `http://localhost:8080/java-rest-mongo/api/books/9780133591620`
- Body:

```text
{
  "isbn": "9780133591620",
  "year": "2014",
  "author": "Andrew S. Tanenbaum ",
  "title": "Modern Operating Systems",
  "tags": ["computing", "operating-systems"]
}
```

__Response__:

- Code: `200 OK`
- Body:

```text
{
	"_id": {
		"$oid": "5b29759c7b46936951926789"
	},
	"isbn": "9780133591620",
	"year": "2014",
	"author": "Andrew S. Tanenbaum ",
	"title": "Modern Operating Systems",
	"tags": [
		"computing",
		"operating-systems"
	]
}
```

### Get the updated book

__Request__:

- Operation: `GET`
- URL: `http://localhost:8080/java-rest-mongo/api/books/9780133591620`

__Response__:

- Code: `200 OK`
- Body:

```text
{
	"_id": {
		"$oid": "5b29759c7b46936951926789"
	},
	"isbn": "9780133591620",
	"year": "2014",
	"author": "Andrew S. Tanenbaum ",
	"title": "Modern Operating Systems",
	"tags": [
		"computing",
		"operating-systems"
	]
}
```

### Delete book

__Request__:

- Operation: `DELETE`
- URL: `http://localhost:8080/java-rest-mongo/api/books/9780133591620`

__Response__:

- Code: `200 OK`
- Body:

```text
{}
```

### List all books again

__Request__:

- Operation: `GET`
- URL: `http://localhost:8080/java-rest-mongo/api/books`

__Response__:

- Code: `404 Not found`
- Body:

```text
{}
```

## Primary key

MongoDB handles a unique ID for each document, but as we are letting MongoDB to set random unique IDs to the books, we'll use an additional primary key: The `isbn` field. This field is configured as `unique index` in the MongoDB collection `books`.

The ID used in the examples above for the `GET`, `PUT` and `DELETE` methods is the `isbn` field of the `book` object.

## Error messages

If an exception happens while running one of the REST operations, the `500 Internal Server Error` HTTP status code will be returned with a JSON object in the body containing more detailed information about the problem. For example, if we try to create an object that already exists.

__Request__:

- Operation: `POST`
- URL: `http://localhost:8080/java-rest-mongo/api/books`
- Body:

```text
{
  "isbn": "9780133591620",
  "year": "2014",
  "author": "Andrew Tanenbaum ",
  "title": "Modern Operating Systems",
  "tags": ["science", "mathematics"]
}
```

__Response__:

- Code: `500 Internal Server Error`
- Body:

```text
{
	"code": "500",
	"description": "Internal Server Error",
	"message": "Error inserting document"
}
```