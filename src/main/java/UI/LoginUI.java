package UI;

import service.AuthenticationSerivice;

import javax.swing.*;
import java.awt.*;

public class LoginUI {
    AuthenticationSerivice authenticationSerivice = new AuthenticationSerivice();



    public LoginUI(){
        JFrame frame = new JFrame("POS System");
        frame.getContentPane().setBackground(Color.GRAY);

        JLabel logoLbl = new JLabel("POS System");
        logoLbl.setFont(new Font("Calibri", Font.PLAIN, 50));
        logoLbl.setBounds(130,30,300,50);
        logoLbl.setForeground(Color.orange);


        JLabel usernameLbl=new JLabel("Enter UserId : ");
        usernameLbl.setBounds(110,130,150,20);

        JLabel passwordLbl=new JLabel("Enter Password : ");
        passwordLbl.setBounds(110,180,150,20);

        JTextField usernameTf = new JTextField();
        usernameTf.setBounds(240,132,150,20);

        JPasswordField passwordTf = new JPasswordField();
        passwordTf.setBounds(240,182,150,20);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(200,240,100,20);

        loginBtn.addActionListener(e ->{
            String designation = authenticationSerivice.checkLogin(usernameTf.getText(),passwordTf.getText());
            if(designation!=null&&designation.equalsIgnoreCase("staff")){
                JOptionPane.showMessageDialog(frame,"Staff Login successful");
                frame.dispose();
                new UserTransactionUI();
                return;
            }else if(designation!=null&&designation.equalsIgnoreCase("admin")){
                JOptionPane.showMessageDialog(frame,"Admin Login successful");
                frame.dispose();
                new AdminUI();
                return;
            }
            JOptionPane.showMessageDialog(frame,"Login Unsuccessful");

            usernameTf.setText("");
            passwordTf.setText("");
        });

        frame.add(logoLbl);
        frame.add(usernameLbl);
        frame.add(usernameTf);
        frame.add(passwordLbl);
        frame.add(passwordTf);
        frame.add(loginBtn);
        frame.setSize(500,500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
