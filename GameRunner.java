import java.util.Scanner;
public class GameRunner {


    public static void main(String[] args){
        Board GameBoard = new Board();       //Creates a Board object named GameBoard

        String[][] Boarditerable = GameBoard.getBoard();  // brings the 2-D array into the file under BoardIterable

        Scanner userInput = new Scanner(System.in);  //creates a scanner object named userInput to get user input
        int moveCounter = 3;            //move counter variable used in order to keep track of whos turn it is
        int colPlace = 0;               // creates the variable that tracks the column users would place a piece

        System.out.println("lets play connect four!");             //prints lets play connect four!
        System.out.println("Player 1 (x's) what is your name?");   //prints the question to user
        String player1 = userInput.nextLine();                    //recieves input and stores it in a variable
        System.out.println("Player 2 (@'s) what is your name?");
        String player2 = userInput.nextLine();

        //while someone has not won, loop
        while (!(GameBoard.findWin().substring(0,1).equals("x")) && !(GameBoard.findWin().substring(0,1).equals("@")) )
        {
            //iterates through the Board:2D-Array and prints each row
            for (int row = 0; row < Boarditerable.length; row++){
                System.out.println(GameBoard.printRow(row));
            }
            //shows the column markers
            System.out.println(GameBoard.printColGuide());

            //if the counter is odd then it is player 1's turn
            if (moveCounter % 2 == 1)
            {
                System.out.println("Which column, " + player1+"?");
                colPlace = Integer.parseInt(userInput.nextLine());
                GameBoard.placeInCol(colPlace - 1,"x");

            }
            //if the counter is even then it is player 2's turn
            if (moveCounter % 2 == 0)
            {
                System.out.println("Which column, "+player2+"?");
                colPlace = Integer.parseInt(userInput.nextLine());
                GameBoard.placeInCol(colPlace - 1, "@");
            }
            //add to the counter variable
            moveCounter += 1;

        }

        //saves the value created by find win usually 'x234'which shows who won, how they won, and the coord it was found
        String winner = GameBoard.findWin();

        //highlights the location where a user won the game
        GameBoard.colorWin(Integer.valueOf(winner.substring(2,3)), Integer.valueOf(winner.substring(3)), winner.substring(1,2));

        //prints the board after someone has won, displaying highlighted section
        for (int row = 0; row < Boarditerable.length; row++){
            System.out.println(GameBoard.printWin(row));
        }
        //displays column guide
        System.out.println(GameBoard.printColGuide());

        //if x won then print player 1 won
        if (winner.substring(0,1).equals("x"))
        {
            System.out.println("CONGRATS, "+player1+" wins!!");
        }
        //then @ win and player 2 winning is displayed
        else
        {
            System.out.println("CONGRATS, "+player2+" wins!!");
        }




    }
}