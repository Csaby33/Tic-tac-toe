import java.util.Objects;

public class CheckWinner {
    protected static int	boardSize =3;
    protected String board[][];
    protected boolean winner; // false means it's a draw, true means we have a winner

    boolean checkRow(String input) {
        int counter;
        for (int i = 0; i < boardSize; i++) {
            counter = 0; // counter reset after each row is checked
            for (int j = 0; j < boardSize; j++) {
                System.out.println(input);
                System.out.println("board check =" +board[i][j]);
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

    protected boolean checkCol(String input) {
        int counter;
        for (int i = 0; i < boardSize; i++) {
            counter = 0; // counter reset after each column is checked
           // System.out.println("column counter reseted");
            for (int j = 0; j < boardSize; j++) {

                if (Objects.equals(board[j][i], input)) {
                    counter++;
                }
                if (counter == boardSize) {
                    return true;
                }
            }
           // System.out.println("counter col=" + counter);
        }
        return false;
    }

    protected boolean checkDiag1(String input) {
        int counter = 0;
        for (int i = 0; i < boardSize; i++) {
            if (Objects.equals(board[i][i], input)) {
                counter++;
            }
            if (counter == boardSize) {
                return true;
            }
         //   System.out.println("counter diag1=" + counter);
        }
        return false;
    }

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
          //  System.out.println("counter diag2=" + counter);
        }
        return false;
    }
}