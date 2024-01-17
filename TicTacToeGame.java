import java.util.Scanner;

// Player class representing each player
class Player {
    private String name;
    private char symbol;

    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }
}

// Board class representing the game board
class Board {
    public static final int SIZE = 3;
    private char[][] grid;


    public Board() {
        grid = new char[SIZE][SIZE];
        // Initialize the board with empty spaces
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = ' ';
            }
        }
    }

    public void displayBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(grid[i][j]);
                if (j < SIZE - 1) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (i < SIZE - 1) {
                System.out.println("---------");
            }
        }
    }

    public boolean isCellEmpty(int row, int col) {
        return grid[row][col] == ' ';
    }

    public void markCell(int row, int col, char symbol) {
        grid[row][col] = symbol;
    }

    public boolean checkWin(char symbol) {
        // Check rows, columns, and diagonals for a win
        for (int i = 0; i < SIZE; i++) {
            if (grid[i][0] == symbol && grid[i][1] == symbol && grid[i][2] == symbol) {
                return true; // Row win
            }
            if (grid[0][i] == symbol && grid[1][i] == symbol && grid[2][i] == symbol) {
                return true; // Column win
            }
        }
        // Diagonal wins
        return (grid[0][0] == symbol && grid[1][1] == symbol && grid[2][2] == symbol) ||
                (grid[0][2] == symbol && grid[1][1] == symbol && grid[2][0] == symbol);
    }

    public boolean isBoardFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] == ' ') {
                    return false; // There is an empty cell
                }
            }
        }
        return true; // Board is full
    }
}

// TicTacToeGame class representing the game logic
public class TicTacToeGame {
    private Player player1;
    private Player player2;
    private Board board;
    private Player currentPlayer;

    public TicTacToeGame(String player1Name, String player2Name) {
        player1 = new Player(player1Name, 'X');
        player2 = new Player(player2Name, 'O');
        board = new Board();
        currentPlayer = player1; // Player 1 starts
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        int row, col;

        do {
            board.displayBoard();
            System.out.println(currentPlayer.getName() + ", enter your move (row and column): ");
            row = scanner.nextInt();
            col = scanner.nextInt();

            if (isValidMove(row, col)) {
                board.markCell(row, col, currentPlayer.getSymbol());

                if (board.checkWin(currentPlayer.getSymbol())) {
                    board.displayBoard();
                    System.out.println(currentPlayer.getName() + " wins!");
                    return;
                }

                if (board.isBoardFull()) {
                    board.displayBoard();
                    System.out.println("It's a tie!");
                    return;
                }

                // Switch to the other player
                currentPlayer = (currentPlayer == player1) ? player2 : player1;
            } else {
                System.out.println("Invalid move. Try again.");
            }
        } while (true);
    }

    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < Board.SIZE && col >= 0 && col < Board.SIZE && board.isCellEmpty(row, col);
    }

    public static void main(String[] args) {
        TicTacToeGame game = new TicTacToeGame("Player1", "Player2");
        game.playGame();
    }
}
