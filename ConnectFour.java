import java.util.Scanner;

/**
 * Connect Four Game
 * @author nbarton1
 */
public class ConnectFour {
    public static void main(String[] args) {

        // Instantiating variables
        Board game = new Board();
        boolean playingGame = true;

        // Game in progress
        while(playingGame) {

            // Turns
            turn(1, game);
            if(game.playerWon()==0)
                turn(2, game);

            // Checking for a win
            if(game.playerWon()!=0 || game.boardIsFull())
                playingGame = false;
        }
        // Printing board at the end
        System.out.println(game);
        if(game.playerWon()!=0)
            System.out.println("\nPlayer " + game.playerWon() + " wins!");
        else System.out.println("\nIt's a tie!");
    }

    /**
     * Gets a user chosen spot (1-7 as int)
     * @param input Scanner object
     * @return int from user input
     */
    public static int pickSpot(Scanner input) {
        int spot;
        do {
            System.out.print("Pick a spot: ");
            while(!input.hasNextInt()) {
                System.out.print("Pick a spot: ");
                input.nextLine();
            }
            spot = input.nextInt();
        } while(!(spot>=1 && spot<=7));
        input.nextLine();
        return spot;
    }

    public static void turn(int player, Board game) {

        // Instantiating variables
        Scanner input = new Scanner(System.in);
        boolean done = false;

        // Taking turn
        while(!done) {

            // Printing instructions
            System.out.println(game);
            String p = "(x)";
            if(player==2)
                p = "(o)";
            System.out.println("Player " + p);

            // Playing a move
            int spot = pickSpot(input)-1;
            if(game.isLegalMove(spot)) {
                game.putAtTop(player, spot);
                game.moveDown(player, spot);
                done = true;
            }
        }
    }
}
