package UI;

import service.AuthenticationSerivice;
import service.DailyReportServices;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DailyReportUi {

    public static void main(String[] args) {
        new DailyReportUi();
    }

    DailyReportServices transactionServices = new DailyReportServices();
    AuthenticationSerivice authenticationSerivice = new AuthenticationSerivice();
    public DailyReportUi() {
        JFrame frame = new JFrame("Daily Report");

        JPanel DailyReportPanel = new JPanel();

        JButton back = new JButton(new ImageIcon("/home/murtaza/Desktop/POS PROJ/pos-system/src/Assets/back_8.png"));
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

        String usersList[]= authenticationSerivice.getAllusers();
        JComboBox comboBox = new JComboBox(usersList);
        comboBox.setBounds(1100,100,110,30);


        String[] DailyReportColumns = {"Transaction ID","UserID","Transaction Date","Total Ammount"};

        String[][] data = transactionServices.getDataForTable(DailyReportColumns.length);
        DefaultTableModel dailyReportDtm = new DefaultTableModel(data, DailyReportColumns);
        JTable dailyReportPanel = new JTable(dailyReportDtm);
        JScrollPane dailyReportSp = new JScrollPane(dailyReportPanel);
        dailyReportSp.setBounds(10, 90, 580, 500);



        DailyReportPanel.add(dailyReportSp);
        DailyReportPanel.add(dailyReportlogolbl);
        DailyReportPanel.setBounds(350,50,600,640);
        DailyReportPanel.setBackground(Color.GRAY);
        DailyReportPanel.setBorder(BorderFactory.createLineBorder(Color.black,10));
        DailyReportPanel.setLayout(null);

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
