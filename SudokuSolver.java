import java.util.Scanner;

public class SudokuSolver {

    // Size of the Sudoku grid
    private static final int SIZE = 9;

    public static void main(String[] args) {
        int[][] board = new int[SIZE][SIZE];
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the Sudoku puzzle (use 0 for empty cells):");

        // Read the Sudoku grid from the user
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = scanner.nextInt();
            }
        }

        // Solve the Sudoku puzzle
        if (solveSudoku(board)) {
            System.out.println("Solved Sudoku:");
            printBoard(board);
        } else {
            System.out.println("No solution exists.");
        }

        scanner.close();
    }

    // Method to solve the Sudoku puzzle using backtracking
    private static boolean solveSudoku(int[][] board) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                // Check if the current cell is empty
                if (board[row][col] == 0) {
                    // Try all numbers from 1 to 9
                    for (int num = 1; num <= SIZE; num++) {
                        if (isValid(board, row, col, num)) {
                            // If the number is valid, place it in the cell
                            board[row][col] = num;

                            // Recursively solve the rest of the board
                            if (solveSudoku(board)) {
                                return true;
                            }

                            // If the number leads to no solution, backtrack
                            board[row][col] = 0;
                        }
                    }
                    // If no number leads to a solution, return false
                    return false;
                }
            }
        }
        // If the board is full, return true
        return true;
    }

    // Method to check if a number can be placed in a specific cell
    private static boolean isValid(int[][] board, int row, int col, int num) {
        // Check if the number is already in the row
        for (int i = 0; i < SIZE; i++) {
            if (board[row][i] == num) {
                return false;
            }
        }

        // Check if the number is already in the column
        for (int i = 0; i < SIZE; i++) {
            if (board[i][col] == num) {
                return false;
            }
        }

        // Check if the number is already in the 3x3 subgrid
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[startRow + i][startCol + j] == num) {
                    return false;
                }
            }
        }

        // If the number is not found in the row, column, or subgrid, it's valid
        return true;
    }

    // Method to print the Sudoku board
    private static void printBoard(int[][] board) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}