import java.util.Objects;

public class CheckWinner {
    protected static int boardSize = 3;
    protected String[][] board;

    // Check if any row has all elements equal to the specified input
    boolean checkRow(String input) {
        int counter;
        for (int i = 0; i < boardSize; i++) {
            counter = 0; // Counter reset after each row is checked
            for (int j = 0; j < boardSize; j++) {
                if (Objects.equals(board[i][j], input)) {
                    counter++;
                }
                if (counter == boardSize) {
                    System.out.println("counter row=" + counter);
                    return true;
                }
            }
        }
        return false;
    }

    // Check if any column has all elements equal to the specified input
    protected boolean checkCol(String input) {
        int counter;
        for (int i = 0; i < boardSize; i++) {
            counter = 0; // Counter reset after each column is checked
            for (int j = 0; j < boardSize; j++) {
                if (Objects.equals(board[j][i], input)) {
                    counter++;
                }
                if (counter == boardSize) {
                    return true;
                }
            }
        }
        return false;
    }

    // Check if the main diagonal has all elements equal to the specified input
    protected boolean checkDiag1(String input) {
        int counter = 0;
        for (int i = 0; i < boardSize; i++) {
            if (Objects.equals(board[i][i], input)) {
                counter++;
            }
            if (counter == boardSize) {
                return true;
            }
        }
        return false;
    }

    // Check if the secondary diagonal has all elements equal to the specified input
    protected boolean checkDiag2(String input) {
        int counter = 0;
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (i + j == boardSize - 1) {
                    if (Objects.equals(board[i][j], input)) {
                        counter++;
                    }
                }
                if (counter == boardSize) {
                    return true;
                }
            }
        }
        return false;
    }
}