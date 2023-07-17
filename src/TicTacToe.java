import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TicTacToe implements ActionListener {

    JButton[] buttons;
    boolean player_turn = true;
    JFrame frame;
    JPanel buttonPanel;
    JLabel textfield = new JLabel();
    JPanel titlePanel = new JPanel();

    public static void createAndShowGUI() {
        TicTacToe game = new TicTacToe();
        game.initialize();
    }

    public void initialize() {

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setTitle("Tic Tac Toe");
        frame.setLayout(new BorderLayout());

        // Title Panel
        textfield.setBackground(new Color(175, 180, 250));
        textfield.setForeground(new Color(0, 0, 0));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setFont(new Font("Arial", Font.BOLD, 24));
        textfield.setText("TIC TAC TOE - X TURN");
        textfield.setOpaque(true);
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setPreferredSize(new Dimension(400, 50));
        titlePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        titlePanel.add(textfield, BorderLayout.CENTER);

        // Button Panel
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 3));
        buttonPanel.setPreferredSize(new Dimension(400, 300));
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        buttons = new JButton[9];
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
            buttons[i].setFont(new Font("Arial", Font.BOLD, 40));
            buttonPanel.add(buttons[i]);
        }

        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();
        if (!buttonClicked.getText().isEmpty()) {
            return;
        }
        if (player_turn) {
            textfield.setText("O TURN");
            buttonClicked.setText("X");
            buttonClicked.setBackground(Color.GREEN);
            player_turn = false;
            check();
        } else {
            textfield.setText("X TURN");
            buttonClicked.setText("O");
            buttonClicked.setBackground(Color.GREEN);
            player_turn = true;
            check();
        }
    }

    public void check() {
        String[] board = new String[9];
        for (int i = 0; i < 9; i++) {
            board[i] = buttons[i].getText();
        }

        String winner = null;
        if (board[0].equals(board[1]) && board[1].equals(board[2]) && !board[0].equals("")) {
            winner = board[0];
        } else if (board[3].equals(board[4]) && board[4].equals(board[5]) && !board[3].equals("")) {
            winner = board[3];
        } else if (board[6].equals(board[7]) && board[7].equals(board[8]) && !board[6].equals("")) {
            winner = board[6];
        } else if (board[0].equals(board[3]) && board[3].equals(board[6]) && !board[0].equals("")) {
            winner = board[0];
        } else if (board[1].equals(board[4]) && board[4].equals(board[7]) && !board[1].equals("")) {
            winner = board[1];
        } else if (board[2].equals(board[5]) && board[5].equals(board[8]) && !board[2].equals("")) {
            winner = board[2];
        } else if (board[0].equals(board[4]) && board[4].equals(board[8]) && !board[0].equals("")) {
            winner = board[0];
        } else if (board[2].equals(board[4]) && board[4].equals(board[6]) && !board[2].equals("")) {
            winner = board[2];
        }

        boolean isDraw = true;
        for (int i = 0; i < 9; i++) {
            if (board[i].equals("")) {
                isDraw = false;
                break;
            }
        }

        if (winner != null) {
            clearScreen();
            textfield.setText(winner + " WINS!");
            JOptionPane.showMessageDialog(frame, winner + " wins the game!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            resetGame();
        } else if (isDraw) {
            clearScreen();
            textfield.setText("DRAW!");
            JOptionPane.showMessageDialog(frame, "It's a draw!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            resetGame();
        }
    }

    public void clearScreen() {
        for (int i = 0; i < 9; i++) {
            buttons[i].setText("");
            buttons[i].setBackground(null);
        }
    }

    public void resetGame() {
        clearScreen();
        player_turn = true;
        textfield.setText("TIC TAC TOE - X TURN");
    }
}
