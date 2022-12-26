package UI;

import javax.swing.*;
import java.awt.*;

public class EditVariantsUI {
    public static void main(String[] args) {
        new EditProductsUI();
    }
    public EditVariantsUI() {
        JFrame frame = new JFrame("POS SYSTEM");

        JPanel editVarientPanel = new JPanel();

        JButton back = new JButton(new ImageIcon("/home/murtaza/Desktop/POS PROJ/pos-system/src/Assets/back_8.png"));
        back.setBackground(new Color(1, 176, 222));
        back.setBounds(10,2,100,50);
        back.addActionListener(e->{
            new ProductUI();
            frame.dispose();
        });

        JLabel editVarientLogoLabel =new JLabel("Edit Varient ");
        editVarientLogoLabel.setFont(new Font("Calibri", Font.PLAIN, 50));
        editVarientLogoLabel.setBounds(150,30,400,50);
        editVarientLogoLabel.setForeground(Color.orange);

        JLabel editvarientnamelabel = new JLabel("Enter Name");
        editvarientnamelabel.setBounds(100,100,170,40);
        editvarientnamelabel.setFont(new Font("calibri",Font.BOLD,20));

        JTextField editVarientnameTf = new JTextField();
        editVarientnameTf.setBounds(250,105,170,30);

        String[] optionsToChoose = {"small","medium","large"};
        JComboBox<String> jComboBox = new JComboBox<>(optionsToChoose);
        jComboBox.setBounds(250, 160, 170, 25);
        jComboBox.setFont(new Font("calibri",Font.PLAIN,20));

        JButton donebtn = new JButton(new ImageIcon("/home/murtaza/Desktop/POS PROJ/pos-system/src/Assets/done.png"));
        donebtn.setBounds(280, 190, 90, 40);

        donebtn.addActionListener(e->{
            String option = "Updated as "+editVarientnameTf.getText()+" and Category "+ jComboBox.getItemAt(jComboBox.getSelectedIndex());
            JOptionPane.showMessageDialog(frame,option);
        });
        editVarientPanel.add(jComboBox);
        editVarientPanel.add(donebtn);
        editVarientPanel.add(editvarientnamelabel);
        editVarientPanel.add(editVarientnameTf);
        editVarientPanel.add(editVarientLogoLabel);
        editVarientPanel.setBounds(370,50,600,400);
        editVarientPanel.setBackground(Color.GRAY);
        editVarientPanel.setBorder(BorderFactory.createLineBorder(Color.black,10));
        editVarientPanel.setLayout(null);


        frame.add(back);
        frame.add(editVarientPanel);
        frame.setSize(1500,730);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
