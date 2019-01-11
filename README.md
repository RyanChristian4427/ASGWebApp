#C1717381 Client Project ReadMe

1) In order to run this, a local database named "ASG" will need to be made, along with a schema "asg". Due to the migration services, without a
schema in place, it can't start. The path of the jar is build/libs/asg-1.0.0.jar

2) Before any manual tests are done, the team originally used MailTrap, which is an inbox that collects all out going mail from the server. The credentials
for this account are "ChristianR@cardiff.ac.uk" as the username, and the password "U&P5@n9C". Alternatively, you could swap this out with any other mail
server, and the server details are located in "application.properties". Without access to the email, no new accounts could be authorized, and no accounts
can update their passwords. 

//If you choose to use the existing mail server
3) Navigate to localhost:8080/register
4) Give a first and last name, a valid email (name@gmail.com), and a password.
5) Accept terms and conditions
6) Click on "Register"
7) Wait for redirect to /login
8) Use the details you previousely provided and try to login
9) You should be denied
10) Go to https://mailtrap.io/inboxes and login
11) Go to the demo inbox
12) There will be a single email to the address you provided, click on the 'Verify Email' button
13) Login using the credentials you registered with

//else
3) Navigate to localhost:8080/login
4) Login using the credentials 'client4@asg.com' and 'pass' as the password
5) This is the first step a client would see after registering for an account, and verifying


14/6) Click on the "Register" part of the modal. This would be how the client registers for the course. Don't fill it in.
15) Instead, click on the user icon in the top right hand of the webpage, and logout
16) Login with the credentials 'admin@asg.com' and 'pass'
17) Using the left side bar, click on 'Charts'
18) Hover over the green in either chart, and you'll observe two candidates are currently in stage 'Flight Assessment'
19) Logout in the top right hand of the webpage again
20) Login with the credentials 'client@asg.com' and 'pass'.
21) This is what a client would see after they filled in the previous registration form. 
22) Click 'View Details' under 'Resources Access and Upload'
23) Upload any PDF file that you may have
24) While the end user will not see this, the file will be added to a directory "upload-dir" in the same directory as the jar is in.
25) Once again, logout.
26) Log back in with 'admin@asg.com' and 'pass'
27) Go back to charts
28) Observe that there is now a new candidate at stage "Operators Manual"