# Scope Text 
 
## Version 1.0
 
## Description 
* ScopeText is an Android OS application that performs actions based on keywords, and phrases from text messages.

## Target Devices
Currently, this application is only supported on mobile devices that support the Android OS with at least version 4.1 Jelly Bean (API 15) installed.

## Definitions
The following definitions are used throughout this document and the overall application to help encapsulate desired features.
  * **Message**
    - An entire text message.
  * **keyword**
    - Single word within a message.
  * **Phrase**
    - Multiple adjacent keywords within a message.
 
## Features
1. **Specify expected contact.** 
   1. Get the desired contact.
   2. Need persistence to keep track of expected contacts.
   3. Current contact limit is pending until stress tests are implemented.
2. **Specify expected Message/Keyword/Phrase within a text message.**
   1. Allow user to specify exact matches of a desired Message/Keyword/Phrase from an expected contact.
3. **Alarm App Interaction**
   1. Allow user to specify when they would like to change their alarm.
   2. Implement communication with one alarm application.
