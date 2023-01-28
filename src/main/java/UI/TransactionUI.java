package UI;

import service.DailyReportServices;
import service.TransactionService;
import service.UserService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.MessageFormat;

public class TransactionUI {
    TransactionService transactionService = new TransactionService();
    UserService authenticationSerivice = new UserService();
    DailyReportServices dailyReportServices = new DailyReportServices();

    public static void main(String[] args) {
        new TransactionUI();
    }
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

        JLabel dropdownlbl = new JLabel("See Report Of Users");
        dropdownlbl.setFont(new Font("Calibri", Font.BOLD, 15));
        dropdownlbl.setBounds(840,100,400,50);

        String[] usersList = authenticationSerivice.getAllusers();
        JComboBox comboBox = new JComboBox(usersList);
        comboBox.setBounds(850,150,110,30);

        String[] monthList = {"All","January","February","March","April","May","June","July","August","September",
                "October","November","December"};
        JComboBox monthComboBox = new JComboBox(monthList);
        monthComboBox.setBounds(850,200,110,30);

        JButton userReportBtn = new JButton("See Report");
        userReportBtn.setFont(new Font("Calibri", Font.BOLD, 15));
        userReportBtn.setBounds(840,250,130,30);

        JButton printReportBtn = new JButton("Print Report");
        printReportBtn.setFont(new Font("Calibri", Font.BOLD, 15));
        printReportBtn.setBounds(840,300,130,30);


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
        userReportBtn.addActionListener(e->{
            if(comboBox.getSelectedIndex()==0 && monthComboBox.getSelectedIndex()==0){
                transactionsTable.setModel(transactionsDtm);
                return;
            }
            String[][] dataforUser = null;
            if(comboBox.getSelectedIndex()==0 && monthComboBox.getSelectedIndex()!=0){
                dataforUser = authenticationSerivice.getALLTransactionReportOfMonthly(monthComboBox.getSelectedItem().toString());
            }else if(comboBox.getSelectedIndex()!=0 && monthComboBox.getSelectedIndex()==0){
                dataforUser = authenticationSerivice.getALLReportOfUser(comboBox.getSelectedItem().toString(),"");
            }else{
                dataforUser = authenticationSerivice.getALLReportOfUser(comboBox.getSelectedItem().toString(),monthComboBox.getSelectedItem().toString());
            }
            DefaultTableModel dtm2 = new DefaultTableModel(dataforUser, productColumn);
            transactionsTable.setModel(dtm2);
            totalBillAmountLbl.setText(dailyReportServices.getAllTransactionReportUserProfit(comboBox.getSelectedItem().toString()).toString());
        });
        printReportBtn.addActionListener(e->{
            MessageFormat header = new MessageFormat("All Report ");
            MessageFormat footer = new MessageFormat("Powered By StepWay");
            transactionsDtm.addRow(new String[]{"","","Total Bill : ",totalBillAmountLbl.getText()});
            try{
                transactionsTable.print(JTable.PrintMode.FIT_WIDTH,header,footer);
            }catch (Exception a){
                a.printStackTrace();
            }
            transactionsDtm.removeRow(transactionsTable.getRowCount()-1);
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
        frame.add(printReportBtn);
        frame.add(userReportBtn);
        frame.add(dropdownlbl);
        frame.add(comboBox);
        frame.add(monthComboBox);
        frame.add(transactionPanel);
        frame.setSize(1035,730);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
