import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame {
    private char currentPlayer = 'X';
    private JButton[][] buttons = new JButton[3][3];
    private JLabel label;
    private boolean gameEnded = false;

    public TicTacToe() {
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton button = new JButton("");
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!gameEnded) {
                            button.setText(String.valueOf(currentPlayer));
                            button.setEnabled(false);
                            if (checkWinner(currentPlayer)) {
                                gameEnded = true;
                                label.setText(currentPlayer + " wins!");
                            } else if (checkDraw()) {
                                gameEnded = true;
                                label.setText("Draw!");
                            } else {
                                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                                label.setText(currentPlayer + "'s turn");
                            }
                        }
                    }
                });
                buttons[i][j] = button;
                panel.add(button);
            }
        }
        add(panel, BorderLayout.CENTER);

        label = new JLabel(currentPlayer + "'s turn", SwingConstants.CENTER);
        add(label, BorderLayout.SOUTH);

        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private boolean checkWinner(char player) {
        for (int i = 0; i < 3; i++) {
            if (checkRow(i, player) || checkColumn(i, player)) {
                return true;
            }
        }
        return checkDiagonal(player);
    }

    private boolean checkRow(int row, char player) {
        for (int col = 0; col < 3; col++) {
            if (buttons[row][col].getText().isEmpty() || buttons[row][col].getText().charAt(0) != player) {
                return false;
            }
        }
        return true;
    }

    private boolean checkColumn(int col, char player) {
        for (int row = 0; row < 3; row++) {
            if (buttons[row][col].getText().isEmpty() || buttons[row][col].getText().charAt(0) != player) {
                return false;
            }
        }
        return true;
    }

    private boolean checkDiagonal(char player) {
        return (checkRow(0, player) && checkColumn(0, player)) || (checkRow(2, player) && checkColumn(2, player));
    }

    private boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].isEnabled()) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}