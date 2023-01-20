package UI;

import service.UserService;
import service.DailyReportServices;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DailyReportUi {



    DailyReportServices transactionServices = new DailyReportServices();
    UserService userService = new UserService();
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
        dailyReportlogolbl.setBounds(150,30,400,50);
        dailyReportlogolbl.setForeground(Color.orange);

        JLabel dropdownlbl = new JLabel("See Report Of Users");
        dropdownlbl.setFont(new Font("Calibri", Font.BOLD, 15));
        dropdownlbl.setBounds(1080,100,400,50);

        String usersList[]= userService.getAllusers();
        JComboBox comboBox = new JComboBox(usersList);
        comboBox.setBounds(1100,150,110,30);

        JButton userReportBtn = new JButton("See Report");
        userReportBtn.setFont(new Font("Calibri", Font.BOLD, 15));
        userReportBtn.setBounds(1095,200,130,30);

        String[] DailyReportColumns = {"Transaction ID","UserID","Transaction Date","Total Amount"};;

        String[][] data = transactionServices.getDataForTable();

        DefaultTableModel dailyReportDtm = new DefaultTableModel(data, DailyReportColumns);
        JTable dailyReportTable = new JTable(dailyReportDtm);
        JScrollPane dailyReportSp = new JScrollPane(dailyReportTable);
        dailyReportSp.setBounds(10, 90, 580, 500);

        userReportBtn.addActionListener(e->{
            if(comboBox.getSelectedIndex()==0){
              dailyReportTable.setModel(dailyReportDtm);
              return;
            }
            String[][] dataforUser = userService.getreportofUser(comboBox.getSelectedItem().toString());
            DefaultTableModel dtm2 = new DefaultTableModel(dataforUser, DailyReportColumns);
            dailyReportTable.setModel(dtm2);
        });




        DailyReportPanel.add(dailyReportSp);
        DailyReportPanel.add(dailyReportlogolbl);
        DailyReportPanel.setBounds(350,50,600,640);
        DailyReportPanel.setBackground(Color.GRAY);
        DailyReportPanel.setBorder(BorderFactory.createLineBorder(Color.black,10));
        DailyReportPanel.setLayout(null);

        frame.add(userReportBtn);
        frame.add(dropdownlbl);
        frame.add(comboBox);
        frame.add(back);
        frame.add(DailyReportPanel);
        frame.setSize(1500,730);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }


}
