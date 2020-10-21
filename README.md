# BackendCodingChallenge

Intro
This project is the submission for the 10/13/2020 back-end coding challenge given by Jump Plus. The directive is provide a maintainable back-end API for the Enrollees of a health care program, including all CRUD operations.

Documentation
The bulk of the documentation for this project has been generated via Doxygen, with formatted comments being interpreted into full help files with class descriptions, method details, and see-also links. To access it, click the Xerus_Documentation shortcut in the Documentation folder, or index.html in the html folder.

Database Layout
The database is Mongodb, using Mongodb Atlas as the server host. The data is stored in two collections, labelled enrollee and dependent. Enrollees have a Set of one or more Dependents, so to avoid duplication the Java-side Set is tagged with @DBRef, which treats items as referencing the dependent collection. Since Mongodb is a NoSQL framework, unfilled fields won't be saved, but if all data is present, then the layout is as follows:

Database: centchallenge
Collection: enrollee
field	type	description
_id	string	The database generated id
name	string	The full name of the enrollee
birthdate	date	The date of birth of the enrollee
activationStatus	bool	The status of an enrollee's application, true for active, false for inactive
phoneNumber	string	The phone number of the enrollee
dependents	array of dependent references	The dependents of an enrollee
Collection: dependent
field	type	description
_id	string	The database generated id
name	string	The full name of the enrollee
birthdate	date	The date of birth of the enrollee
API usage
Get an Enrollee
GET /enrollee/get/{id}

Requests a string parameter, which is the id of an existing Enrollee. Responds with a JSON formatted Enrollee, or null if none exists.

Create an Enrollee
PUT /enrollee/add

Requests a JSON formatted Enrollee, with the minimum following data:

{
    "name":"ExampleName",
    "birthDate":"08-15-2020"
}
optional input data is as follows, with their defaults:

{
    "activationStatus":false,
    "phoneNumber":"",
    "dependents": []
}
Responds with the Enrollee added to the database, containing the generated id.

Update an Enrollee
PUT /enrollee/update

Requests a JSON formatted Enrollee, with the minimum following data:

{
    "id":"ExampleId123",
    "name":"ExampleName",
    "birthDate":"08-15-2020"
}
optional input data is as follows, with their defaults:

{
    "activationStatus":false,
    "phoneNumber":"",
    "dependents": []
}
Responds with the true if the update was successful, false otherwise.

Delete an Enrollee
DELETE /enrollee/delete/{id}

Requests a string parameter, which is the id of an existing Enrollee. Responds with true if the deletion was successful, false otherwise. The Enrollee's Dependents will also be deleted.

Add a Dependent to an Enrollee
PUT /enrollee/add/{enId}/dependent/{depId}

Requests two string parameters, which are the id of an existing Enrollee, and the id of an existing Dependent. Responds with true if the addition was successful, false otherwise.

Remove a Dependent from an Enrollee
DELETE /enrollee/delete/{enId}/dependent/{depId}

Requests two string parameters, which are the id of an existing Enrollee, and the id of one of their Dependents. Responds with true if the removal was successful, false otherwise.

Get a Dependent
GET /dependent/get/{id}

Requests a string parameter, which is the id of an existing Dependent. Responds with a JSON formatted Dependent, or null if none exists.

Create a Dependent
PUT /dependent/add

Requests a JSON formatted Dependent, with the following data:

{
    "name":"SomeName",
    "birthDate":"08-15-2020"
}
Responds with the Dependent added to the database, containing the generated id.

Update a Dependent
PUT /dependent/update

Requests a JSON formatted Dependent, with the following data:

{
    "id":"ExampleId123",
    "name":"ExampleName",
    "birthDate":"08-15-2020"
}
Responds with true if the Dependent was successfully updated, false otherwise.

Delete a Dependent
DELETE /dependent/delete/{id}

Requests a string parameter, which is the id of an existing Dependent. Responds with true if the deletion was successful, false otherwise. Be warned that Enrollees with this Dependent will not be updated accordingly- but Enrollee.dependents will be stripped of null values when acquired from the database, and updating with this will remove the dependent, effectively removing the issue by hiding it.
