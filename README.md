# The gamesforthebrain challenge Game test automation
The Checkers Game - Deliver The Checkers Game project - checker

Test Automation approach 
1.	Navigate to the site
o	Use driver.get("https://www.gamesforthebrain.com/game/checkers/")
o	Confirm page title or key element exists
2.	Confirm site is up
o	Assert page title or presence of board elements
3.	Make five legal moves as orange
o	Use DOM inspection to identify clickable cells (id="cell_x_y")
o	Simulate five moves using click() events
o	Include a move that captures a blue piece
4.	Use “Make a move” as confirmation
o	Click the “Make a move” link after each move
o	Validate that the move was processed
5.	Restart the game
o	Click “Restart...” link
o	Confirm board reset and all are default

The Card Game Deliver The Black Jack Game project - jack

Test Automation approach 
1.	Confirm site is up
o	Send GET https://deckofcardsapi.com/api/deck/new/
o	Check for 200 OK and valid JSON response
2.	Get a new deck
o	Extract deck_id from response
3.	Shuffle the deck
o	GET /api/deck/{deck_id}/shuffle/
4.	Deal three cards to each of two players
o	GET /api/deck/{deck_id}/draw/?count=6
o	Split into two hands
5.	Check for blackjack
o	Implement scoring logic:
	Face cards = 10
	Ace = 11 (or 1 if needed)
	Blackjack = score of 21 with two cards (adjusted for 3-card hands)
6.	Write out which player has blackjack
o	Print result based on score comparison
