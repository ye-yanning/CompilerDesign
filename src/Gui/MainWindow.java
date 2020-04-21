package Gui;

import javax.swing.*;
import java.awt.*;

public class MainWindow {
    public void MainFunction(){prepareGUI();}
    private void prepareGUI(){
        JFrame frame = new JFrame("C简单编译器");
        frame.setSize(300,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("C简单编译器");
        titleLabel.setBounds(100,20,80,25);
        panel.add(titleLabel);
        frame.add(panel);

        frame.setVisible(true);

    }
    public static void main(String[] args){
        MainWindow gui = new MainWindow();
        gui.MainFunction();
    }

}
