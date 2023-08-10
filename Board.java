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
     * @return player that won, 0 if game is not over
     */
    public int playerWon() {
        int player;
        for (int i = 0; i <= HEIGHT; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != 0) {
                    player = board[i][j];

                    // Ways to win

                    // Vertical
                    if (i <= 2 && (board[i + 1][j] == player && board[i + 2][j] == player && board[i + 3][j] == player))
                        return player;
                    // Horizontal
                    if (j <= 3 && (board[i][j + 1] == player && board[i][j + 2] == player && board[i][j + 3] == player))
                        return player;
                    // + Diagonal
                    if (j <= 3 && i <= 2 && (board[i + 1][j + 1] == player && board[i + 2][j + 2] == player && board[i + 3][j + 3] == player))
                        return player;
                    // - Diagonal
                    if (j >= 3 && i <= 2 && (board[i + 1][j - 1] == player && board[i + 2][j - 2] == player && board[i + 3][j - 3] == player))
                        return player;
                }
            }
        } return 0;
    }

    public boolean boardIsFull() {
        for(int[] row : board) {
            for(int n : row) {
                if(n==0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Puts a token at the top of a column
     * @param player Player who owns the token
     * @param j x coordinate of token
     */
    public void putAtTop(int player, int j) {
        board[HEIGHT][j] = player;
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
     */
    public void moveDown(int player, int x) {
        int y = HEIGHT;
        while (isEmptyBelow(x, y)) {
            board[y][x] = 0;
            y-=1;
            board[y][x] = player;
        }
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
        for(int i=HEIGHT; i>=0; i--) {
            ret.append("\n| ");
            for(int j=0; j<board[i].length; j++) {
                switch(board[i][j]) {
                    case 1 -> ret.append("x ");
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
