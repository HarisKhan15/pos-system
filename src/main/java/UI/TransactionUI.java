package UI;

import service.TransactionService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TransactionUI {
    TransactionService transactionService = new TransactionService();


    public TransactionUI(){
        JFrame frame = new JFrame("POS SYSTEM");



        JPanel transactionPanel = new JPanel();

        JButton back = new JButton(new ImageIcon("/home/murtaza/Desktop/POS PROJ/pos-system/src/Assets/back_8.png"));
        back.setBackground(new Color(1, 176, 222));
        back.setBounds(10,2,100,50);
        back.addActionListener(e->{
            new AdminUI();
            frame.dispose();
        });

        JLabel adminlogolbl= new JLabel("Transactions");
        adminlogolbl.setFont(new Font("Calibri", Font.PLAIN, 50));
        adminlogolbl.setBounds(150,30,400,50);
        adminlogolbl.setForeground(Color.orange);



        String[][] dataFromDatabase = transactionService.getDataForJTable();
        String[] productColumn = {"Transaction ID","Staff name","Transaction Date","Total amount"};
        DefaultTableModel transactionsDtm = new DefaultTableModel(dataFromDatabase,productColumn);
        JTable transactionsTable = new JTable(transactionsDtm);
        JScrollPane transactionsSp = new JScrollPane(transactionsTable);
        transactionsSp.setBounds(10,130,580,400);


        JButton openTransactionBtn = new JButton("Open");
        openTransactionBtn.setBounds(240,560,100,30);


        openTransactionBtn.addActionListener(e->{
            if(transactionsTable.getSelectedRow()<0){
                JOptionPane.showMessageDialog(frame,"Please Select any Transaction!!");
                return;
            }
            frame.dispose();
            Integer transactionId = Integer.valueOf(transactionsTable.getValueAt(transactionsTable.getSelectedRow(),0).toString());
            new ViewTransactionUI(transactionId);
        });


        transactionPanel.add(openTransactionBtn);
        transactionPanel.add(transactionsSp);
        transactionPanel.add(adminlogolbl);
        transactionPanel.setBounds(350,50,600,640);
        transactionPanel.setBackground(Color.GRAY);
        transactionPanel.setBorder(BorderFactory.createLineBorder(Color.black,10));
        transactionPanel.setLayout(null);


        frame.add(back);
        frame.add(transactionPanel);
        frame.setSize(1500,730);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
