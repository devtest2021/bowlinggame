A simple spring boot application which gives the possibility to submit and get scores for a bowling game. 

## Prerequisite

	POSTMAN API Application

## Installation
	
	Clone the repository git@github.com:devtest2021/bowlinggame.git 
	Run ‘mvn clean package’ command under the root directory in any IDE.

## Start the application
	After build is completed, run ‘java -jar target\Bowling_Scores-0.0.1-SNAPSHOT.jar’ command to start the application.

## Test the application
    * Import the BowlingGame.postman_collection.json file in POSTMAN application. 
	* Test data for step 2 are available in the project location - DemoApp/TestData/
	
	Base url: http://localhost:8080/bowling
	Enter the base url in POSTMAN and select the appropriate HTTP Method as per the below calls and add the routes at the end.
	
	Step 1: Create New PlayerId
		Register new Player - use the route '/newPlayer'
		A Guid is created which is the unique id for the player. This is referrred as playerId in this document.
		N number of games can be played with the same playerId.
		
	Step 2: Submit score for the player created
		The score should be submitted with the count of the balls rolled down at each roll.
		To submit the score - use the POST method with the route /{playerId}/score
		Once submitted, a success message is displayed. 
		In case of failure, step 1 should be followed to generate a new playerId to with a new test case. -This means that the application failed for some particular test case.
		
	Step 3: Get the total score for the player
		Once the scores for all the frames are submitted. The next goal is to get the total and the frame by frame score.
		To get the score - use the GET method with the route /{playerId}/score
		The score for each frame and the total score is displayed.
		
		
	
