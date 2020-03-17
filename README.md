# Android-Application-Development
The challenge: Create a basic search activity that will take a text input from the user and return a list of related games.

You must design and implement a basic android app. The app will work in the following way:
The user opens the app to find the search screen.
The user types a search query into a text field.
As the user types their query, a list of related games is continuously shown beneath the text field.
If the user chooses to scroll down the list, and then gets to the end of the results, an additional request will be sent asking for more games starting from the given index.

You are responsible for:
Creating the input and display for the search results.
Writing a search algorithm that always returns the most appropriate results.
Adding pagination capabilities for when the user scrolls to the bottom of the games list.
Error messages, in case things go wrong.
Making sure the code is well commented, well written, and bug free.

You may assume that the given data file “games.json” contains all the games that you would need to search through.

Here is an example game object from the “games.json” file:
{
"genre":"GAME",				// The game’s genre.
"imgURL":"www.someimageurl.com",	// The game’s image url.
"subgenre":"Puzzle",			// The game’s sub-genre.
"title":"Some Game Title",	   	// The title of the game.
"pid":"com.mistplay.game",		// The game’s package id.
"rating":"4.6",				// The game’s rating.
"rCount":"170865"				// How many ratings the game has.
},
