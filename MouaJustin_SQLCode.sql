/*
Program name : MouaJustin_Database_HW4.sql
Author       : Justin Moua
Professor    : Thomas Johnson
Course       : CS 4433/5233 | Introduction to Database Systems (CS 4433)
Due Date     : 29 Mar. 2024
Description  : N/A

Tables:
	tblUser
    tblMovie_or_TVShow
    tblActors
    tblActors_has_moviesTVShow
    tblUser_has_watchlist
	tblPlanToWatch
	tblCurrentlyWatching
	tblCompletedWatching

Inserts:
	
*/

DROP DATABASE IF EXISTS dbMywatchList;
CREATE DATABASE IF NOT EXISTS dbMyWatchList; #Creates database called dbMywatchList

USE dbMyWatchList; #Using dbMyWatchList

#=================TABLE CREATION STARTS HERE======================TABLE CREATION STARTS HERE======================TABLE CREATION STARTS HERE======================

#User table
/*
CREATE TABLE IF NOT EXISTS tblAccount (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    pswd VARCHAR(255) NOT NULL
);
*/

#User table
CREATE TABLE IF NOT EXISTS tblUser (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    firstName VARCHAR(100),
    lastName VARCHAR(100),
    DOB VARCHAR(100),
    Gender ENUM('Male', 'Female', 'Other')
);

#Media Table (Media as in movie and tv shows)
CREATE TABLE IF NOT EXISTS tblMovie_or_TVShow (
    Movie_or_TVShow_id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    genre VARCHAR(50),
    release_year INT,
    director VARCHAR(100),
    duration_minutes INT,
    release_date DATE,
    actor_count INT
);

#Actors table
CREATE TABLE IF NOT EXISTS tblActors (
    actorID INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(100),
    lastName VARCHAR(100),
    DOB DATE,
    gender ENUM('Male', 'Female', 'Other')
);

#This allows for a many to many relationship between actors and movies
CREATE TABLE IF NOT EXISTS tblActors_has_moviesTVShow(
    actorID INT,
    Movie_or_TVShow_id INT,
    PRIMARY KEY (actorID, Movie_or_TVShow_id),
    FOREIGN KEY (actorID) REFERENCES tblActors(actorID),
    FOREIGN KEY (Movie_or_TVShow_id) REFERENCES tblMovie_or_TVShow(Movie_or_TVShow_id)
);

#This checks to see if a user has an watchlist. 1:3 relationship for user. 
CREATE TABLE IF NOT EXISTS tblUser_has_watchlist (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    hasPlanToWatch BOOL DEFAULT FALSE,
    hasCurrentlyWatching BOOL DEFAULT FALSE,
    hasCompletedWatching BOOL DEFAULT FALSE,
    FOREIGN KEY (user_id) REFERENCES tblUser(id)
);

#Watchlist Table: Plan to Watch
CREATE TABLE IF NOT EXISTS tblPlanToWatch (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    Movie_or_TVShow_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES tblUser(id),
    FOREIGN KEY (Movie_or_TVShow_id) REFERENCES tblMovie_or_TVShow(Movie_or_TVShow_id)
);

#Watchlist Table: Currently Watching
CREATE TABLE IF NOT EXISTS tblCurrentlyWatching (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    Movie_or_TVShow_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES tblUser(id),
    FOREIGN KEY (Movie_or_TVShow_id) REFERENCES tblMovie_or_TVShow(Movie_or_TVShow_id)
);

#Watchlist Table: Completed Watching
CREATE TABLE IF NOT EXISTS tblCompletedWatching (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    Movie_or_TVShow_id INT NOT NULL,
    rating INT, 
    FOREIGN KEY (user_id) REFERENCES tblUser(id),
    FOREIGN KEY (Movie_or_TVShow_id) REFERENCES tblMovie_or_TVShow(Movie_or_TVShow_id)
);
#=================TABLE CREATION ENDS HERE======================TABLE CREATION ENDS HERE======================TABLE CREATION ENDS HERE======================

#=================INSERTION STARTS HERE=================INSERTION STARTS HERE=================INSERTION STARTS HERE=================
INSERT INTO tblUser (username, password, firstName, lastName, DOB, Gender) VALUES
('Example1', '1234', 'Justin', 'Moua', '2002-10-16', 'Male'),
('Example2', '1234', 'Emily', 'Hafer', '1992-07-20', 'Female'),
('Example3', '1234', 'Daniel', 'Smith', '1988-04-15', 'Male'),
('Example4', '1234', 'Sophia', 'Garcia', '1995-11-30', 'Female');


#If release date is null then it is a good indicator for the system to know that it is a tv show. 
INSERT INTO tblMovie_or_TVShow (title, genre, release_year, director, duration_minutes, release_date, actor_count) VALUES
('Rush Hour', 'Action Comedy', 1998, 'Brett Ratner', 98, '1998-09-18', 0), #Starring Jackie Chan and Chris Tucker
('Police Story', 'Action Comedy', 1985, 'Jackie Chan', 100, '1985-12-14', 0), #Starring Jackie Chan
('Men in Black', 'Action Comedy', 1997, 'Barry Sonnenfeld', 98, '1997-07-02', 0), #Starring Will Smith
('Breaking Bad', 'Crime Drama', 2008, 'Various', NULL, '2008-01-20', 0), #Starring Bryan Cranston and Anna Gynn
('Breaking Bad: Better Call Saul', 'Crime Drama', 2022, 'Various', NULL, '2022-11-03', 0); #Starring Bryan Cranston, not Anna Gunn

