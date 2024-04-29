//Tic Tac Toe is fun!
//Albert Floyd Villanueva
//ITCC 11.1 B
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToeGame implements ActionListener {
    private JFrame frame;
    private JPanel panel;
    private JButton[] buttons = new JButton[9];
    private JLabel turnLabel;
    private boolean xTurn = true;

    public TicTacToeGame() {
        frame = new JFrame("Tic-Tac-Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        turnLabel = new JLabel("X's Turn", SwingConstants.CENTER);
        turnLabel.setFont(new Font("Arial", Font.BOLD, 16));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 40));
            buttons[i].addActionListener(this);
            panel.add(buttons[i]);
        }

        frame.add(turnLabel, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.setSize(800, 800);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.getContentPane().setBackground(Color.MAGENTA);
        frame.setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if (!button.getText().isEmpty()) return; // If cell already occupied

        if (xTurn) {
            button.setText("X");
        } else {
            button.setText("O");
        }
        button.setEnabled(false);
        xTurn = !xTurn;

        updateTurnLabel();
        checkForWinner();
    }

    private void updateTurnLabel() {
        turnLabel.setText(xTurn ? "X's Turn" : "O's Turn");
    }

    public void checkForWinner() {
        // Check rows
        for (int i = 0; i < 9; i += 3) {
            if (buttons[i].getText().equals(buttons[i+1].getText()) && buttons[i].getText().equals(buttons[i+2].getText()) && !buttons[i].isEnabled()) {
                announceWinner(buttons[i].getText());
                return;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (buttons[i].getText().equals(buttons[i+3].getText()) && buttons[i].getText().equals(buttons[i+6].getText()) && !buttons[i].isEnabled()) {
                announceWinner(buttons[i].getText());
                return;
            }
        }

        // Check diagonals
        if (buttons[0].getText().equals(buttons[4].getText()) && buttons[0].getText().equals(buttons[8].getText()) && !buttons[0].isEnabled()) {
            announceWinner(buttons[0].getText());
            return;
        }
        if (buttons[2].getText().equals(buttons[4].getText()) && buttons[2].getText().equals(buttons[6].getText()) && !buttons[2].isEnabled()) {
            announceWinner(buttons[2].getText());
            return;
        }

        // Check for tie
        boolean tie = true;
        for (int i = 0; i < 9; i++) {
            if (buttons[i].isEnabled()) {
                tie = false;
                break;
            }
        }
        if (tie) {
            JOptionPane.showMessageDialog(frame, "Tie game!");
            Object[] options = {"New Game", "Exit"};
            int n = JOptionPane.showOptionDialog(frame,
                            "Would you like to start a new game?",
                            "Reset Game",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[0]);
            if (n == JOptionPane.YES_OPTION) {
            resetGame();
            } else if (n == JOptionPane.NO_OPTION) {
            System.exit(0);
            }
        }
    }

    private void announceWinner(String winner) {
        JOptionPane.showMessageDialog(frame, winner + " wins!");
        Object[] options = {"New Game", "Exit"};
        int n = JOptionPane.showOptionDialog(frame,
                        "Would you like to start a new game?",
                        "Reset Game",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);
        if (n == JOptionPane.YES_OPTION) {
        resetGame();
        } else if (n == JOptionPane.NO_OPTION) {
        System.exit(0);
        }
    }

    public void resetGame() {
        for (int i = 0; i < 9; i++) {
            buttons[i].setText("");
            buttons[i].setEnabled(true);
        }
        xTurn = true;
        updateTurnLabel();
    }

    public static void main(String[] args) {
        new TicTacToeGame();
    }
}
