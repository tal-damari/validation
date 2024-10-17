# Functional Programming Project - Combinator Design Pattern

This project was about creating a validation for users using *Combinator* design pattern:

* Creating a *User* class that has username, email, password and age of the user that we implement.
* Creating both *valid* and *invalid* classes - which will determined which user is a valid
user with its parameters and which user don't.
* Creating a *ValidationResult* trait, which gets the result - if it 
is valid, then there is no reason why it is valid.
but if it is not valid, then we will get the reason why it's not valid.
* Creating a *UserValidation* trait and object, that will have the functionality 
of checking if the user is valid user and which is not. This is the part where we use functional programming,
as we use few function together to get the result.
***
* *Demo* object is to create testing on project to check that what we did is correct.
***

### This code was created by Adar Azulay and Tal Damari