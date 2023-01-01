package UI;

import domain.Users;
import domain.Varient;
import repository.UserRepository;
import service.AuthenticationSerivice;

import javax.swing.*;
import java.awt.*;

public class AddUserUI {

    public static void main(String[] args) {
        new AddUserUI();
    }
    AuthenticationSerivice authenticationSerivice = new AuthenticationSerivice();
    UserRepository userRepository = new UserRepository();
    public AddUserUI() {
        JFrame frame = new JFrame("POS System");
        JLabel userIdlbl=new JLabel("Enter User ID : ");
        userIdlbl.setBounds(135,130,150,20);

        JButton back = new JButton(AddCategoryUI.Imagepath);
        back.setBackground(new Color(1, 176, 222));
        back.setBounds(10,2,100,50);
        back.addActionListener(e->{
            new AdminUI();
            frame.dispose();
        });

        JLabel passwordLbl=new JLabel("Enter User Password : ");
        passwordLbl.setBounds(80,180,170,20);

        JLabel usernameLbl=new JLabel("Enter Name : ");
        usernameLbl.setBounds(145,230,150,20);

        JLabel userEmailLbl=new JLabel("Enter Email : ");
        userEmailLbl.setBounds(150,280,150,20);

        JLabel userDesignationLbl=new JLabel("Enter Designation : ");
        userDesignationLbl.setBounds(100,330,150,20);



        JTextField userIdTF = new JTextField();
        userIdTF.setBounds(250,132,150,20);

        JTextField userpassWordTf = new JTextField();
        userpassWordTf.setBounds(250,182,150,20);

        JTextField userNameTf = new JTextField();
        userNameTf.setBounds(250,232,150,20);

        JTextField UseremailTf = new JTextField();
        UseremailTf.setBounds(250,282,150,20);

      String  Designations[]={"admin","user"};
        JComboBox userDesignationCb = new JComboBox(Designations);
        userDesignationCb.setBounds(250,332,150,20);


        JButton AddUser = new JButton("Add User");
        AddUser.setBounds(270,382,100,20);



        AddUser.addActionListener(e ->{

            Users user =new Users(userIdTF.getText(),userpassWordTf.getText(),userNameTf.getText(),userDesignationCb.getSelectedItem().toString(),UseremailTf.getText());
            if(authenticationSerivice.addUser(user)){
                JOptionPane.showMessageDialog(frame, "User added Successfully");
            }
            else{
                JOptionPane.showMessageDialog(frame, "User Already Available");
            }


        });


        frame.add(back);
        frame.add(userIdlbl);
        frame.add(passwordLbl);
        frame.add(usernameLbl);
        frame.add(userEmailLbl);
        frame.add(userIdTF);
        frame.add(userpassWordTf);
        frame.add(userNameTf);
        frame.add(UseremailTf);
        frame.add(userDesignationLbl);
        frame.add(userDesignationCb);
        frame.add(AddUser);
        frame.setSize(600,600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
