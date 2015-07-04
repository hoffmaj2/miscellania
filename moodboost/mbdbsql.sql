DROP DATABASE IF EXISTS MBDB;

CREATE DATABASE MBDB;

USE MBDB;


CREATE TABLE Mood
(
name varchar(255),
img varchar(255),
PRIMARY KEY (name)
);

CREATE TABLE Data
(
did int NOT NULL AUTO_INCREMENT,
name varchar(255),
tme DATETIME,
PRIMARY KEY (did),
FOREIGN KEY (name) REFERENCES Mood(name)
);

CREATE TABLE Atom
(
id varchar(255),
PRIMARY KEY (id)
);

CREATE TABLE A_M
(
id varchar(255),
name varchar(255),
PRIMARY KEY (name,id),
FOREIGN KEY (name) REFERENCES Mood(name),
FOREIGN KEY (id) REFERENCES Atom(id)
);

CREATE TABLE Content
(
link varchar(255),
rating DOUBLE(2,2),
PRIMARY KEY (link)
);


CREATE TABLE A_C
(
id varchar(255),
link varchar(255),
PRIMARY KEY (link,id),
FOREIGN KEY (link) REFERENCES Content(link),
FOREIGN KEY (id) REFERENCES Atom(id)
);


CREATE TABLE M_C
(
name varchar(255),
link varchar(255),
PRIMARY KEY (link,name),
FOREIGN KEY (link) REFERENCES Content(link),
FOREIGN KEY (name) REFERENCES Mood(name)
);


CREATE TABLE Tags
(
tag int NOT NULL AUTO_INCREMENT,
mood varchar(255),
FOREIGN KEY (mood) REFERENCES Mood(name),
PRIMARY KEY (tag)
);


CREATE TABLE Chats
(
taga int,
tagb int,
PRIMARY KEY (taga,tagb)
);


CREATE TABLE Msgs
(
pfrm int,
pto int,
msg varchar(255),
tme DATETIME,
PRIMARY KEY (pfrm,pto,msg,tme)
);


INSERT INTO Atom VALUES ("Happy");
INSERT INTO Atom VALUES ("Sad");
INSERT INTO Atom VALUES ("Angry");
INSERT INTO Atom VALUES ("Disgusted");
INSERT INTO Atom VALUES ("Afraid");
INSERT INTO Atom VALUES ("Surprised");

INSERT INTO Content VALUES ("https://upload.wikimedia.org/wikipedia/commons/8/88/Quetzalcoatl_telleriano.jpg",0.0);
INSERT INTO Content VALUES ("http://www.latinamericanstudies.org/aztecs/huitzilopochtli.jpg",0.2);
INSERT INTO Content VALUES ("https://godsilove.files.wordpress.com/2012/07/tezcatlipoca.png",0.0);
INSERT INTO Content VALUES ("https://s-media-cache-ak0.pinimg.com/736x/50/c2/88/50c288394c67b81697bf8847431183af.jpg",0.0);
INSERT INTO Content VALUES ("https://www.youtube.com/watch?v=jZhQOvvV45w&list=PL88534134DDA83D46",0.1);
INSERT INTO Content VALUES ("https://www.youtube.com/watch?v=y6Sxv-sUYtM",0.3);
INSERT INTO Content VALUES ("https://www.youtube.com/watch?v=3GjF_hbufUg",0.4);


INSERT INTO Mood VALUES ("happy","http://www.thelawofattraction.org/wp-content/uploads/2013/06/happy.jpg");
INSERT INTO Mood VALUES ("sad","http://ghcorps.org/wp-content/uploads//2013/01/Sad-Cat.jpg");
INSERT INTO Mood VALUES ("angry","https://selectresource.com/wp-content/uploads/2015/01/bigstock-portrait-of-young-angry-man-52068682.jpg");
INSERT INTO Mood VALUES ("disgusted","https://readbodylanguage.files.wordpress.com/2011/05/cf2102-f4_default.gif");
INSERT INTO Mood VALUES ("afraid","http://www.thedailymind.com/wp-content/uploads/2012/04/94_anxious.jpg");
INSERT INTO Mood VALUES ("surprised","http://i.livescience.com/images/i/000/019/601/i02/surprised-guy.jpg?1314908791");

INSERT INTO A_M SELECT Atom.id, Mood.name FROM Atom, Mood WHERE Mood.name = Atom.id;

