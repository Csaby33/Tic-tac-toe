import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends CheckWinner implements ActionListener {
    private static String input; // choose which player starts the game
    private final JFrame frame;
    private final JPanel gamePanel;
    private final JButton gameButton;
    private final JLabel playerLabel;
    private final JLabel statusLabel;
    private final JTextField boardSizeInput;
    Dimension frameSize;
    private int clickCount;
    private JPanel boardPanel;
    private JButton[][] button;
    private String gameStatus;
    private String playerStatus;

    public Game() {
        super();
        board = new String[boardSize][boardSize];
        input = "X";

        // Initialize the frame
        frame = new JFrame("Tic Tac Toe by Csaby");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameSize = new Dimension(700, 700);
        frame.setMinimumSize(frameSize);
        frame.setMaximumSize(frameSize);
        frame.setLayout(null);

        // Initialize the game panel to put the game start button and labels
        gamePanel = new JPanel();
        boardSizeInput = new JTextField(2);
        boardSizeInput.setText(String.valueOf(boardSize));
        gameButton = new JButton("New Game");
        gamePanel.add(gameButton);
        gamePanel.setBounds(0, 0, 400, 50);
        frame.add(gamePanel);
        playerLabel = new JLabel();
        gamePanel.add(playerLabel);
        statusLabel = new JLabel("Game on");
        gamePanel.add(statusLabel);
        boardSizeInput.addActionListener(this);
        gameButton.addActionListener(this);
        boardDraw();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Game game = new Game();
        boardSize = 3;// Set the initial boardSize
    }

    private void check() {

        // Check if the current player wins
        if (checkRow(input) || checkCol(input) || checkDiag1(input) || checkDiag2(input)) {

            // Set the status label and player label accordingly
            if (input.equals("X")) {
                statusLabel.setForeground(Color.red);

            } else {
                statusLabel.setForeground(Color.blue);
            }
            playerStatus = "";
            playerLabel.setText(playerStatus);
            gameStatus = "The winner is player " + input;
            statusLabel.setText(gameStatus);

            // Disable all buttons since the game is over
            disableButtons();
        }
    }

    public void boardDraw() {

        // Remove the existing board panel
        if (boardPanel != null) {
            frame.remove(boardPanel);
        }
        gameStatus = "Game on";
        statusLabel.setText(gameStatus);
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(boardSize, boardSize));
        boardPanel.setBounds(100, 100, 300, 300);
        button = new JButton[boardSize][boardSize];
        clickCount = 0;

        // Create the buttons for the game board
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                button[i][j] = new JButton(" ");
                button[i][j].addActionListener(this);
                boardPanel.add(button[i][j]);
            }
        }
        frame.add(boardPanel);
        boardPanel.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(gameButton)) {

            // Start a new game based on the specified board size
            boardSize = Integer.parseInt(boardSizeInput.getText());
            initGame();
            return;
        }

        // Handle button clicks on the game board
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (e.getSource().equals(button[i][j])) {
                    if (button[i][j].getText().equals(" ")) {
                        board[i][j] = input;
                        clickCount++;
                        check();
                        button[i][j].setText(input);

                        // Switch the player and update labels accordingly
                        if (input.equals("X")) {
                            button[i][j].setForeground(Color.red);
                            playerLabel.setForeground(Color.blue);
                            playerStatus = "O player turn";
                            input = "O";
                        } else {
                            button[i][j].setForeground(Color.blue);
                            playerLabel.setForeground(Color.red);
                            playerStatus = "X player turn";
                            input = "X";
                        }
                        playerLabel.setText(playerStatus);

                        // Check if it's a draw
                        if (clickCount == boardSize * boardSize) {
                            gameStatus = "It's a draw";
                            statusLabel.setText(gameStatus);
                            disableButtons();
                            return;
                        }
                        break;
                    }
                }
            }
        }
    }

    private void disableButtons() {

        // Disable all buttons on the game board
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                button[i][j].setEnabled(false);
            }
        }
    }

    public void initGame() {
        clickCount = 0;
        gameStatus = "Game on";
        statusLabel.setText(gameStatus);

        // Reset the game board and enable buttons
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                button[i][j].setEnabled(true);
                button[i][j].setText(" ");
                button[i][j].removeActionListener(this);
                button[i][j].addActionListener(this);
                board[i][j] = null;
            }
        }
    }
}