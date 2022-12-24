package UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class UserTransactionUI {
    public static void main(String[] args) {
        new UserTransactionUI();
    }

    public UserTransactionUI(){
        JFrame frame = new JFrame("POS System");
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);

        // Search area
        JPanel searchAreaPnl = new JPanel();
        searchAreaPnl.setBounds(40,20,500,640);
        searchAreaPnl.setBackground(Color.GRAY);
        searchAreaPnl.setBorder(BorderFactory.createLineBorder(Color.black,10));
        searchAreaPnl.setLayout(null);

        JLabel logoLbl = new JLabel("POS System");
        logoLbl.setFont(new Font("Calibri", Font.PLAIN, 50));
        logoLbl.setBounds(130,20,300,50);
        logoLbl.setForeground(Color.orange);

        JTextField searchBarTf = new JTextField("Search Product");
        searchBarTf.setBounds(75,100,350,20);

        String[][] dataFromDatabase = {{"ABCDE","Pepsi","small","50"},
                            {"ABCDR","Pepsi","Medium","100"},
                            {"ABCDT","Pepsi","Large","150"},
                            {"ADDSA","Tuc biscuits","Medium","30"}};
        String[] productColumn = {"Product ID","Product name","Product variant","Price"};
        DefaultTableModel productDtm = new DefaultTableModel(dataFromDatabase,productColumn);
        JTable productTable = new JTable(productDtm);
        JScrollPane productSp = new JScrollPane(productTable);
        productSp.setBounds(22,150,455,455);

        searchAreaPnl.add(logoLbl);
        searchAreaPnl.add(searchBarTf);
        searchAreaPnl.add(productSp);

        //Cart
        JPanel cartPnl = new JPanel();
        cartPnl.setBackground(Color.GRAY);
        cartPnl.setBounds(550,20,430,530);
        cartPnl.setBorder(BorderFactory.createLineBorder(Color.black,10));
        cartPnl.setLayout(null);

        JLabel cartLbl = new JLabel("Your Cart");
        cartLbl.setFont(new Font("Serif", Font.PLAIN, 30));
        cartLbl.setBounds(160,10,300,50);

        String[][] cartData = {{"ABCDE","Pepsi","small","50"},
                {"ABCDR","Pepsi","Medium","100"},
                {"ABCDT","Pepsi","Large","150"},
                {"ADDSA","Tuc biscuits","Medium","30"}};
        String[] cartColumn = {"Product ID","Product name","Product variant","Price"};
        DefaultTableModel cartDtm = new DefaultTableModel(cartData,cartColumn);
        JTable cartTable = new JTable(cartDtm);
        JScrollPane cartSp = new JScrollPane(cartTable);
        cartSp.setBounds(18,70,395,380);

        JLabel totalBillLbl = new JLabel("Total Bill : ");
        totalBillLbl.setBounds(30,480,150,20);
        totalBillLbl.setFont(new Font("Serif", Font.PLAIN, 20));
        Integer amount = 330;

        JLabel amountLbl = new JLabel(amount.toString());
        amountLbl.setBounds(130,470,100,40);
        amountLbl.setFont(new Font("Serif", Font.PLAIN, 20));
        amountLbl.setBorder(BorderFactory.createLineBorder(Color.black,2));
        amountLbl.setBackground(Color.WHITE);
        amountLbl.setOpaque(true);

        cartPnl.add(cartLbl);
        cartPnl.add(cartSp);
        cartPnl.add(totalBillLbl);
        cartPnl.add(amountLbl);

        //Bill
        JPanel billPnl = new JPanel();
        billPnl.setBackground(Color.GRAY);
        billPnl.setBounds(550,560,430,100);
        billPnl.setBorder(BorderFactory.createLineBorder(Color.black,10));
        billPnl.setLayout(new FlowLayout(FlowLayout.CENTER,25,10));

        JButton completeTransactionBtn = new JButton("Complete Transaction");
        JButton cancelTransactionBtn = new JButton("Cancel Transaction");
        JButton logOutBtn = new JButton("LogOut");


        billPnl.add(completeTransactionBtn);
        billPnl.add(cancelTransactionBtn);
        billPnl.add(logOutBtn);

        //Button config
        completeTransactionBtn.addActionListener(e->{
            frame.dispose();
            new CompleteTransactionUI(Integer.parseInt(amountLbl.getText()));
        });
        cancelTransactionBtn.addActionListener(e->{
            frame.dispose();
            new UserTransactionUI();
        });
        logOutBtn.addActionListener(e->{
            frame.dispose();
            new LoginUI();
        });
        frame.add(cartPnl);
        frame.add(searchAreaPnl);
        frame.add(billPnl);

        frame.setSize(1035,730);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
