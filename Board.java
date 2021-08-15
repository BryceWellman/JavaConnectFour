public class Board {
    //private 2-D Array thay represents a board of connect 4
    private String[][] Board = new String[6][7];

    //variables used to color text a certain color, called throughout class
    public static final String TEXT_RED = "\u001B[31m";
    public static final String TEXT_RESET = "\u001B[0m";
    public static final String TEXT_BLUE = "\u001B[34m";
    public static final String TEXT_YELLOW = "\u001B[33m";
    public static final String TEXT_GREEN = "\u001B[32m";

    //Board constructor that creates a 2-D array with O's displayed (empty slots)
    public Board() {
        for (int row = 0; row < Board.length; row++){
            for (int column = 0; column < Board[0].length; column++){
                Board[row][column] = "O";
            }
        }
    }

    //get method used to get iterable board in runner class
    public String[][] getBoard() {       //returns private array variable to Runner class
        return Board;
    }

    //method that prints the column guide under the printed board
    public String printColGuide(){
        return " 1 2 3 4 5 6 7 ";
    }

    //method that 'paints' pieces based on which piece they are
    public String paintColor(String val)
    {
        if (val.equals("x"))
        {
            return TEXT_RED + val + TEXT_RESET;
        }
        else if (val.equals("@"))
        {
            return TEXT_YELLOW + val + TEXT_RESET;
        }
        else
        {
            return val;
        }
    }

    //mehthod that prints each row of the 2-D array in a way that appears as a board in terminal
    public String printRow(int row){
        return (TEXT_BLUE+'|'+TEXT_RESET+paintColor(Board[row][0])+TEXT_BLUE+'|'+TEXT_RESET+paintColor(Board[row][1])+TEXT_BLUE+'|'+
                TEXT_RESET+paintColor(Board[row][2])+TEXT_BLUE+'|'+TEXT_RESET+paintColor(Board[row][3])+TEXT_BLUE+'|'+TEXT_RESET+paintColor(Board[row][4])+
                TEXT_BLUE+'|'+TEXT_RESET +paintColor(Board[row][5])+TEXT_BLUE+'|'+TEXT_RESET+paintColor(Board[row][6])+TEXT_BLUE+'|'+TEXT_RESET);
    }

    //method which highlights the win in greeen to be displayed
    public void colorWin(int rowCoord, int colCoord, String style)
    {

        //if the win was a vertical win (color 3 above the bottom most piece in the win pattern)
        if (style.equals("0"))
        {
            for (int row=0; row<Board.length; row++)
            {
                for (int col=0; col<Board[0].length;col++)
                {
                    if (row == rowCoord && col == colCoord)
                    {
                       Board[row][col] = TEXT_GREEN + Board[row][col] + TEXT_RESET;
                       Board[row+1][col] = TEXT_GREEN + Board[row+1][col] + TEXT_RESET;
                       Board[row+2][col] = TEXT_GREEN + Board[row+2][col] + TEXT_RESET;
                       Board[row+3][col] = TEXT_GREEN + Board[row+3][col] + TEXT_RESET;
                    }
                }
            }
        }
        //if the win is a diagonal downward win (color 3 diagonal down from top left piece)
        else if (style.equals("1"))
        {
            for (int row = 0; row <= Board.length - 4; row++)
            {
                for (int col =0; col < Board[0].length;col++)
                {
                    if (row == rowCoord && col == colCoord)
                    {
                        Board[row][col] = TEXT_GREEN + Board[row][col] + TEXT_RESET;
                        Board[row+1][col+1] = TEXT_GREEN + Board[row+1][col+1] + TEXT_RESET;
                        Board[row+2][col+2] = TEXT_GREEN + Board[row+2][col+2] + TEXT_RESET;
                        Board[row+3][col+3] = TEXT_GREEN + Board[row+3][col+3] + TEXT_RESET;
                    }
                }
            }
        }
        //if horizontal win (color 3 to the right and the origional win pieve in the line)
        else if (style.equals("2"))
        {
            for (int row = 0; row < Board.length; row++)
            {
                for (int col =0; col <= Board[0].length - 4;col++)
                {
                    if (row == rowCoord && col == colCoord)
                    {
                        Board[row][col] = TEXT_GREEN + Board[row][col] + TEXT_RESET;
                        Board[row][col+1] = TEXT_GREEN + Board[row][col+1] + TEXT_RESET;
                        Board[row][col+2] = TEXT_GREEN + Board[row][col+2] + TEXT_RESET;
                        Board[row][col+3] = TEXT_GREEN + Board[row][col+3] + TEXT_RESET;
                    }
                }
            }
        }
        //if diagonal upward win (color 3 up and diagonal to the right plus origional piece in the line)
        else if (style.equals("3"))
        {
            for (int row = 3; row < Board.length ; row++)
            {
                for (int col =0; col < Board[0].length - 4;col++)
                {
                    if (row == rowCoord && col == colCoord) {
                        Board[row][col] = TEXT_GREEN + Board[row][col] + TEXT_RESET;
                        Board[row - 1][col + 1] = TEXT_GREEN + Board[row - 1][col + 1] + TEXT_RESET;
                        Board[row - 2][col + 2] = TEXT_GREEN + Board[row - 2][col + 2] + TEXT_RESET;
                        Board[row - 3][col + 3] = TEXT_GREEN + Board[row - 3][col + 3] + TEXT_RESET;
                    }
                }
            }
        }

    }

    //prints the winning Board which highlights the winner by row (doesnt use paintColor method so only green shows)
    public String printWin(int row)
    {
        return (TEXT_BLUE+'|'+TEXT_RESET+Board[row][0]+TEXT_BLUE+'|'+TEXT_RESET+Board[row][1]+TEXT_BLUE+'|'+
                TEXT_RESET+Board[row][2]+TEXT_BLUE+'|'+TEXT_RESET+Board[row][3]+TEXT_BLUE+'|'+TEXT_RESET+Board[row][4]+
                TEXT_BLUE+'|'+TEXT_RESET +Board[row][5]+TEXT_BLUE+'|'+TEXT_RESET+Board[row][6]+TEXT_BLUE+'|'+TEXT_RESET);
    }

    //method which places a users desired play into the 2-D Array( the 'board')
    public void placeInCol(int column, String piece){
        int x = 0;
        int row = 5;
        while (x<1){
            if (Board[row][column].equals("O")){
                Board[row][column] = piece;
                x+=1;
            }
            row -= 1;
        }
    }

    //method which returns a String which has 4 characters which gives the information -
    //-who won, the way in which they did it(horz., vert., diagonal) and the coordinate for first piece in win
    public String findWin() {
        //Checks for vertical wins in the board
        String Winner = " ";
        for (int row = 0; row <= Board.length - 4; row++)
        {
            for (int col =0; col < Board[0].length;col++)
            {
                if (Board[row][col] != "O" && Board[row][col] == Board[row+1][col] && Board[row+1][col] == Board[row+2][col] &&
                Board[row+2][col] == Board[row+3][col])
                {
                    Winner = Board[row][col]+"0"+Integer.toString(row)+Integer.toString(col);
                }
            }
        }

        //checks for Diagonal downward win in the board
        for (int row = 0; row <= Board.length - 4; row++)
        {
            for (int col =0; col < Board[0].length;col++)
            {
                if (Board[row][col] != "O" && Board[row][col] == Board[row+1][col+1] && Board[row+1][col+1] == Board[row+2][col+2] &&
                        Board[row+2][col+2] == Board[row+3][col+3])
                {
                    Winner = Board[row][col]+"1"+Integer.toString(row)+Integer.toString(col);
                }
            }
        }

        //checks for horizontal win in the board
        for (int row = 0; row < Board.length; row++)
        {
            for (int col =0; col <= Board[0].length - 4;col++)
            {
                if (Board[row][col] != "O" && Board[row][col] == Board[row][col+1] && Board[row][col+1] == Board[row][col+2] &&
                        Board[row][col+2] == Board[row][col+3])
                {
                    Winner = Board[row][col]+"2"+Integer.toString(row)+Integer.toString(col);
                }
            }
        }

        //checks for diagonal upward win in the board
        for (int row = 3; row < Board.length ; row++)
        {
            for (int col =0; col < Board[0].length - 4;col++)
            {
                if (Board[row][col] != "O" && Board[row][col] == Board[row-1][col+1] && Board[row-1][col+1] == Board[row-2][col+2] &&
                        Board[row-2][col+2] == Board[row-3][col+3])
                {
                    Winner = Board[row][col]+"3"+Integer.toString(row)+Integer.toString(col);
                }
            }
        }
        return Winner;

    }


}