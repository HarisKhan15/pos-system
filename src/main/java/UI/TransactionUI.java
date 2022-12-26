package UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TransactionUI {

    public TransactionUI(){
        JFrame frame = new JFrame("POS System");
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);

        JPanel transactionPnl = new JPanel();
        transactionPnl.setBounds(40,20,940,640);
        transactionPnl.setBackground(Color.GRAY);
        transactionPnl.setBorder(BorderFactory.createLineBorder(Color.black,10,true));
        transactionPnl.setLayout(null);

        JButton backBtn = new JButton("back");
        backBtn.setBounds(35,40,80,25);


        JLabel logoLbl = new JLabel("Transactions");
        logoLbl.setFont(new Font("Calibri", Font.PLAIN, 50));
        logoLbl.setBounds(350,25,300,50);
        logoLbl.setForeground(Color.orange);

        String[][] dataFromDatabase = {{"ABCDE","Pepsi","small","50"},
                {"ABCDR","Pepsi","Medium","100"},
                {"ABCDT","Pepsi","Large","150"},
                {"ADDSA","Tuc biscuits","Medium","30"}};
        String[] productColumn = {"Product ID","Product name","Product variant","Price"};
        DefaultTableModel transactionsDtm = new DefaultTableModel(dataFromDatabase,productColumn);
        JTable transactionsTable = new JTable(transactionsDtm);
        JScrollPane transactionsSp = new JScrollPane(transactionsTable);
        transactionsSp.setBounds(100,100,750,500);
        transactionsSp.setBorder(BorderFactory.createLineBorder(Color.black,5));


        //Button functionality
        backBtn.addActionListener(e->{
            frame.dispose();
            new AdminUI();
        });
        transactionPnl.add(backBtn);
        transactionPnl.add(transactionsSp);
        transactionPnl.add(logoLbl);

        frame.add(transactionPnl);
        frame.setSize(1035,730);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


    }
}
