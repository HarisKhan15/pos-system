package UI;

import service.TransactionService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ViewTransactionUI {
    TransactionService transactionService = new TransactionService();

    public static void main(String[] args) {
        new ViewTransactionUI(1);
    }
    public ViewTransactionUI(Integer transactionId){
        JFrame frame = new JFrame("POS SYSTEM");


        JPanel transactionPanel = new JPanel();
        transactionPanel.setBounds(220,30,600,640);
        transactionPanel.setBackground(Color.GRAY);
        transactionPanel.setBorder(BorderFactory.createLineBorder(Color.black,10));
        transactionPanel.setLayout(null);

        JButton backBtn = new JButton("Back");
        backBtn.setBounds(20,20,100,30);

        JLabel logoLbl = new JLabel("Transaction");
        logoLbl.setFont(new Font("Calibri", Font.PLAIN, 50));
        logoLbl.setBounds(180,50,300,50);
        logoLbl.setForeground(Color.orange);

        String[][] dataFromDatabase = transactionService.getSpecificDataForJTable(transactionId);
        String[] productColumn = {"Product Name","Variant","Category","Quantity","Amount"};
        DefaultTableModel transactionsDtm = new DefaultTableModel(dataFromDatabase,productColumn);
        JTable transactionsTable = new JTable(transactionsDtm);
        JScrollPane transactionsSp = new JScrollPane(transactionsTable);
        transactionsSp.setBounds(10,130,580,500);

        backBtn.addActionListener(e->{
            frame.dispose();
            new TransactionUI();
        });
        transactionPanel.add(backBtn);
        transactionPanel.add(logoLbl);
        transactionPanel.add(transactionsSp);

        frame.add(transactionPanel);
        frame.setSize(1035,730);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
