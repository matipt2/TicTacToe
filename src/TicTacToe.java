import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

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
        frame.setSize(800, 800);
        frame.setLayout(new BorderLayout());
        textfield.setBackground(new Color(175, 180, 250));
        textfield.setForeground(new Color(0, 0, 0));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setFont(new Font("Arial", Font.BOLD, 40));
        textfield.setText("TIC TAC TOE   ||  X TURN");
        textfield.setOpaque(true);
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setPreferredSize(new Dimension(800, 100));
        titlePanel.add(textfield, BorderLayout.CENTER);
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 3));
        buttonPanel.setPreferredSize(new Dimension(800, 700));
        buttons = new JButton[9];
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
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
        if(!buttonClicked.getText().isEmpty()){
            return;
        }
        if (player_turn) {
            textfield.setText("O TURN");
            Font newFont = new Font(Font.SERIF, Font.PLAIN,  64);
            buttonClicked.setFont(newFont);
            buttonClicked.setText("X");
            buttonClicked.setBackground(Color.GREEN);
            player_turn = false;
            check();
        } else {
            textfield.setText("X TURN");
            Font newFont = new Font(Font.SERIF, Font.PLAIN,  64);
            buttonClicked.setFont(newFont);
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
            if (winner.equals("X")) {
                clearScreen();
                JLabel label = new JLabel();
                buttonPanel.add(label);
                buttonPanel.setLayout(null);
                textfield.setText("X WIN CONGRATS!");
                ImageIcon iconLogo = new ImageIcon("images/image.jpg");
                label.setIcon(new ImageIcon(iconLogo.getImage().getScaledInstance(300,300, Image.SCALE_DEFAULT)));
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setVerticalAlignment(SwingConstants.TOP);
                label.setBounds(250, 250, 300, 300);


            } else {
                clearScreen();
                JLabel label = new JLabel();
                buttonPanel.add(label);
                buttonPanel.setLayout(null);
                textfield.setText("O WIN CONGRATS!");
                ImageIcon iconLogo = new ImageIcon("images/image1.jpg");
                label.setIcon(new ImageIcon(iconLogo.getImage().getScaledInstance(300,300, Image.SCALE_DEFAULT)));
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setVerticalAlignment(SwingConstants.TOP);
                label.setBounds(250, 250, 300, 300);

            }
        } else if (isDraw) {
            clearScreen();
            JLabel label = new JLabel();
            clearScreen();
            buttonPanel.add(label);
            buttonPanel.setLayout(null);
            textfield.setText("DRAW!");
            ImageIcon iconLogo = new ImageIcon("images/image2.jpg");
            label.setIcon(new ImageIcon(iconLogo.getImage().getScaledInstance(300,300, Image.SCALE_DEFAULT)));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.TOP);
            label.setBounds(250, 250, 300, 300);
        }
    }
    public void clearScreen() {
        buttonPanel.removeAll();
        frame.revalidate();
        frame.repaint();
    }
}

