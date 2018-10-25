DROP DATABASE if exists NotesDB;
CREATE DATABASE NotesDB;

USE NotesDB;

CREATE TABLE notes (
    noteid INT NOT NULL AUTO_INCREMENT, 
    datecreated DATETIME NOT NULL, 
    contents NVARCHAR(10000)NOT NULL,  
    CONSTRAINT PK_ID PRIMARY KEY (noteid));

INSERT INTO notes(noteid, datecreated, contents)
VALUES(2018-07-02, "this is a test");
COMMIT;