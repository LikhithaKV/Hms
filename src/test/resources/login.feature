Feature: Hms Login Functionality

@first
Scenario: To validate whether User is able to login successfully
Given User is on Login Page
When User enters valid Username "raj@gmail.com" and Password "123456"
And clicks on Login button
Then Message should be displayed saying logged in successfully "Doctor Dashboard - Hospital Management System"

@second
Scenario: to verify order is placed
Given select payment mode type "mode"
Then order should be placed

|mode|        discount|offer|
|credit card| 10%|1000 rs off|
|debit card| 5%|500 rs off|
|cod| no discount|no offer|

@third
Scenario: To validate whether User is able provide appointment
Given User is on Login Page
When User enters valid Username "raj@gmail.com" and Password "123456"
And clicks on Login button
And verify user is on Doctor dashboard  page "Doctor Dashboard - Hospital Management System"
 And clicks on Appointment
 And clicks on AppointmentList
 And verify user is on appointment page "Appointment - Hospital Management System"
 And clicks on AddAppointment
 
 And user clicks on date text box
 And user selects the required date
 And user clicks on time 
 And user selects required time
 And user clicks on selectpatient dropdown
 And user enters patient name "Likhitha"
 And user clicks on submit button
 Then user should be able to provide appointment successfully
 
 
 


