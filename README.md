# Special_Card_Game

### How to Run
1. Open a terminal and change directory to src folder
2. Compile the Main.java by typing javac Main.java
3. Run it by typing java Main.java 
4. If the program works as the image below, then it is working well

### Demo image

![](images/card%20game.JPG)

### Rules to determine who has better cards:
- J, Q, K are regarded as special cards.
- Rule 1: The one with more special cards wins.
- Rule 2: If both have the same number of special cards, add the face values of the other card(s) and take the remainder after dividing the sum by 10. The one with a bigger remainder wins. (Note: Ace = 1).
- Rule 3: The dealer wins if both rule 1 and rule 2 cannot distinguish the winner.
