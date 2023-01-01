package UI;

import service.AuthenticationSerivice;

import javax.swing.*;

public class LoginUI {
    AuthenticationSerivice authenticationSerivice = new AuthenticationSerivice();



    public LoginUI(){
        JFrame frame = new JFrame("POS System");
        JLabel usernameLbl=new JLabel("Enter UserId : ");
        usernameLbl.setBounds(120,130,150,20);

        JLabel passwordLbl=new JLabel("Enter Password : ");
        passwordLbl.setBounds(120,180,150,20);

        JTextField usernameTf = new JTextField();
        usernameTf.setBounds(250,132,150,20);

        JPasswordField passwordTf = new JPasswordField();
        passwordTf.setBounds(250,182,150,20);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(200,220,100,20);

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
