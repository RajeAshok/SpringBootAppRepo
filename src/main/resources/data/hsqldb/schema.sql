DROP TABLE Greeting IF exists;

CREATE TABLE Greeting(
id BIGINT GENERATED by default as IDENTITY(START WITH 1 ,INCREMENT BY 1 )NOT NULL,
text varchar(100) NOT NULL,
primary key id
);