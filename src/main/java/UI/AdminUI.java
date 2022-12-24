package UI;

import javax.swing.*;
import java.awt.*;

public class AdminUI {
    public static void main(String[] args) {
        new  AdminUI();
    }
    public AdminUI() {
        JFrame frame = new JFrame("POS SYSTEM");



        JPanel adminAreaPanel = new JPanel();
        adminAreaPanel.setBounds(100,100,800,400);
        adminAreaPanel.setBackground(Color.GRAY);
        adminAreaPanel.setBorder(BorderFactory.createLineBorder(Color.black,10));
        adminAreaPanel.setLayout(null);

        JLabel adminlogolbl= new JLabel("POS System");
        adminlogolbl.setFont(new Font("Calibri", Font.PLAIN, 50));
        adminlogolbl.setBounds(250,30,400,50);
        adminlogolbl.setForeground(Color.orange);



        JButton transactionBtn = new JButton("Transaction");
        transactionBtn.setBounds(200,100,150,100);


        JButton dailyReportBtn = new JButton("Daily Report");
        dailyReportBtn.setBounds(450,100,150,100);


        JButton addvariantBtn = new JButton("Add/Delete Variant");
        addvariantBtn.setBounds(100,250,180,100);


        JButton addinventoryBtn = new JButton("Add/Delete Inventory");
        addinventoryBtn.setBounds(300,250,200,100);

        JButton addproductBtn = new JButton("Add/Delete Product");
        addproductBtn.setBounds(520,250,180,100);

        adminAreaPanel.add(adminlogolbl);
        adminAreaPanel.add(transactionBtn);
        adminAreaPanel.add(dailyReportBtn);
        adminAreaPanel.add(addproductBtn);
        adminAreaPanel.add(addvariantBtn);
        adminAreaPanel.add(addinventoryBtn);


        frame.add(adminAreaPanel);

        frame.setSize(1035,730);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
