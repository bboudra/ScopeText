<h1 align="center"> Scope Text</h1> 
 
<h2 align="center"> Version 1.0</h2> 
 
## Description 
* ScopeText is an Android OS application that performs actions based on keywords, and phrases from text messages.

## Target Devices
* Currently, this application is only supported on mobile devices that support the Android OS with at least version 4.1 Jelly Bean (API 15) installed.

## Definitions
* The following definitions are used throughout this document and the overall application to help encapsulate desired features.

  * **Message** - An entire text message.
  * **keyword** - Single word within a message.
  * **Phrase** -  Multiple adjacent keywords within a message.
 
## Functional Requirements
1. **Contacts** 
   1. Allow user to select contacts from Android OS contacts.
   2. Allow user to select up to 10(Dependant upon stress tests) contacts simultaneously.
2. **External Application Interface**
   2. ScopeText must interact with one external Alarm application.
3. **User Input**
   1. Allow user to specify exact matches of a desired Message/Keyword/Phrase from a text message.
   2. Allow user to modify a specific alarm.
   3. User must be able to change the time on an alarm.
   4. If user wants to edit more details of the alarm, then open the external alarm application.
4. **Persistence**
   1. ScopeText must save the user's selections.
   2. Scopetext must load the user's selections on application start up.
6. **SMS**
   1. Listen for desired text message based on user selected contact.

## Design
<h3 align="center">Entity Relationship Diagram</h3>
![Entity Relationship Diagram](ScopeTextERD.png)
&nbsp;
<h3 align="center">Screen Hierarchy</h3>
&nbsp;
<div style="text-align:center"><img src ="ScopeTextScreenHierarchy.png"/></div>
