-- Create table queries
/*
CREATE TABLE Main.SCOPETEXT (
                    _ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                    MESSAGE_ID INTEGER, 
                    RESPONSE_ID INTEGER,
                    NAME VARCHAR(50) NOT NULL,
                    IN_USE CHARACTER(1) NOT NULL CHECK(IN_USE IS 'Y' OR IN_USE IS 'N'),
                    FOREIGN KEY(MESSAGE_ID) REFERENCES MESSAGE(MESSAGE_ID),
                    FOREIGN KEY(RESPONSE_ID) REFERENCES RESPONSE(RESPONSE_ID)
                    );  
                    
                    

CREATE TABLE Main.MESSAGE (
                    MESSAGE_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                    TYPE VARCHAR(4) NOT NULL CHECK(TYPE IS 'TEXT'),
                    REG_EXP VARCHAR(50) NOT NULL
                  );   
                  
CREATE TABLE Main.RESPONSE (
                    RESPONSE_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                    ACTION_APP VARCHAR(6) NOT NULL CHECK(ACTION_APP IS 'CREATE' OR ACTION_APP IS 'READ' OR ACTION_APP IS 'UPDATE' OR ACTION_APP IS 'DELETE'),
                    EXTERNAL_APP VARCHAR(25) NOT NULL CHECK(EXTERNAL_APP IS 'ALARM')
                  ); 
                  
INSERT INTO MESSAGE (_ID) 
VALUES ('');     

INSERT INTO RESPONSE(ACTION_APP, EXTERNAL_APP)
VALUES('DELETE','ALARM'); 

INSERT INTO SCOPETEXT (MESSAGE_ID, RESPONSE_ID, NAME, IN_USE) VALUES (10, 1, 'ST1', 'Y');

pragma FOREIGN_KEYS
*/

INSERT INTO SCOPETEXT (MESSAGE_ID, RESPONSE_ID, NAME, IN_USE) VALUES (10, 1, 'ST1', 'Y');
