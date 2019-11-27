import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args)
    {
        char[][] gameBoard = new char[3][3];
        initializeGameBoard(gameBoard);


        int counter=0;
        while (gameOnGoing(gameBoard))
        {
            if (counter%2==0)
                makeMove('X', gameBoard);
            else
                makeMove('O', gameBoard);
            counter++;
        }
    } // end of the main method

    /**
     * This method will display the gameBoard on the console
     */
    public static void displayGameBoard(char[][] gameBoard)
    {
        for (int row=0; row<gameBoard.length; row++) {
            for (int col = 0; col < gameBoard[0].length; col++)
                System.out.printf(" %c %s", gameBoard[row][col],(col==2)?"":"|");

            if (row!=2)
                System.out.println("\n-----------");
        }
    }

    /**
     * Initialize the gameboard will put a blank space character in every position
     * of the 2D array
     */
    public static void initializeGameBoard(char[][] gameBoard)
    {
        for (int row=0; row<gameBoard.length; row++)
        {
            for (int col=0; col<gameBoard[0].length; col++)
                gameBoard[row][col]=' ';
        }
    }

    /**
     * This method will check the gameBoard to validate if
     * there is a winner or tie.  Returns true if the game
     * can continue, false otherwise.
     */
    public static boolean gameOnGoing(char[][] gameBoard)
    {
        //check for a horiztonal win
        for (int row=0; row<gameBoard.length;row++)
        {
            if (gameBoard[row][0]==gameBoard[row][1]&& gameBoard[row][1]==gameBoard[row][2]
                    && gameBoard[row][0]!= ' ')
            {
                System.out.printf("Player %c wins%n",gameBoard[row][0]);
                displayGameBoard(gameBoard);
                return false;
            }
        }

        //check for a vertical win
        for (int col=0; col<gameBoard.length;col++)
        {
            if (gameBoard[0][col]==gameBoard[1][col]&& gameBoard[1][col]==gameBoard[2][col]
                    && gameBoard[0][col]!= ' ')
            {
                System.out.printf("Player %c wins%n",gameBoard[0][col]);
                displayGameBoard(gameBoard);
                return false;
            }
        }

        //check for right to left down win
        if (gameBoard[0][0]==gameBoard[1][1]&& gameBoard[1][1]==gameBoard[2][2]
                && gameBoard[0][0]!= ' ')
        {
            System.out.printf("Player %c wins%n",gameBoard[0][0]);
            displayGameBoard(gameBoard);
            return false;
        }

        //check for left to right down win
        if (gameBoard[0][2]==gameBoard[1][1]&& gameBoard[1][1]==gameBoard[2][0]
                && gameBoard[0][2]!= ' ')
        {
            System.out.printf("Player %c wins%n",gameBoard[0][2]);
            displayGameBoard(gameBoard);
            return false;
        }

        if (gridFull(gameBoard))
        {
            System.out.println("Stale mate");
            displayGameBoard(gameBoard);
            return false;
        }

        return true;
    }

    /**
     * This method will parse over the gameBoard and return true
     * if each position has a value that is not ' '
     */
    public static boolean gridFull(char[][] gameBoard)
    {
        for (int row=0; row<gameBoard.length; row++) {
            for (int col = 0; col < gameBoard[0].length; col++)
                if (gameBoard[row][col] == ' ')
                    return false;
        }
        return true;
    }

    /**
     * This method accepts that players' character (X or O),
     * prompts for a coordinate and if the space is available,
     * plays it into the gameboard
     */
    public static void makeMove(char player, char[][] gameBoard)
    {
        displayGameBoard(gameBoard);

        boolean invalidMove=true;

        //prompt player for coordinates
        do {
            int row = getPosition("row", player) - 1;
            int col = getPosition("column", player) - 1;

            System.out.printf("player: %c row: %d  col: %d%n", player, row, col);

            //if available make the move
            if (gameBoard[row][col] == ' ') //the space is free
            {
                gameBoard[row][col] = player;
                invalidMove=false;
            }
            else
                System.out.println("That position is taken.");

            //check if available, if not ask for coordinates again
        } while (invalidMove);
    }

    /**
     * This method will prompt the user to enter a number between 1 and 3
     * @return A validated int (1-3) is returned
     */
    public static int getPosition(String rowOrCol, char player)
    {
        int num=0;
        do {
            System.out.printf("\nPlayer %c - enter the %s (1-3): ", player, rowOrCol);
            Scanner keyboard = new Scanner(System.in);
            try {
                num = keyboard.nextInt();
            } catch (InputMismatchException e)
            {
                System.out.println("text is not value, enter an integer.");
            }
        } while (num < 1 || num > 3);
        return num;
    }
}
