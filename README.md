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
 
## Functional Requirements
1. **Contacts** 
   1. Allow user to select contact from Android OS contacts.
   2. Allow user to select up to 10 contacts simultaneously.
2. **External Application Interface**
   2. ScopeText must interact with one external Alarm application.
3. **User Input**
   1. Allow user to specify exact matches of a desired Message/Keyword/Phrase from an expected contact.
   2. Allow user to modify a specific alarm.
4. **Actions**
   3. ScopeText must be able to change the time on an alarm.
   4. If user wants to edit more details of the alarm, then open the external alarm application.
5. **Application Behavior**
   1. ScopeText must save the user's selections.
   2. Scopetext must load the user's selections on application start up.