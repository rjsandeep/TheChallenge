# The gamesforthebrain challenge Game test automation
The Checkers Game - Deliver The Checkers Game project - checker

Test Automation approach 
1.	Navigate to the site
2.	Use driver.get("https://www.gamesforthebrain.com/game/checkers/")
3.	Confirm page title or key element exists
4.	Confirm site is up
5.	Assert page title or presence of board elements
6.	Make five legal moves as orange
7.	Use DOM inspection to identify clickable cells
8.	Simulate five moves using click() events
9.	Use “Make a move” as confirmation
10.	Validate that the move was processed
11.	Restart the game
12.	Click “Restart...” link
13.	Confirm board reset and all are default

The Card Game Deliver The Black Jack Game project - jack

Test Automation approach 
1.	Confirm site is up
2.	Send GET https://deckofcardsapi.com/api/deck/new/
3.	Check for 200 OK and valid JSON response
4.	Get a new deck
5.	Extract deck_id from response
6.	Shuffle the deck
7.	GET /api/deck/{deck_id}/shuffle/
8.	Deal three cards to each of two players
9.	GET /api/deck/{deck_id}/draw/?count=6
10.	Split into two hands
11.	Check for blackjack
12.	Implement scoring logic:
    13.	Face cards = 10
    14.	Ace = 11 (or 1 if needed)
    15.	Blackjack = score of 21 with two cards (adjusted for 3-card hands)
14.	Write out which player has blackjack
15.	Print result based on score comparison
