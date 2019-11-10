package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Application {
    public static void main(String[] args) {
        JFrame frame = new JFrame("XO");
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        GridLayout layout = new GridLayout(3,3);
        JPanel panel = new JPanel(layout);
        frame.add(panel);
        JButton [][] buttons = new JButton[3][3];
        ImageIcon icon = new ImageIcon("C:\\Users\\Mikhail\\IdeaProjects\\Java3\\src\\GUI\\img.jpg");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                final JButton b = buttons[i][j];
                buttons[i][j].addActionListener(e -> {
                    b.setText("X");
                    b.setEnabled(false);
                    if (check(buttons) == 1){
                        JFrame end = new JFrame("Game over");
                        end.setSize(200, 100);
                        JTextField text = new JTextField();
                        text.setEditable(false);
                        text.setText("Player WIN!");
                        end.add(text);
                        end.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                        end.setVisible(true);
                    }
                    if (check(buttons) == 2){

                    }
                    if (check(buttons) == -1){

                    }
                    moveAI(buttons);
                });
                int finalJ = j;
                int finalI = i;
                buttons[i][j].addKeyListener(new KeyListener() {

                    @Override
                    public void keyTyped(KeyEvent e) {
                        System.out.println("Typed!");
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                        System.out.println("Pressed!");
                        char code = e.getKeyChar();
                        if(code == 'a'){
                            b.setText("a");
                            if(finalJ < 2) buttons[finalI][finalJ +1].requestFocus();
                            else if(finalI < 2) buttons[finalI+1][0].requestFocus();
                        }
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                        System.out.println("Released!");
                    }
                });
                panel.add(buttons[i][j]);
            }
        }
        frame.setVisible(true);
    }

    private static void moveAI(JButton[][] buttons) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(buttons[i][j].getText().isEmpty()){
                    buttons[i][j].setText("O");
                    buttons[i][j].setEnabled(false);
                    i = 100;
                    break;
                }
            }
        }
        switch (check(buttons)){
            case 1:
            case 2:
            case -1:
        }
    }

    private static int check(JButton[][] buttons) {
        for (int i = 0; i < 3; i++) {
            if (!buttons[i][0].getText().isEmpty()){
                if(buttons[i][0].getText().equals(buttons[i][1].getText()) &&
                        buttons[i][1].getText().equals(buttons[i][2].getText())){
                    if(buttons[i][0].getText().equals("X")) return 1;
                    else return 2;
                }
            }
        }
        return 1;
    }
}
