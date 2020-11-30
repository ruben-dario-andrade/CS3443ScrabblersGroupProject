# Project Name: Scrabble Simulator
#### Team Name: Team Scrabblers
#### Team Members: Ruben Andrade, Miguel Alejandro, Miguel Fabrigas, Frank Idrogo

## Description
Scrabble Simulator provides a fully interactive, single player Scrabble game with word helper to simulate Scrabble games. This includes a:
1. Scrabble board to play pieces on
2. Tray that holds player pieces and refreshes from a shuffled piece pile with each turn
3. Score calculator based on the words played and special tile multipliers.

The word helper enhances this by displaying all the words the user may play given their hand as well as letters they input into it. For example, you could input letters on the board and not in your hand that you may want to base your word off of.

## Known Bugs:
1. If you press undo immediately after pressing replace in the game screen, you have to press replace again in order to properly replace tiles in the player tray
2. The score isn't stored in the save file and returns to 0 when loading a save.
3. The scoring system is slightly inaccurate compared to the word score output given by the word helper.

## Planned Features Not Implemented:
1. Computer AI to play against player
2. Identifying save attributes (save name, last modified date, etc) to differentiate between saves

