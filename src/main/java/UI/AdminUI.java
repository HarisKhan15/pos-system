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
        adminAreaPanel.setBounds(250,100,800,400);
        adminAreaPanel.setBackground(Color.GRAY);
        adminAreaPanel.setBorder(BorderFactory.createLineBorder(Color.black,10));
        adminAreaPanel.setLayout(null);

        JLabel adminlogolbl= new JLabel("POS System");
        adminlogolbl.setFont(new Font("Calibri", Font.PLAIN, 50));
        adminlogolbl.setBounds(250,30,400,50);
        adminlogolbl.setForeground(Color.orange);



        JButton transactionBtn = new JButton("Transaction");
        transactionBtn.setBounds(100,100,180,100);
        transactionBtn.addActionListener(e->{
            new TransactionUI();
            frame.dispose();
        });


        JButton dailyReportBtn = new JButton("Daily Report");
        dailyReportBtn.setBounds(300,100,200,100);
        dailyReportBtn.addActionListener(e->{
            new DailyReportUi();
            frame.dispose();
        });

        JButton categorybtn = new JButton("Add Category");
        categorybtn.setBounds(520,100,180,100);




        JButton addvariantBtn = new JButton("Add/Delete Variant");
        addvariantBtn.setBounds(100,250,180,100);
        addvariantBtn.addActionListener(e->{
            new VariantsUI();
            frame.dispose();
        });


        JButton addinventoryBtn = new JButton("Add/Delete Inventory");
        addinventoryBtn.setBounds(300,250,200,100);

        JButton addproductBtn = new JButton("Add/Delete Product");
        addproductBtn.setBounds(520,250,180,100);
        addproductBtn.addActionListener(e->{
            new ProductUI();
        });



        adminAreaPanel.add(adminlogolbl);
        adminAreaPanel.add(categorybtn);
        adminAreaPanel.add(transactionBtn);
        adminAreaPanel.add(dailyReportBtn);
        adminAreaPanel.add(addproductBtn);
        adminAreaPanel.add(addvariantBtn);
        adminAreaPanel.add(addinventoryBtn);


        frame.add(adminAreaPanel);

        frame.setSize(1500,730);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
