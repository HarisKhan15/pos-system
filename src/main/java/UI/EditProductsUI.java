package UI;

import javax.swing.*;
import java.awt.*;

public class EditProductsUI {

    public EditProductsUI() {
        JFrame frame = new JFrame("POS SYSTEM");



        JPanel addprocuctPanel = new JPanel();

        JButton back = new JButton(new ImageIcon("/home/murtaza/Desktop/POS PROJ/pos-system/src/Assets/back_8.png"));
        back.setBackground(new Color(1, 176, 222));
        back.setBounds(10,2,100,50);
        back.addActionListener(e->{
            new ProductUI();
            frame.dispose();
        });

        JLabel editProductsLogoLabel =new JLabel("Edit Product");
        editProductsLogoLabel.setFont(new Font("Calibri", Font.PLAIN, 50));
        editProductsLogoLabel.setBounds(150,30,400,50);
        editProductsLogoLabel.setForeground(Color.orange);

        JLabel editProductnamelabel = new JLabel("Enter Name");
        editProductnamelabel.setBounds(100,100,170,40);
        editProductnamelabel.setFont(new Font("calibri",Font.BOLD,20));

        JTextField editProductnameTf = new JTextField();
        editProductnameTf.setBounds(250,105,170,30);

        String[] optionsToChoose = {"liquid","solid","Gand"};
        JComboBox<String> jComboBox = new JComboBox<>(optionsToChoose);
        jComboBox.setBounds(250, 160, 170, 25);
        jComboBox.setFont(new Font("calibri",Font.PLAIN,20));

        JButton donebtn = new JButton(new ImageIcon("/home/murtaza/Desktop/POS PROJ/pos-system/src/Assets/done.png"));
        donebtn.setBounds(280, 190, 90, 40);

        donebtn.addActionListener(e->{
            String option = "Updated as "+editProductnameTf.getText()+" and Category "+ jComboBox.getItemAt(jComboBox.getSelectedIndex());
            JOptionPane.showMessageDialog(frame,option);
        });
        addprocuctPanel.add(jComboBox);
        addprocuctPanel.add(donebtn);
        addprocuctPanel.add(editProductnamelabel);
        addprocuctPanel.add(editProductnameTf);
        addprocuctPanel.add(editProductsLogoLabel);
        addprocuctPanel.setBounds(370,50,600,400);
        addprocuctPanel.setBackground(Color.GRAY);
        addprocuctPanel.setBorder(BorderFactory.createLineBorder(Color.black,10));
        addprocuctPanel.setLayout(null);


        frame.add(back);
        frame.add(addprocuctPanel);
        frame.setSize(1500,730);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
