/**
 * Board Class
 */
public class Board {
    /**
     * Where the board is kept track of
     */
    private final int[][] board;
    /**
     * Height of the board (5)
     */
    public int HEIGHT;

    /**
     * Default constructor
     */
    public Board() {
        board = new int[6][7];
        HEIGHT = board.length-1;
    }

    /**
     * Determines whether a player has won or not
     * @return true if player has won, false otherwise
     */
    public boolean playerWon() {
        int player;
        for (int y = 0; y <= HEIGHT; y++) {
            for (int x = 0; x < board[y].length; x++) {
                if (board[y][x] != 0) {
                    player = board[y][x];

                    // Ways to win

                    // Vertical
                    if (y <= 2 && (board[y + 1][x] == player && board[y + 2][x] == player && board[y + 3][x] == player))
                        return true;
                    // Horizontal
                    if (x <= 3 && (board[y][x + 1] == player && board[y][x + 2] == player && board[y][x + 3] == player))
                        return true;
                    // + Diagonal
                    if (x <= 3 && y <= 2 && (board[y + 1][x + 1] == player && board[y + 2][x + 2] == player && board[y + 3][x + 3] == player))
                        return true;
                    // - Diagonal
                    if (x >= 3 && y <= 2 && (board[y + 1][x - 1] == player && board[y + 2][x - 2] == player && board[y + 3][x - 3] == player))
                        return true;
                }
            }
        } return false;
    }

    /**
     * Puts a token at the top of a column
     * @param player Player who owns the token
     * @param x x coordinate of token
     */
    public void putAtTop(int player, int x) {
        board[HEIGHT][x] = player;
    }

    /**
     * Determines whether a space below another space is empty
     * @param x x coordinate of given space
     * @param y y coordinate of given space
     * @return true if the space 1 below is empty, false otherwise
     */
    public boolean isEmptyBelow(int x, int y) {
        return y>0 && board[y-1][x] == 0;
    }

    /**
     * Moves a token down one space
     * @param player The person who owns the token
     * @param x x coordinate of space
     * @param y y coordinate of previous space
     * @return y coordinate of new space
     */
    public int moveDown(int player, int x, int y) {
        board[y][x] = 0;
        board[y-1][x] = player;
        return y-1;
    }

    /**
     * Determines if a move is legal
     * @param x x coordinate of move
     * @return true if move is legal, false otherwise
     */
    public boolean isLegalMove(int x) {
        return board[HEIGHT][x]==0;
    }

    /**
     * Overwrites toString() to print the board
     * @return String with the board output
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        for(int y=HEIGHT; y>=0; y--) {
            ret.append("\n| ");
            for(int x=0; x<board[y].length; x++) {
                switch(board[y][x]) {
                    case 1 -> ret.append("+ ");
                    case 2 -> ret.append("o ");
                    default -> ret.append("  ");
                }
            }
            ret.append("|");
        }
        ret.append("\n-----------------\n  1 2 3 4 5 6 7");
        return ret.toString();
    }
}
