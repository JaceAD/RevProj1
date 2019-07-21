

--************************************************************************
--      INSERTS
--************************************************************************


--------------------------------------
--      Populate Employees
--------------------------------------

--------------------------------------
--      Populate Managers
--------------------------------------

--------------------------------------
--      Populate Requests
--------------------------------------


--************************************************************************
--      SELECTS
--************************************************************************


--------------------------------------
--      Validate User Exists
--------------------------------------
SELECT (password) FROM Users WHERE userID=?; --(1, userID)

--------------------------------------
--      Employees Home Page
--------------------------------------

-----------------------
--View Pending Requests
-----------------------
SELECT * FROM Requests WHERE status=?; --(1, RequestStatusType.Pending)

-----------------------
--View Resolved Requests
-----------------------
SELECT * FROM Requests WHERE status=?; --(1, RequestStatusType.Resolved)

-----------------------
--View Account
-----------------------
SELECT * FROM Users WHERE userID=?; --(1, userID)


--------------------------------------
--      Manager's Home Page
--------------------------------------

-----------------------
--View Pending Requests
--All Employees
-----------------------
SELECT * FROM Requests WHERE status=?; --(1, RequestStatusType.Pending)

-----------------------
--View Resolved Requests
--All Employees
-----------------------
SELECT * FROM Requests WHERE status=?; --(1, RequestStatusType.Resolved)

-----------------------
--View Employee's Requests
--Disregard Status
-----------------------
SELECT * FROM Requests WHERE userID=?; --(1, userID)

-----------------------
--View Employees
--Disregard Status
-----------------------
SELECT (name, email) FROM Users;



--************************************************************************
--      UPDATES
--************************************************************************
--------------------------------------
--      Employee Home Page
--------------------------------------
UPDATE Employee SET
Username=?, --(1, username) String
Password=?, --(2, password) String --maybe require that both fields be applied and match before the submit button allows continuing.
name=?, --(3, name) String
email=? --(4, email) String
WHERE UserID=?; --(5, userID) int




--************************************************************************
--      DELETES
--************************************************************************
--------------------------------------
--      Remove Employees
--------------------------------------

--------------------------------------
--      Remove Managers
--------------------------------------

--------------------------------------
--      Remove Requests
--------------------------------------


------------------------------
--      Connecting
------------------------------
--Java: getPassword(int userDriversID)
--SQL: 
SELECT password FROM MyAdmin.Users WHERE userDriversID = 10000000;

------------------------------
--      Adding Cars
------------------------------
INSERT INTO CARS (1,1,100,1,1,null,null);


------------------------------
--      Viewing Cars
------------------------------
--Java: getUserLot()
--DAO: getAllCars()
SELECT * FROM MyAdmin.Cars;


--Java: viewDealershipLot()
--DAO: getDealerCars()
SELECT * FROM MyAdmin.Cars WHERE ownerID = 10000000;



------------------------------
--      Sequences
------------------------------
/* 
Auto increments the Customer's UserDriversID, when one is created
*/
CREATE SEQUENCE customerIT
    START WITH 1
    INCREMENT BY 1
;
CREATE OR REPLACE TRIGGER customerIT
BEFORE INSERT ON Users
FOR EACH ROW
BEGIN
    IF :new.UserDriversID IS NULL
    THEN
        SELECT customerIT.nextval INTO :new.UserDriversID FROM dual;
    END IF;
END;

/* 
Auto increments the car id, when one is created
*/
CREATE SEQUENCE car_it
    START WITH 1
    INCREMENT BY 1
;
CREATE OR REPLACE TRIGGER car_it
BEFORE INSERT ON Cars
FOR EACH ROW
BEGIN
    IF :new.CarID IS NULL
    THEN
        SELECT car_it.nextval INTO :new.CarID FROM dual;
    END IF;
END;

/* 
Auto increments the car id, when one is created
*/
CREATE SEQUENCE contract_it
    START WITH 1
    INCREMENT BY 1
;
CREATE OR REPLACE TRIGGER contract_it
BEFORE INSERT ON Contracts
FOR EACH ROW
BEGIN
    IF :new.ContractID IS NULL
    THEN
        SELECT contract_it.nextval INTO :new.ContractID FROM dual;
    END IF;
END;


-------------------------------
--      Truncate Tables
-------------------------------
TRUNCATE TABLE MyAdmin.PAYMENTS;
TRUNCATE TABLE MyAdmin.CONTRACTS;
TRUNCATE TABLE MyAdmin.CARREGES;
TRUNCATE TABLE MyAdmin.CARS;
TRUNCATE TABLE MyAdmin.OFFERS;
TRUNCATE TABLE MyAdmin.USERS;
