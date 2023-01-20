package UI;

import javax.swing.*;
import java.awt.*;

public class AdminUI {

    public AdminUI() {
        JFrame frame = new JFrame("POS SYSTEM");



        JPanel adminAreaPanel = new JPanel();
        adminAreaPanel.setBounds(90,100,840,400);
        adminAreaPanel.setBackground(Color.GRAY);
        adminAreaPanel.setBorder(BorderFactory.createLineBorder(Color.black,10));
        adminAreaPanel.setLayout(null);

        JButton back = new JButton("Logout");
        back.setBackground(new Color(1, 176, 222));
        back.setBounds(10, 2, 100, 50);
        back.addActionListener(e -> {
            new LoginUI();
            frame.dispose();
        });


        JLabel adminlogolbl= new JLabel("POS System");
        adminlogolbl.setFont(new Font("Calibri", Font.PLAIN, 50));
        adminlogolbl.setBounds(300,30,400,50);
        adminlogolbl.setForeground(Color.orange);



        JButton transactionBtn = new JButton("Transaction");
        transactionBtn.setBounds(30,100,180,100);
        transactionBtn.addActionListener(e->{
            new TransactionUI();
            frame.dispose();
        });


        JButton dailyReportBtn = new JButton("Daily Report");
        dailyReportBtn.setBounds(230,100,180,100);
        dailyReportBtn.addActionListener(e->{
            new DailyReportUi();
            frame.dispose();
        });

        JButton addproductBtn = new JButton("Add/Delete Product");
        addproductBtn.setBounds(430,100,180,100);
        addproductBtn.addActionListener(e->{
            new AddProductUI();
            frame.dispose();
        });

        JButton AddUserBtn = new JButton("Add/Delete User");
        AddUserBtn.setBounds(630,100,180,100);
        AddUserBtn.addActionListener(e->{
            new AddUserUI();
            frame.dispose();
        });

        JButton addvariantBtn = new JButton("Add/Delete Variant");
        addvariantBtn.setBounds(135,250,180,100);
        addvariantBtn.addActionListener(e->{
            new VariantsUI();
            frame.dispose();
        });

        JButton addinventoryBtn = new JButton("Add/Delete Inventory");
        addinventoryBtn.setBounds(335,250,180,100);

        addinventoryBtn.addActionListener(e->{
            new InventoryUI();
            frame.dispose();
        });
        JButton categorybtn = new JButton("Add Category");
        categorybtn.setBounds(535,250,180,100);
        categorybtn.addActionListener(e->{
            new CategoryUI();
            frame.dispose();
        });







        adminAreaPanel.add(AddUserBtn);
        adminAreaPanel.add(adminlogolbl);
        adminAreaPanel.add(categorybtn);
        adminAreaPanel.add(transactionBtn);
        adminAreaPanel.add(dailyReportBtn);
        adminAreaPanel.add(addproductBtn);
        adminAreaPanel.add(addvariantBtn);
        adminAreaPanel.add(addinventoryBtn);


        frame.add(back);
        frame.add(adminAreaPanel);

        frame.setSize(1035,730);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
