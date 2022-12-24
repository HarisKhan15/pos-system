package UI;

import javax.swing.*;
import java.awt.*;

public class TransactionUI {
    public static void main(String[] args) {
        new TransactionUI();
    }
    public TransactionUI(){
        JFrame frame = new JFrame("POS System");

        JPanel searchAreaPnl = new JPanel();
        searchAreaPnl.setBounds(40,20,500,640);
        searchAreaPnl.setBackground(Color.GRAY);
        searchAreaPnl.setBorder(BorderFactory.createLineBorder(Color.black,10));
        searchAreaPnl.setLayout(null);

        frame.add(searchAreaPnl);
        frame.setSize(1035,730);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


    }
}
