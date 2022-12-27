package UI;

import domain.Varient;
import service.VarientServices;

import javax.swing.*;
import java.awt.*;

public class AddVarientUI {
    public static void main(String[] args) {
        new AddVarientUI();
    }
    VarientServices varientServices = new VarientServices();
    public AddVarientUI() {
        JFrame frame = new JFrame("POS SYSTEM");

        JPanel addVarientPanel = new JPanel();

        JButton back = new JButton(new ImageIcon("/home/murtaza/Desktop/POS PROJ/pos-system/src/Assets/back_8.png"));
        back.setBackground(new Color(1, 176, 222));
        back.setBounds(10,2,100,50);
        back.addActionListener(e->{
            new VariantsUI();
            frame.dispose();
        });

        JLabel addVarientLogoLabel =new JLabel("Add Varient ");
        addVarientLogoLabel.setFont(new Font("Calibri", Font.PLAIN, 50));
        addVarientLogoLabel.setBounds(150,30,400,50);
        addVarientLogoLabel.setForeground(Color.orange);

        JLabel addvarientnamelabel = new JLabel("Enter Name");
        addvarientnamelabel.setBounds(100,100,170,40);
        addvarientnamelabel.setFont(new Font("calibri",Font.BOLD,20));

        JTextField addVarientnameTf = new JTextField();
        addVarientnameTf.setBounds(250,105,170,30);


        JButton donebtn = new JButton(new ImageIcon("/home/murtaza/Desktop/POS PROJ/pos-system/src/Assets/done.png"));
        donebtn.setBounds(280, 190, 90, 40);

        donebtn.addActionListener(e->{
            Varient vrt =new Varient(addVarientnameTf.getText());
            if(varientServices.addVarient(vrt)==true){
                JOptionPane.showMessageDialog(frame, "Varient saved Successfully");
            }
            else {
                JOptionPane.showMessageDialog(frame,"Varient not saved Successfully");

            }
        });

        addVarientPanel.add(donebtn);
        addVarientPanel.add(addvarientnamelabel);
        addVarientPanel.add(addVarientnameTf);
        addVarientPanel.add(addVarientLogoLabel);
        addVarientPanel.setBounds(370,50,600,400);
        addVarientPanel.setBackground(Color.GRAY);
        addVarientPanel.setBorder(BorderFactory.createLineBorder(Color.black,10));
        addVarientPanel.setLayout(null);


        frame.add(back);
        frame.add(addVarientPanel);
        frame.setSize(1500,730);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
