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
6. **Messages**
   1. Listen for desired text message based on user selected contact.
   2. Allow up to 10(Dependant upon stress tests) stored messages per contact.

## Design Components

### ScopeText Architecture
<img src="docs/ScopeTextDFD.png" alt="Screen Hierarchy" align="middle">

### UI Component
1. **MainActivity.java**
	1. Container for all ScopeText Fragments.
	2. Handles all life cycle events for the application.
	3. Container for the ActionBar UI View.
3. **MainMenuFragment.java**
	1. + button on actionbar switches fragment to the NewContactFragment.
	2. Inherits from a ListFragment to support Views that navigate to the ContactFragment.
	3. Send newly created contacts to the Persistence Component.
	4. Load contacts (If any existing) on app start up.
<h3>Screen Hierarchy</h3>
<img src="docs/ScopeTextScreenHierarchy.png" alt="Screen Hierarchy" align="middle">
	
## Future Version Ideas
* Message search feature.
