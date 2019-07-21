CREATE TABLE Users (
    userID NUMBER,
    userName VARCHAR2(40),
    password VARCHAR2(40),
    name VARCHAR2(40),
    email VARCHAR2(60),
    type NUMBER,    
PRIMARY KEY (userID)
);

CREATE TABLE Requests(
    reqID NUMBER,
    reqAmount FLOAT, 
    description VARCHAR2(500),
    status NUMBER,
	userID NUMBER,
PRIMARY KEY (reqID),
CONSTRAINT FK_User FOREIGN KEY (userID)
REFERENCES Users(userID) 
);

CREATE TABLE Receipts(
    receiptID NUMBER,
    image BLOB,
	reqID NUMBER,
PRIMARY KEY (receiptID),
CONSTRAINT FK_Request FOREIGN KEY (reqID)
REFERENCES Requests(reqID)
)