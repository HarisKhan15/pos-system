package UI;

import service.AuthenticationSerivice;
import service.DailyReportServices;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicReference;

public class DailyReportUi {



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

        JLabel dropdownlbl = new JLabel("See Report Of Users");
        dropdownlbl.setFont(new Font("Calibri", Font.BOLD, 15));
        dropdownlbl.setBounds(1080,100,400,50);

        JButton refresh = new JButton("Refresh");
        refresh.setFont(new Font("Calibri", Font.BOLD, 15));
        refresh.setBounds(1110,250,100,30);

        refresh.addActionListener(e->{
            new DailyReportUi();
            frame.dispose();
        });
        String usersList[]= authenticationSerivice.getAllusers();
        JComboBox comboBox = new JComboBox(usersList);
        comboBox.setBounds(1100,150,110,30);

        JButton userReportBtn = new JButton("See Report");
        userReportBtn.setFont(new Font("Calibri", Font.BOLD, 15));
        userReportBtn.setBounds(1095,200,130,30);
        String[] DailyReportColumns = {"Transaction ID","UserID","Transaction Date","Total Amount"};




        userReportBtn.addActionListener(e->{
            JScrollPane dailyReportSp=maketable(authenticationSerivice.getreportofUser(comboBox.getSelectedItem().toString()), DailyReportColumns);
            DailyReportPanel.add(dailyReportSp);
        });




        DailyReportPanel.add( maketable(transactionServices.getDataForTable(),DailyReportColumns));
        DailyReportPanel.add(dailyReportlogolbl);
        DailyReportPanel.setBounds(350,50,600,640);
        DailyReportPanel.setBackground(Color.GRAY);
        DailyReportPanel.setBorder(BorderFactory.createLineBorder(Color.black,10));
        DailyReportPanel.setLayout(null);

        frame.add(refresh);
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
    String[][] data = transactionServices.getDataForTable();
    public JScrollPane maketable(String datafortable[][],String columns[]) {
        String[][] data = datafortable;
        DefaultTableModel dailyReportDtm = new DefaultTableModel(data, columns);
        JTable dailyReportPanel = new JTable(dailyReportDtm);
        JScrollPane dailyReportSp = new JScrollPane(dailyReportPanel);
        dailyReportSp.setBounds(10, 90, 580, 500);
        return dailyReportSp;
    }
}
