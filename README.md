# HSTS - High School Test System 

## Structure
Pay attention to the three modules:
1. **client** - a simple client built using JavaFX and OCSF. We use EventBus (which implements the mediator pattern) in order to pass events between classes (in this case: between SimpleClient and PrimaryController.
2. **server** - a simple server built using OCSF.
3. **entities** - a shared module where all the entities of the project live.

## Running
1. Run Maven install **in the parent project**.
2. Run the server using the exec:java goal in the server module.
3. Run the client using the javafx:run goal in the client module.
4. Press the button and see what happens!


##Screenshots 
<img width="749" alt="Screenshot 2023-09-18 003224" src="https://github.com/user-attachments/assets/eeb4dd2a-6b7c-4aa1-b4eb-9ec23fd389b6">

<img width="750" alt="Screenshot 2023-09-18 003947" src="https://github.com/user-attachments/assets/8a6e3907-3567-4696-98b3-d61150e39874">

<img width="752" alt="Screenshot 2023-09-18 004432" src="https://github.com/user-attachments/assets/7e989da5-15e5-4ca9-9df5-a5151850d89b">

<img width="750" alt="Screenshot 2023-09-18 004029" src="https://github.com/user-attachments/assets/b7e6c3c6-2d82-4254-9a84-a92b781583d8">

<img width="750" alt="Screenshot 2023-09-18 004058" src="https://github.com/user-attachments/assets/16bbaf4d-a902-4332-a602-ff6d16ffec05">

<img width="750" alt="Screenshot 2023-09-18 003947" src="https://github.com/user-attachments/assets/70821b8d-f55c-43c2-99ca-fa80eb146c74">


<img width="752" alt="Screenshot 2023-09-18 004209" src="https://github.com/user-attachments/assets/1e0c80e9-36af-421e-b4ce-0a1899780318">


<img width="751" alt="Screenshot 2023-09-18 004625" src="https://github.com/user-attachments/assets/1d2a499c-9cd2-47bd-bef1-dceb8616ebaa">


