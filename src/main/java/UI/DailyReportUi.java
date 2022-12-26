package UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DailyReportUi {

    public static void main(String[] args) {
        new DailyReportUi();
    }
    public DailyReportUi() {
        JFrame frame = new JFrame("POS SYSTEM");



        JPanel adminAreaPanel = new JPanel();

        JButton back = new JButton(new ImageIcon("/home/murtaza/Desktop/POS PROJ/pos-system/src/Assets/back_8.png"));
        back.setBackground(new Color(1, 176, 222));
        back.setBounds(10,2,100,50);
        back.addActionListener(e->{
            new AdminUI();
            frame.dispose();
        });

        JLabel adminlogolbl= new JLabel("Daily Report");
        adminlogolbl.setFont(new Font("Calibri", Font.PLAIN, 50));
        adminlogolbl.setBounds(150,30,400,50);
        adminlogolbl.setForeground(Color.orange);



        String[][] dataFromDatabase = {{"ABCDE","Pepsi","small","50"},
                {"ABCDR","Pepsi","Medium","100"},
                {"ABCDT","Pepsi","Large","150"},
                {"ADDSA","Tuc biscuits","Medium","30"}};
        String[] productColumn = {"Product ID","Product name","Product variant","Price"};
        DefaultTableModel transactionsDtm = new DefaultTableModel(dataFromDatabase,productColumn);
        JTable transactionsTable = new JTable(transactionsDtm);
        JScrollPane transactionsSp = new JScrollPane(transactionsTable);
        transactionsSp.setBounds(10,130,580,500);


        adminAreaPanel.add(transactionsSp);
        adminAreaPanel.add(adminlogolbl);
        adminAreaPanel.setBounds(350,50,600,640);
        adminAreaPanel.setBackground(Color.GRAY);
        adminAreaPanel.setBorder(BorderFactory.createLineBorder(Color.black,10));
        adminAreaPanel.setLayout(null);


        frame.add(back);
        frame.add(adminAreaPanel);
        frame.setSize(1500,730);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
