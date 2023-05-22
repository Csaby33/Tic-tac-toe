import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;


public class Game extends CheckWinner implements ActionListener {



    private static String input; // choose which player starts the game
    private int clickCount;

    private JFrame frame;

    private JPanel boardPanel;
    private JButton[][] button;

    private JPanel gamePanel;
    private JButton gameButton;

    private JLabel playerLabel;

    private String gameStatus;
    private String playerStatus;
    private JLabel statusLabel;
    private JTextField boardSizeInput;

    Dimension frameSize;

    public enum GameStatus {
        NEWGAME, PLAYER_BUTTON, WINNERX, WINNER0, DRAW
    }


    public Game() {
        // Here comes the init of the frame

        super();


        input = "X";


        // init frame component
        frame = new JFrame("XO Game by Boti & Csaby");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameSize = new Dimension(700, 700);
        frame.setMinimumSize(frameSize);
        frame.setMaximumSize(frameSize);
        frame.setLayout(null);
        // init gamepanel to put the game start button and labels
        gamePanel = new JPanel();
        boardSizeInput = new JTextField(2);
        gamePanel.add(boardSizeInput);
        boardSizeInput.setText(this.boardSize + "");
        gameButton = new JButton("New Game");
        gamePanel.add(gameButton);
        gamePanel.setBounds(0,0,400,50);
        frame.add(gamePanel);


        playerLabel = new JLabel();
        gamePanel.add(playerLabel);

        statusLabel = new JLabel("Game on");
        gamePanel.add(statusLabel);


        boardSizeInput.addActionListener(this);
        gameButton.addActionListener(this);

        boardDraw();
        frame.setVisible(true);

    }

    private void check() {
        // xoTransferObj.setIsBoard(false);
        // xoTransferObj.setOtherMessage("");

        if (checkRow(input) || checkCol(input) || checkDiag1(input) || checkDiag2(input)) {

            if (input.equals("X")) {
                statusLabel.setForeground(Color.red);

            } else {
                statusLabel.setForeground(Color.blue);

            }
            playerStatus = "";
            playerLabel.setText(playerStatus);


            gameStatus = "The winner is player " + input;
            statusLabel.setText(gameStatus);


            winner = true;
            for (int i = 0; i < boardSize; i++) {
                for (int j = 0; j < boardSize; j++) {
                    button[i][j].setEnabled(false);
                }
            }
        } else if (clickCount == boardSize * boardSize) {

            gameStatus = "It's a draw";

            statusLabel.setText(gameStatus);


        }

    }

    public void boardDraw() {
        // frame.setVisible(false);
        board = new String[boardSize][boardSize];

        if (boardPanel != null) {
            frame.remove(boardPanel);
        }
        gameStatus = "Game on";
        statusLabel.setText(gameStatus);
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(boardSize, boardSize));
        boardPanel.setBounds(100,100, 300,300);
        button = new JButton[boardSize][boardSize];

        clickCount = 0;
        for (int i = 0; i < boardSize; i++) {

            for (int j = 0; j < boardSize; j++) {
                button[i][j] = new JButton(" ");
                button[i][j].addActionListener(this);

//			Pelda
//				button[i][j].addActionListener(new ActionListener() {
//
//					@Override
//					public void actionPerformed(ActionEvent e) {
//						// TODO Auto-generated method stub
//
//					}
//				});

                boardPanel.add(button[i][j]);


            }

        }
        frame.add(boardPanel);
        boardPanel.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boardSize = Integer.parseInt(boardSizeInput.getText());


        //
        if (e.getSource().equals(gameButton)) {

            // gameStatus = "Game on";
            // statusLabel.setText(gameStatus);
            initGame();
            check();

            return;
        }

        // starting checking the click to boardbutton

        for (int i = 0; i < boardSize; i++) {

            for (int j = 0; j < boardSize; j++) {

                if (e.getSource().equals(button[i][j])) {
                    board[i][j] = input;
                    check();

                    clickCount++;



                    System.out.println("boardgame ="+ board[i][j]);
                    System.out.println(Arrays.deepToString(board));
                    button[i][j].setText(input);
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

                    //button[i][j].removeActionListener(this);



                    break;

                }

            }

        }

    }

    public static void main(String[] args) {

        Game game = new Game();

        // Need to have an init boardSize
        game.boardSize = 3;

    }




    public void initGame() {

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                button[i][j].setText(" ");
                button[i][j].addActionListener(this);
                board[i][j] = null;

            }
        }
    }

}