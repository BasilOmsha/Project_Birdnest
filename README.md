# Project_Birdnest
## Purpose
This is a web application fulfilling the requirments asked for the summer 2023 trainee position by Reaktor, which are:<br>

- only query this information for the drones violating the NDZ.
- Persist the pilot information for 10 minutes since their drone was last seen by the equipment
- Display the closest confirmed distance to the nest
- Contain the pilot name, email address and phone number
- Immediately show the information from the last 10 minutes to anyone opening the application
- Not require the user to manually refresh the view to see up-to-date information

More info found on this link -> https://assignments.reaktor.com/birdnest/

## Solution
This soution was created using Google App Engine Standard Java Project and was developed on Eclipse IDE. Runs on localhost:8080.

The main files can be found under: <br>
- /Birdnest/src/main/java
- /Birdnest/src/main/webapp

### Backedend tech
- Servlets
- Javascript

### Frontend tech
- Jsp
- Javascript
- css

## What was achieved? 📝
- Scrap the data from the urls given ✔
- Add the data to the databas ✔
- Keep only the drones that are violating ✔
- Compare the distances each pilot has and keep the closest distance recorded for each pilot ✔
- Read all the remainig data from the db, parse it into JSON and send it to the client where they can see first the older data ✔
- Delete 10+ minutes old data by comparing the drones timestamps to the current timestamp ✔
- The table updates automatically with new data without the need for refreshing the page ✔

## In progress ⏲
- Do more testing
- <strike> bug fix </strike>
- Improve the interface

## Images

![UI1](https://user-images.githubusercontent.com/107993017/211383219-26f4ec1d-cf06-4cce-815f-5c4790a00dc5.png)
![UI2](https://user-images.githubusercontent.com/107993017/211383230-ace9a6b2-4b18-4756-9560-5313994dbf10.png)
![drones_databaseTable](https://user-images.githubusercontent.com/107993017/211171785-2a976ad6-05ac-4a36-ae93-a31044945422.png)


