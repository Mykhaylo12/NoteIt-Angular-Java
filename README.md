# NoteIt (SpringBoot + Angular)
- Table of Contents
* [Project purpose](#purpose)
* [Project structure](#structure)
* [For developer](#developer-start)
* [Authors](#authors)

## <a name="purpose"></a>Project purpose

It implements typical functions for notebook (task tracker) application. 

Available functions for all users (user and admin roles): 
* login (jwt authorization token)
* logout

Available functions for user (user role): 
* create notebook (inside notebook you can create note)
* change notebook
* delete notebook
* create note (task)
* update note 
* delete note
* find note by text

Available functions for an admin only:
* get all users 
* delete user
<hr>

## <a name="structure"></a>Project Structure
* Java 11
* SpringBoot
* Angular 6
* Maven 4.0.0
* Slf4j
* MySQL 8.0

<hr>

## <a name="developer-start"></a>For developer
Open separately NoteIt-api (back-end part) project in your IDE and then noteit-ng-app (front-end part).
Create in MySQL database noteit schema and establish connection with your username and password
(also write username and password in application properties file in NoteIt-api).
Set in application properties file (NoteIt-api) your mail settings for feedback functionality.

In back-end part implemented db seeder which creates 3 users in database when you start application.
1) email: userOnly@gmail.com , password: 12345 , roles: user;
2) email: adminOnly@gmail.com , password: 12345 , roles: admin;
3) email: adminAndUser@gmail.com , password: 12345 , roles: admin, user;
 
<hr>

## <a name="authors"></a>Authors
[Mykhailo Kramar](https://github.com/Mykhaylo12?tab=repositories)

