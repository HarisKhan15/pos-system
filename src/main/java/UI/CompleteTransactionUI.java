package UI;

import repository.CartRepository;
import service.CartService;
import service.TransactionService;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class CompleteTransactionUI {
    TransactionService transactionService = new TransactionService();
    CartService cartService = new CartService();


    public CompleteTransactionUI(Double totalAmount){
        JFrame frame = new JFrame("POS System");
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);

        JLabel totalAmountLbl=new JLabel("Total amount :  "+totalAmount.toString()+ " Rs");
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
            if(Double.parseDouble(receivedAmountTf.getText())<totalAmount){
                JOptionPane.showMessageDialog(frame,"Received amount is less than the total bill");
                return;
            }
            JOptionPane.showMessageDialog(frame,"Transaction Completed \n Returned Amount : "+(Double.parseDouble(receivedAmountTf.getText())-totalAmount)+" Rs");

            //Entering Transaction Data into Transaction Table and getting It's Id
            int transactionId = transactionService.transactionEnterIntoDatabaseAndGetId("haris",totalAmount);

            //Entering Items into Database
            cartService.addItemsToDatabase(transactionId);

            CartRepository.clearList();
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
