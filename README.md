<h1 align="center"> Scope Text</h1> 
 
<h2 align="center"> Version 1.0</h2> 
 
## Description 
* ScopeText is an Android OS application that performs actions based on keywords, and phrases from text messages.

## Target Devices
* Currently, this application is only supported on mobile phones that support the Android OS with at least version 2.1 (API 7) installed.

## Definitions
* The following definitions are used throughout this document and the overall application to help encapsulate desired features.

  * **Message** - An entire text message.
  * **keyword** - Single word within a message.
  * **Phrase** -  Multiple adjacent keywords within a message.
 
## Functional Requirements
1. **Contacts** 
   1. Allow user to select contacts from Android OS contacts.
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
6. **Messages**
   1. Listen for desired text message based on user selected contact.
   2. Allow up to 10(Dependant upon stress tests) stored messages.

## Design Components

### ScopeText Architecture
<img src="docs/ScopeTextDFD.png" alt="Screen Hierarchy" align="middle">

<h3>Screen Hierarchy</h3>
<img src="docs/ScopeTextScreenHierarchy.png" alt="Screen Hierarchy" align="middle">


### UI Component
1. **MainActivity.java**
	1. Container for all ScopeText Fragments.
	2. Handles all life cycle events for the application.
	3. Container for the ActionBar UI View.
	4. Contains admob advertising view.  
	5. Allow swiping to switch fragments
		1. Action bar subtitle shift.
2. **ContactFragment.java**
	1. Load contacts (If any existing) from Contact.java.
	2. Store contacts as a scrollable list view.
3. **NewContactFragment.java**
	1. Form requesting name of existing phone contact.
		1. Name is sent over to Contact.java where contact is retrieved.
	2. Check boxes next to all contact phone numbers.
		1. Checked numbers are stored

### Contact Component
2. **Contact.java**
	1. Send newly created contacts to the Persistence Component to store.
	3. Load contacts (If any existing) on app start up.

## Testing Plan

## Unit test

### Integration tests

### System Tests

	
## Future Version Ideas
* Message search feature.
* Allow user to switch to contact app to create new contact if desired one doesn't exist.
* Fade bar animation under subtitle