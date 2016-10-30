Four Row Solitaire (FRS)

Project URL: https://sourceforge.net/projects/fourrow/
Developer Email: masta08@gmail.com

License: GNU GPL v3

Release: Beta v.76

This game is a mixture between Freecell and normal Solitaire.
There are 4 columns with 5 cards each and 4 individual cells with 
cards at the start.

Similarities to FreeCell:
	The cards in the columns are always visible and there are 
		four individual cells at the top.

Similarities to Solitaire:
	There is a deck and discard pile to get cards from.
	Only Kings can be placed in empty columns.
	Cards may be removed from the Ace pile and placed back 
		into the playing field.
	Any number of cards can be moved in one move 
		(as long as they are stacked as in Solitaire)

Features unique to this game:
	The four individual cells start with cards in them.
	You may only go through the deck twice on draw one and 
		three times on draw three.
	The obvious: there are only four columns, not 7 or 8 as 
		in Solitaire and FreeCell, respectively.

Updates v.60 and v.61 made by pavlosn.
=======================================================================
Version History

Beta v.76 (This Version)
-Fixed bug introduced in v.75 for win screen

Beta v.75
-Removed blur from backgrounds
-Fixed display of stats page with large numbers
-Play again message won't display if win screen (fireworks display) is up
-Win sounds loop until stopped (by closing the win screen window)
-The timer no longer runs after the game is over
-Removed ambiguity for button clicks on two-button mice
-Added right click to view full card on columns

Beta v.70
-Added tool tips to difficulty radio buttons that show how many times you may go through the deck
-Added option to stop prompting update notice
-Removed duplicate play again message when timer is enabled
-Centered top times dialog
-Cancelling the save option no longer closes the game

Beta v.63
-Fixed timer decreasing when game is loaded

Beta v.62
-Fixed checking win condition (finished games will be detected again)

Beta v.61
-Added "Best Times" to menu
-Made layout change to time scoreboard
-Changed code for timer display
-Added timer continuity for game continues

Beta v.60
-Added top 10 time score table

Beta v.50
-Hints now include placing Kings in empty piles
-Added the remaining error checking to ensure no false options are saved
-Fixed draw count and difficulty not properly loading in all cases
-Number of cards viewable on discard pile will now be the same upon loading a game
-Added full display of 3-card draw discard pile
-Fixed undo where multiple cards were moved simultaneously

Beta v.40
-Fixed clicking draw pile while a card is selected
-Centered popup messages even when game window is moved

Beta v.30
-Added difficulty options (changes the number of times through the deck you get)
-Added separate statistics for difficulty ratings
-Added separate statistics for 1 and 3 card draw (your current stats will be counted as 1-card draw medium)
-Added support for faster deal deck clicking

Beta v.27
-Right clicking the discard pile will show the card directly below the one on top
-Double clicking a card that won't move to its ace pile will move to a cell (if available)
-The timer will start at the first click (instead of at the deal)
-Loading a saved game will keep the number of times through the deck (instead of resetting)
-If a game is saved, the card draw will be the same on loading even if the user changes it before exiting

Beta v.26
-Added highlighted card face images
-Changed clicking of cards to highlight instead of temporarily disappear
-Added double click to move card to its ace pile
-Fixed card positioning (now all stacks are actually within drawn boundaries)

Beta v.25
-Fixed load/save of games
-Added win animation and sounds for a won game (4 midis + fireworks)
-Added "Win Animation" to options
-Added "Win Sounds" to options
-Added new background (3 total)
-Changed backgrounds from .png to .jpg and compressed images

Beta v.22
-Fixed statistics viewing for mac and linux (now truly works cross platform)
-Added auto update checker

Beta v.21
-Fixed undo for single card draw
-Added save game option on exit
-Exitting without saving results in a loss
-Added icon (instead of default java icon)

Beta v.20
-Added support for undoing moves back to initial layout (instead of only a single move)

Alpha v.18
-Fixed display of three cards after undone move (now shows proper amount of cards)
-Fixed save style for statistics (now saves correctly on all platforms)

Alpha v.17
-Added saving of options and appearance for future play
-Fixed display of three cards (if player picks up a card and makes an invalid move, 
		the cards are still displayed correctly) -- Not for undoing moves, yet

Alpha v.16
-Fixed change to draw 3
-Added "Timer" to options
-Starting a new game without winning now results in a loss (a warning is displayed to player)
-Added "Reset" to statistics
-Game Window is no longer resizable
-Added notification to players for changing options (changes take affect on the next game)
-Added a preview of appearance changes
-Added "Check for Update" to menu (brings user to sourceforge project page)

Alpha v.15
-Changed default draw count back to 1
-Fixed Streaks not switching signs (a -2 streak then a win would still say -2 current streak)
-Added "Hint" to menu
-Added mneumonics and key accelerators to menu items (same as the Microsoft Solitaire counter-parts)

Alpha v.14
-Added "Statistics" to menu
-Added "Options" to menu (allows switch between "Draw 1" and "Draw 3")
-Added new FRS table top
-Fixed card disappearing if you clicked "Undo Move" while a card was selected
-Fixed cards disappearing if you tried to move multiple cards and failed (move was invalid)
-"Options" and "Change Appearance" now keep settings saved during the same instance of the game

Alpha v.13
-Added new FRS card back
-Changed image folder arrangement to be more efficient
-Added "Undo Move" feature to menu (allows undo of only one move at a time)
-Added ChangeOptions class, but have not yet implemented "Draw 3"
-Added statistics outline, but have not yet implemented it

Alpha v.12
-Added "About Game" feature to menu
-Added "Help" feature to menu
-Added "Change Appearance" feature to menu thus allowing deck 2 and future card backs to be used
-Added "Exit" feature to menu
-Added several not yet implemented items to menu

Alpha v.11
-Added "New Game" feature on menu
-Fixed kings sometimes duplicating on movement
-Fixed new game deck problem
-Removed unecessary images from folders

Alpha v.10
-Made game playable (cards can be moved)
-Added win detection
-Added support for new game after win

Pre-Alpha v.02
-Drew board and added starting cards to proper places
-Improved CardStack structure

Pre-Alpha v.01
-Created initial class structure
-Added card face images
-Added two card back images