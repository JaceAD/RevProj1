--Project1, table incrementers
/* 
Auto increments the user id, when one is created
*/
CREATE SEQUENCE user_it
    START WITH 1
    INCREMENT BY 1
;
CREATE OR REPLACE TRIGGER user_it
BEFORE INSERT ON Users
FOR EACH ROW
BEGIN
    IF :new.userID IS NULL
    THEN
        SELECT user_it.nextval INTO :new.userID FROM dual;
    END IF;
END;


/*
Increment Requests
*/
CREATE SEQUENCE requests_it
    START WITH 1
    INCREMENT BY 1
;
CREATE OR REPLACE TRIGGER requests_it
BEFORE INSERT ON Requests
FOR EACH ROW
BEGIN
    IF :new.reqID IS NULL
    THEN
        SELECT requests_it.nextval INTO :new.reqID FROM dual;
    END IF;
END;

/*
Increment Receipts
*/
CREATE SEQUENCE receipt_it
    START WITH 1
    INCREMENT BY 1
;
CREATE OR REPLACE TRIGGER receipt_it
BEFORE INSERT ON Receipts
FOR EACH ROW
BEGIN
    IF :new.receiptID IS NULL
    THEN
        SELECT receipt_it.nextval INTO :new.receiptID FROM dual;
    END IF;
END;