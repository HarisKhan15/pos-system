package UI;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class CompleteTransactionUI {
    public static void main(String[] args) {

    }

    public CompleteTransactionUI(Integer totalAmount){
        JFrame frame = new JFrame("POS System");
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);

        JLabel totalAmountLbl=new JLabel("Total amount :  "+totalAmount.toString());
        totalAmountLbl.setBounds(30,130,450,20);
        totalAmountLbl.setFont(new Font("Serif", Font.PLAIN, 20));

        JLabel receivedAmountLbl=new JLabel("Received amount : ");
        receivedAmountLbl.setBounds(120,180,150,20);

        JTextField receivedAmountTf = new JTextField();
        receivedAmountTf.setBounds(250,182,150,20);

        JButton completeBtn = new JButton("Complete Transaction");
        completeBtn.setBounds(180,220,180,20);

        completeBtn.addActionListener(e->{
            if(receivedAmountTf.getText().isEmpty()){
                JOptionPane.showMessageDialog(frame,"Please Enter received amount!!");
                return;
            }
            if(Integer.parseInt(receivedAmountTf.getText())<totalAmount){
                JOptionPane.showMessageDialog(frame,"Received amount is less than the total bill");
                return;
            }
            JOptionPane.showMessageDialog(frame,"Transaction Complete");
            frame.dispose();
            new UserTransactionUI();

        });

        frame.add(totalAmountLbl);
        frame.add(receivedAmountLbl);
        frame.add(receivedAmountTf);
        frame.add(completeBtn);
        frame.setSize(500,500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}
