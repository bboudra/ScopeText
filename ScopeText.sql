-- Create table queries
/*
CREATE TABLE Main.SCOPETEXT (
                    SCOPETEXT_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                    MESSAGE_ID INTEGER UNIQUE, 
                    RESPONSE_ID INTEGER UNIQUE,
                    NAME VARCHAR(50) UNIQUE NOT NULL,
                    IN_USE CHARACTER(1) NOT NULL CHECK(IN_USE IS 'Y' OR IN_USE IS 'N'),
                    FOREIGN KEY(MESSAGE_ID) REFERENCES MESSAGE(MESSAGE_ID) ON DELETE CASCADE,
                    FOREIGN KEY(RESPONSE_ID) REFERENCES RESPONSE(RESPONSE_ID) ON DELETE CASCADE
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

CREATE TABLE Main.CONTACT (
                    CONTACT_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                    NAME VARCHAR(50) NOT NULL 
                  );   

CREATE TABLE Main.CONTACT_ASSOCIATION (
                    CONTACT_ASSOC_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                    SCOPETEXT_ID INTEGER,
                    CONTACT_ID INTEGER,
                    FOREIGN KEY(SCOPETEXT_ID) REFERENCES SCOPETEXT(SCOPETEXT_ID) ON DELETE CASCADE,
                    FOREIGN KEY(CONTACT_ID) REFERENCES CONTACT(CONTACT_ID) ON DELETE CASCADE
                  );      

-- GET SCOPETEXTS AND CONTACTS
SELECT ST.NAME, C.NAME, ST.IN_USE
FROM SCOPETEXT ST
LEFT JOIN CONTACT_ASSOCIATION CA ON ST.SCOPETEXT_ID = CA.SCOPETEXT_ID
LEFT JOIN CONTACT C ON C.CONTACT_ID = CA.CONTACT_ID;

PRAGMA foreign_keys = ON;


*/

SELECT ST.NAME, C.NAME, ST.IN_USE
FROM SCOPETEXT ST
LEFT JOIN CONTACT_ASSOCIATION CA ON ST.SCOPETEXT_ID = CA.SCOPETEXT_ID
LEFT JOIN CONTACT C ON C.CONTACT_ID = CA.CONTACT_ID
ORDER BY ST.SCOPETEXT_ID;








