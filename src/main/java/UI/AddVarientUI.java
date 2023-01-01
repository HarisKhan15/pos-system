package UI;

import domain.Varient;
import repository.VarientRepository;
import service.VarientServices;

import javax.swing.*;
import java.awt.*;

public class AddVarientUI {
    public static void main(String[] args) {

        new AddVarientUI(null);
    }
    JButton donebtn=null;
    JLabel varientnamelabel=null;
    JTextField VarientnameTf =null;
    JLabel VarientLogoLabel=null;


    VarientServices varientServices = new VarientServices();
    public AddVarientUI(Object object) {
        JFrame frame = new JFrame("POS SYSTEM");

        JPanel addVarientPanel = new JPanel();

        JButton back = new JButton(new ImageIcon("src/Assets/back_8.png"));
        back.setBackground(new Color(1, 176, 222));
        back.setBounds(10,2,100,50);
        back.addActionListener(e->{
            new VariantsUI();
            frame.dispose();
        });

        if(object!=null){
             VarientLogoLabel =new JLabel("Edit Varient ");
            VarientLogoLabel.setFont(new Font("Calibri", Font.PLAIN, 50));
            VarientLogoLabel.setBounds(150,30,400,50);
            VarientLogoLabel.setForeground(Color.orange);

            varientnamelabel = new JLabel("Enter Name");
            varientnamelabel.setBounds(100,100,170,40);
            varientnamelabel.setFont(new Font("calibri",Font.BOLD,20));

            VarientnameTf = new JTextField();
            VarientnameTf.setBounds(250,105,170,30);
            String temp=object.toString();

            VarientnameTf.setText(temp);



            donebtn = new JButton(new ImageIcon("/home/murtaza/Desktop/POS PROJ/pos-system/src/Assets/done.png"));
            donebtn.setBounds(280, 190, 90, 40);

            donebtn.addActionListener(e->{
                String temp2=VarientnameTf.getText();
                if(varientServices.update(object.toString(),temp2)){
                    JOptionPane.showMessageDialog(frame, "Varient Updated Successfully");
                }
                else {
                    JOptionPane.showMessageDialog(frame,"Varient not updated Successfully");

                }
            });
        }else{
            VarientLogoLabel =new JLabel("add Varient ");
            VarientLogoLabel.setFont(new Font("Calibri", Font.PLAIN, 50));
            VarientLogoLabel.setBounds(150,30,400,50);
            VarientLogoLabel.setForeground(Color.orange);
            varientnamelabel = new JLabel("Enter Name");
            varientnamelabel.setBounds(100,100,170,40);
            varientnamelabel.setFont(new Font("calibri",Font.BOLD,20));

            VarientnameTf = new JTextField();
            VarientnameTf.setBounds(250,105,170,30);

            donebtn = new JButton(new ImageIcon("/home/murtaza/Desktop/POS PROJ/pos-system/src/Assets/done.png"));
            donebtn.setBounds(280, 190, 90, 40);

            donebtn.addActionListener(e->{
                Varient vrt =new Varient(VarientnameTf.getText());
                if(varientServices.addVarient(vrt)==true){
                    JOptionPane.showMessageDialog(frame, "Varient saved Successfully");
                }
                else {
                    JOptionPane.showMessageDialog(frame,"Varient not saved Successfully");

                }
            });

        }



        addVarientPanel.add(donebtn);

        addVarientPanel.add(varientnamelabel);
        addVarientPanel.add(VarientnameTf);
        addVarientPanel.add(VarientLogoLabel);
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
