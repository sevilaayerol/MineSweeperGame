import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class MineSweeper {
	private char[][] board;
    private char[][] mineBoard;
    private int rows;
    private int cols;
    private int numMines;
    private int numUncovered;
    private boolean showMines;

    public MineSweeper(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.numMines = (rows * cols) / 4; // Mayın sayısını çeyrek boyutunda ayarlayın
        this.numUncovered = 0;
        this.board = new char[rows][cols];
        this.mineBoard = new char[rows][cols];
        this.showMines = false;
    }

    public void play() {
        initializeBoard();
        placeMines();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Mayın Tarlası Oyuna Hoşgeldiniz!");
        System.out.println();
        
        while (numUncovered < (rows * cols) - numMines) {
            if (showMines) {
                System.out.println("Mayınların Konumu");
                printMatrix(mineBoard);
                System.out.println("===========================");
            }
            
            System.out.println("Oyun Tahtası");
            printMatrix(board);
            System.out.print("Satır Giriniz: ");
            int row = scanner.nextInt();
            System.out.print("Sütun Giriniz: ");
            int col = scanner.nextInt();
            System.out.println("===========================");
            
            if (!isValidMove(row, col)) {
                System.out.println("Geçersiz bir hamle. Lütfen tekrar deneyin.");
                System.out.println("===========================");
                continue;
            }
            
            if (isMine(row, col)) {
                gameOver();
                return;
            }
            
            uncoverCell(row, col);
        }

        System.out.println("Oyunu Kazandınız!");
    }

    private void initializeBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = '-';
                mineBoard[i][j] = '-';
            }
        }
    }

    private void placeMines() {
        int count = 0;
        while (count < numMines) {
            int row = (int) (Math.random() * rows);
            int col = (int) (Math.random() * cols);
            if (mineBoard[row][col] != '*') {
                mineBoard[row][col] = '*';
                count++;
            }
        }
    }

    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols && board[row][col] == '-';
    }

    private boolean isMine(int row, int col) {
        return mineBoard[row][col] == '*';
    }

    private void uncoverCell(int row, int col) {
        int count = countAdjacentMines(row, col);
        board[row][col] = (char) (count + '0');
        numUncovered++;
    }

    private int countAdjacentMines(int row, int col) {
        int count = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < rows && j >= 0 && j < cols && mineBoard[i][j] == '*') {
                    count++;
                }
            }
        }
        return count;
    }

    private void gameOver() {
        System.out.println("Game Over!!");
        showMines = true;
        printBoard();
    }

    private void printBoard() {
        System.out.println("Mayınların Konumu");
        printMatrix(mineBoard);
        System.out.println("===========================");
        System.out.println("Oyun Tahtası");
        printMatrix(board);
        System.out.println("===========================");
    }

    private void printMatrix(char[][] matrix) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}