INSERT INTO tblActors (firstName, lastName, DOB, gender) VALUES
('Jackie', 'Chan', '1954-04-17', 'Male'),
('Will', 'Smith', '1968-09-25', 'Male'),
('Anna', 'Gunn', '1968-08-11', 'Female'),
('Bryan', 'Cranston', '1956-03-07', 'Male'),
('Chris', 'Tucker', '1971-08-31', 'Male');

INSERT INTO tblActors_has_moviesTVShow (actorID, Movie_or_TVShow_id) VALUES
(1, 1), #Shows that Jackie Chan is in Rush Hour
(1, 2), #Shows that Jackie Chan is in Police Story
(2, 3), #Will Smith is in Men in Black
(4, 4), #Bryan Cranston in Breaking Bad
(3, 4), #Anna Gunn in Breaking Bad
(4, 5), #Bryan Cranston in Breaking Bad: Better Call Saul
(5, 1); #Chris Tucker in Rush Hour

INSERT INTO tblUser_has_watchlist (user_id, hasPlanToWatch, hasCurrentlyWatching, hasCompletedWatching) VALUES
(1, TRUE, FALSE, FALSE),
(2, TRUE, FALSE, TRUE),
(3, FALSE, TRUE, FALSE),
(4, TRUE, TRUE, TRUE);

INSERT INTO tblPlanToWatch (user_id, Movie_or_TVShow_id) VALUES
(1, 2), #Sets justin moua to planning to watch police story (jackie chan stars in it)
(2, 3), #Emily Hafer plans to watch Men in Black
(4, 5); #Sophia Garcia plans to watch Breaking Bad BCS

INSERT INTO tblCurrentlyWatching (user_id, Movie_or_TVShow_id) VALUES
(3, 4), #Daniel Smith is currently watching Breaking Bad
(4, 1); #Sophia Garcia is currently watching Rush Hour

-- Insert sample data into tblCompletedWatching
INSERT INTO tblCompletedWatching (user_id, Movie_or_TVShow_id, rating) VALUES
(2, 1, 100), #Emily Hafer completed watching Rush Hour and rated it 5
(4, 3, 100); #Sophia Garcia completed watching Men in Black and rated it 4
#=================INSERTION ENDS HERE=================INSERTION ENDS HERE=================INSERTION ENDS HERE=================


#==============VIEW CREATION AND INNER JOIN STARTS HERE=============VIEW CREATION AND INNER JOIN STARTS HERE=============VIEW CREATION AND INNER JOIN STARTS HERE=============
#This creates a view that allows the user to see which actors are in what movies and what movies consist of what actors. 
CREATE VIEW vwActorMovies AS
SELECT #selects actor fname lname and movies and kind of "writes" them as variables. 
    actor.firstName AS ActorFirstName,
    actor.lastName AS ActorLastName,
    movie.title AS MovieTitle
FROM 
tblActors AS actor
#B -- Inner Join
INNER JOIN #
    tblActors_has_moviesTVShow AS actorsInMovie ON actor.actorID = actorsInMovie.actorID
INNER JOIN 
    tblMovie_or_TVShow AS movie ON actorsInMovie.Movie_or_TVShow_id = movie.Movie_or_TVShow_id;

#F -- PRINTS OUT VIEW
#SELECT * FROM vwActorMovies;

#==============VIEW CREATION AND INNER JOIN ENDS HERE=============VIEW CREATION AND INNER JOIN ENDS HERE=============VIEW CREATION AND INNER JOIN ENDS HERE=============

#==============GROUP BY QUERY STARTS HERE==============GROUP BY QUERY STARTS HERE==============GROUP BY QUERY STARTS HERE==============GROUP BY QUERY STARTS HERE==============
#A -- GROUP BY
#SELECT gender, COUNT(*) AS TotalUsers
#FROM tblUser
#GROUP BY gender;
#==============GROUP BY QUERY ENDS HERE==============GROUP BY QUERY ENDS HERE==============GROUP BY QUERY ENDS HERE==============GROUP BY QUERY ENDS HERE==============



#=============SELECT ALL QUERY STARTS HERE=============SELECT ALL QUERY STARTS HERE=============SELECT ALL QUERY STARTS HERE=============
#D - SELECT ALL OR ANY QUERY
#SELECT * FROM tblActors;

#=============SELECT ALL QUERY ENDS HERE=============SELECT ALL QUERY ENDS HERE=============SELECT ALL QUERY ENDS HERE=============

#=============SELLECT ALL FROM tblUSER=============SELLECT ALL FROM tblUSER=============SELLECT ALL FROM tblUSER=============
SELECT * FROM tblUser;
#SELECT * FROM tblMovie_or_TVShow;
#SELECT id FROM tblUser WHERE username = "Example1" AND password = 1234;
#=============SELLECT ALL FROM tblUSER=============SELLECT ALL FROM tblUSER=============SELLECT ALL FROM tblUSER=============