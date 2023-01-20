package UI;

import service.DailyReportServices;
import service.TransactionService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TransactionUI {
    TransactionService transactionService = new TransactionService();
    DailyReportServices dailyReportServices = new DailyReportServices();

    public TransactionUI(){
        JFrame frame = new JFrame("POS SYSTEM");



        JPanel transactionPanel = new JPanel();

        JButton back = new JButton(new ImageIcon("src/Assets/back_8.png"));
        back.setBackground(new Color(1, 176, 222));
        back.setBounds(10,2,100,50);
        back.addActionListener(e->{
            new AdminUI();
            frame.dispose();
        });

        JLabel adminlogolbl= new JLabel("Transactions");
        adminlogolbl.setFont(new Font("Calibri", Font.PLAIN, 50));
        adminlogolbl.setBounds(160,30,400,50);
        adminlogolbl.setForeground(Color.orange);



        String[][] dataFromDatabase = transactionService.getDataForJTable();
        String[] productColumn = {"Transaction ID","Staff name","Transaction Date","Total amount"};
        DefaultTableModel transactionsDtm = new DefaultTableModel(dataFromDatabase,productColumn);
        JTable transactionsTable = new JTable(transactionsDtm);
        JScrollPane transactionsSp = new JScrollPane(transactionsTable);
        transactionsSp.setBounds(40, 90, 520, 450);


        JButton openTransactionBtn = new JButton("Open");
        openTransactionBtn.setBounds(140,570,100,30);


        JLabel totalProfitLbl = new JLabel("Total Profit : ");
        totalProfitLbl.setBounds(250,570,150,20);
        totalProfitLbl.setFont(new Font("Serif", Font.PLAIN, 20));

        JLabel totalBillAmountLbl = new JLabel(dailyReportServices.getTotalProfit().toString());
        totalBillAmountLbl.setBounds(360,570,150,25);
        totalBillAmountLbl.setFont(new Font("Serif", Font.PLAIN, 20));
        totalBillAmountLbl.setBorder(BorderFactory.createLineBorder(Color.black,2));
        totalBillAmountLbl.setBackground(Color.WHITE);
        totalBillAmountLbl.setOpaque(true);

        openTransactionBtn.addActionListener(e->{
            if(transactionsTable.getSelectedRow()<0){
                JOptionPane.showMessageDialog(frame,"Please Select any Transaction!!");
                return;
            }
            frame.dispose();
            Integer transactionId = Integer.valueOf(transactionsTable.getValueAt(transactionsTable.getSelectedRow(),0).toString());
            new ViewTransactionUI(transactionId,true);
        });


        transactionPanel.add(openTransactionBtn);
        transactionPanel.add(transactionsSp);
        transactionPanel.add(adminlogolbl);
        transactionPanel.add(totalProfitLbl);
        transactionPanel.add(totalBillAmountLbl);
        transactionPanel.setBounds(220,30,600,640);
        transactionPanel.setBackground(Color.GRAY);
        transactionPanel.setBorder(BorderFactory.createLineBorder(Color.black,10));
        transactionPanel.setLayout(null);


        frame.add(back);
        frame.add(transactionPanel);
        frame.setSize(1035,730);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
