package UI;

import javax.swing.*;

public class LoginUI {

    public LoginUI(){
        JFrame frame = new JFrame("POS System");
        JLabel usernameLbl=new JLabel("Enter Username : ");
        usernameLbl.setBounds(120,130,150,20);

        JLabel passwordLbl=new JLabel("Enter Password : ");
        passwordLbl.setBounds(120,180,150,20);

        JTextField usernameTf = new JTextField();
        usernameTf.setBounds(250,132,150,20);

        JTextField passwordTf = new JTextField();
        passwordTf.setBounds(250,182,150,20);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(200,220,100,20);

        loginBtn.addActionListener(e ->{

            if(usernameTf.getText().equalsIgnoreCase("admin")&&passwordTf.getText().equalsIgnoreCase("admin")){
                JOptionPane.showMessageDialog(frame,"sami successful");
                frame.dispose();
                new UserTransactionUI();
            }
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
