package UI;

import service.DailyReportServices;
import service.UserService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicReference;

public class DailyReportUi {



    DailyReportServices transactionServices = new DailyReportServices();
    UserService authenticationSerivice = new UserService();
    public DailyReportUi() {
        JFrame frame = new JFrame("Daily Report");

        JPanel DailyReportPanel = new JPanel();

        JButton back = new JButton(new ImageIcon("src/Assets/back_8.png"));
        back.setBackground(new Color(1, 176, 222));
        back.setBounds(10,2,100,50);
        back.addActionListener(e->{
            new AdminUI();
            frame.dispose();
        });

        JLabel dailyReportlogolbl= new JLabel("Daily Report");
        dailyReportlogolbl.setFont(new Font("Calibri", Font.PLAIN, 50));
        dailyReportlogolbl.setBounds(180,30,400,50);
        dailyReportlogolbl.setForeground(Color.orange);

        JLabel dropdownlbl = new JLabel("See Report Of Users");
        dropdownlbl.setFont(new Font("Calibri", Font.BOLD, 15));
        dropdownlbl.setBounds(840,100,400,50);

        String[] usersList = authenticationSerivice.getAllusers();
        JComboBox comboBox = new JComboBox(usersList);
        comboBox.setBounds(850,150,110,30);

        JButton userReportBtn = new JButton("See Report");
        userReportBtn.setFont(new Font("Calibri", Font.BOLD, 15));
        userReportBtn.setBounds(840,200,130,30);

        String[] DailyReportColumns = {"Transaction ID","UserID","Transaction Date","Total Amount"};;

        String[][] data = transactionServices.getDataForTable();

        DefaultTableModel dailyReportDtm = new DefaultTableModel(data, DailyReportColumns);
        JTable dailyReportTable = new JTable(dailyReportDtm);
        JScrollPane dailyReportSp = new JScrollPane(dailyReportTable);
        dailyReportSp.setBounds(40, 90, 520, 450);

        JButton openTransactionBtn = new JButton("Open");
        openTransactionBtn.setBounds(140,570,100,30);

        JLabel totalProfitLbl = new JLabel("Total Profit : ");
        totalProfitLbl.setBounds(250,570,150,20);
        totalProfitLbl.setFont(new Font("Serif", Font.PLAIN, 20));

        JLabel totalBillAmountLbl = new JLabel(transactionServices.getDailyProfit().toString());
        totalBillAmountLbl.setBounds(360,570,150,25);
        totalBillAmountLbl.setFont(new Font("Serif", Font.PLAIN, 20));
        totalBillAmountLbl.setBorder(BorderFactory.createLineBorder(Color.black,2));
        totalBillAmountLbl.setBackground(Color.WHITE);
        totalBillAmountLbl.setOpaque(true);

        openTransactionBtn.addActionListener(e->{
            if(dailyReportTable.getSelectedRow()<0){
                JOptionPane.showMessageDialog(frame,"Please Select any Transaction!!");
                return;
            }
            frame.dispose();
            Integer transactionId = Integer.valueOf(dailyReportDtm.getValueAt(dailyReportTable.getSelectedRow(),0).toString());
            new ViewTransactionUI(transactionId,false);
        });

        userReportBtn.addActionListener(e->{
            if(comboBox.getSelectedIndex()==0){
              dailyReportTable.setModel(dailyReportDtm);
              return;
            }
            String[][] dataforUser = authenticationSerivice.getreportofUser(comboBox.getSelectedItem().toString());
            DefaultTableModel dtm2 = new DefaultTableModel(dataforUser, DailyReportColumns);
            dailyReportTable.setModel(dtm2);
            totalBillAmountLbl.setText(transactionServices.getUserProfit(comboBox.getSelectedItem().toString()).toString());
        });

        DailyReportPanel.add(totalProfitLbl);
        DailyReportPanel.add(totalBillAmountLbl);
        DailyReportPanel.add(openTransactionBtn);
        DailyReportPanel.add(dailyReportSp);
        DailyReportPanel.add(dailyReportlogolbl);
        DailyReportPanel.setBounds(220,30,600,640);
        DailyReportPanel.setBackground(Color.GRAY);
        DailyReportPanel.setBorder(BorderFactory.createLineBorder(Color.black,10));
        DailyReportPanel.setLayout(null);

        frame.add(userReportBtn);
        frame.add(dropdownlbl);
        frame.add(comboBox);
        frame.add(back);
        frame.add(DailyReportPanel);
        frame.setSize(1035,730);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }


}
