package UI;

import service.DailyReportServices;
import service.TransactionService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ViewTransactionUI {
    TransactionService transactionService = new TransactionService();
    DailyReportServices dailyReportServices = new DailyReportServices();


    public ViewTransactionUI(Integer transactionId,boolean page){
        JFrame frame = new JFrame("POS SYSTEM");


        JPanel transactionPanel = new JPanel();
        transactionPanel.setBounds(220,30,600,640);
        transactionPanel.setBackground(Color.GRAY);
        transactionPanel.setBorder(BorderFactory.createLineBorder(Color.black,10));
        transactionPanel.setLayout(null);

        JButton backBtn = new JButton(AddCategoryUI.Imagepath);
        backBtn.setBounds(20,20,100,30);

        JLabel logoLbl = new JLabel("Transaction");
        logoLbl.setFont(new Font("Calibri", Font.PLAIN, 50));
        logoLbl.setBounds(200,50,300,50);
        logoLbl.setForeground(Color.orange);

        String[][] dataFromDatabase = transactionService.getSpecificDataForJTable(transactionId);
        String[] productColumn = {"Product Name","Variant","Category","Quantity","Amount"};
        DefaultTableModel transactionsDtm = new DefaultTableModel(dataFromDatabase,productColumn);
        JTable transactionsTable = new JTable(transactionsDtm);
        JScrollPane transactionsSp = new JScrollPane(transactionsTable);
        transactionsSp.setBounds(40, 90, 520, 450);

        backBtn.addActionListener(e->{
            if(page){
                frame.dispose();
                new TransactionUI();
                return;
            }
            frame.dispose();
            new DailyReportUi();
        });
        JLabel totalProfitLbl = new JLabel("Total Profit : ");
        totalProfitLbl.setBounds(250,570,150,20);
        totalProfitLbl.setFont(new Font("Serif", Font.PLAIN, 20));

        JLabel totalBillAmountLbl = new JLabel(dailyReportServices.getProfitPerTransaction(transactionId).toString());
        totalBillAmountLbl.setBounds(360,570,150,25);
        totalBillAmountLbl.setFont(new Font("Serif", Font.PLAIN, 20));
        totalBillAmountLbl.setBorder(BorderFactory.createLineBorder(Color.black,2));
        totalBillAmountLbl.setBackground(Color.WHITE);
        totalBillAmountLbl.setOpaque(true);

        transactionPanel.add(totalProfitLbl);
        transactionPanel.add(totalBillAmountLbl);
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
