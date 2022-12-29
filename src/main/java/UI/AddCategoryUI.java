package UI;

import domain.Category;
import domain.Varient;
import service.CategoryServices;
import service.VarientServices;

import javax.swing.*;
import java.awt.*;

public class AddCategoryUI {
    JButton donebtn=null;
    JLabel categorynamelabel=null;
    JTextField categorynameTf=null;
    JLabel categoryLogoLabel=null;
    CategoryServices categoryServices = new CategoryServices();


    public AddCategoryUI(Object object2) {
        JFrame frame = new JFrame("POS SYSTEM");

        JPanel addCategoryPanel = new JPanel();

        JButton back = new JButton(new ImageIcon("/home/murtaza/Desktop/POS PROJ/pos-system/src/Assets/back_8.png"));
        back.setBackground(new Color(1, 176, 222));
        back.setBounds(10,2,100,50);
        back.addActionListener(e->{
            new CategoryUI();
            frame.dispose();
        });

        if(object2!=null){
            categoryLogoLabel =new JLabel("Edit Category ");
            categoryLogoLabel.setFont(new Font("Calibri", Font.PLAIN, 50));
            categoryLogoLabel.setBounds(150,30,400,50);
            categoryLogoLabel.setForeground(Color.orange);

            categorynamelabel = new JLabel("Enter Name");
            categorynamelabel.setBounds(100,100,170,40);
            categorynamelabel.setFont(new Font("calibri",Font.BOLD,20));

            categorynameTf = new JTextField();
            categorynameTf.setBounds(250,105,170,30);
            String temp=object2.toString();

            categorynameTf.setText(temp);



            donebtn = new JButton(new ImageIcon("/home/murtaza/Desktop/POS PROJ/pos-system/src/Assets/done.png"));
            donebtn.setBounds(280, 190, 90, 40);

            donebtn.addActionListener(e->{
                String temp2=categorynameTf.getText();
                if(categoryServices.update(object2.toString(),temp2)){
                    JOptionPane.showMessageDialog(frame, "Category Updated Successfully");
                }
                else {
                    JOptionPane.showMessageDialog(frame,"Category not updated Successfully");

                }
            });
        }
        else{
            categoryLogoLabel =new JLabel("add Category ");
            categoryLogoLabel.setFont(new Font("Calibri", Font.PLAIN, 50));
            categoryLogoLabel.setBounds(150,30,400,50);
            categoryLogoLabel.setForeground(Color.orange);
            categoryLogoLabel = new JLabel("Enter Name");
            categoryLogoLabel.setBounds(100,100,170,40);
            categoryLogoLabel.setFont(new Font("calibri",Font.BOLD,20));

            categorynameTf = new JTextField();
            categorynameTf.setBounds(250,105,170,30);

            donebtn = new JButton(new ImageIcon("/home/murtaza/Desktop/POS PROJ/pos-system/src/Assets/done.png"));
            donebtn.setBounds(280, 190, 90, 40);

            donebtn.addActionListener(e->{
                Category cat =new Category(categorynameTf.getText());
                if(categoryServices.addService(cat)==true){
                    JOptionPane.showMessageDialog(frame, "Category saved Successfully");
                }
                else {
                    JOptionPane.showMessageDialog(frame,"Category not saved Successfully");

                }
            });

        }



        addCategoryPanel.add(donebtn);

        addCategoryPanel.add(categorynamelabel);
        addCategoryPanel.add(categorynameTf);
        addCategoryPanel.add(categoryLogoLabel);
        addCategoryPanel.setBounds(370,50,600,400);
        addCategoryPanel.setBackground(Color.GRAY);
        addCategoryPanel.setBorder(BorderFactory.createLineBorder(Color.black,10));
        addCategoryPanel.setLayout(null);


        frame.add(back);
        frame.add(addCategoryPanel);
        frame.setSize(1500,730);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}

