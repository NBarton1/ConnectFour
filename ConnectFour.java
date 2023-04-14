import java.util.Scanner;

/**
 * Connect Four Game
 * @author nbarton1
 */
public class ConnectFour {
    public static void main(String[] args) {

        // Instantiating variables
        Scanner input = new Scanner(System.in);
        Board game = new Board();
        boolean doneTurn;
        boolean playingGame = true;
        int HEIGHT = game.HEIGHT;

        // While playing the game
        while(playingGame) {

            // Player 1's turn
            doneTurn = false;
            while(!doneTurn) {

                System.out.println(game);
                System.out.println("Player 1 (+)");

                // Picking spot
                int p1spot = pickSpot(input)-1;
                int y = HEIGHT;

                // Doing the physics of the move
                if(game.isLegalMove(p1spot)) {
                    game.putAtTop(1, p1spot);
                    while (game.isEmptyBelow(p1spot, y)) {
                        y = game.moveDown(1, p1spot, y);
                    }
                    doneTurn = true;
                }
            }

            // Player 2's turn if Player 1 did not win on their turn
            if(!game.playerWon()) {

                // Player 2's turn
                doneTurn = false;
                while(!doneTurn) {

                    System.out.println(game);
                    System.out.println("Player 2 (o)");

                    // Picking spot
                    int p2spot = pickSpot(input)-1;
                    int y = HEIGHT;

                    // Doing physics of the move
                    if(game.isLegalMove(p2spot)) {
                        game.putAtTop(2, p2spot);
                        while (game.isEmptyBelow(p2spot, y)) {
                            y = game.moveDown(2, p2spot, y);
                        }
                        doneTurn = true;
                    }
                }
            }

            // End game condition
            if(game.playerWon()) {
                playingGame = false;
            }
        }
        System.out.println(game);
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
}
