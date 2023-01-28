package UI;

import domain.Users;
import repository.UserRepository;
import service.UserService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AddUserUI {

    private    JLabel userIdlbl,passwordLbl,usernameLbl,userEmailLbl, confirmPasswordlbl,userDesignationLbl;
    private   JTextField userIdTF,userpassWordTf,userNameTf,confirmPasswordTf,useremailTf;
    private DefaultTableModel userDtm;
    private  JTable userTable;
    private JScrollPane userSp;
    private String[][] dataFromDatabase;
    JComboBox userDesignationCb;
    private Integer flag=0;
    private   String[] UserColumns = {"UserId", "UserName", "UserDesignation", "UserEmail"};
    private String Designations[] = {"admin", "staff"};

    public static void main(String[] args) {
        new AddUserUI();
    }
    UserService userService = new UserService();
    public AddUserUI() {
        JFrame userFrame = new JFrame("Add/Delete Users");

        JPanel userAreaPanel = new JPanel();

        JButton back = new JButton(new ImageIcon("src/Assets/back_8.png"));
        back.setBackground(new Color(1, 176, 222));
        back.setBounds(10, 2, 100, 50);
        back.addActionListener(e -> {
            new AdminUI();
            userFrame.dispose();
        });

        JLabel userLogolbl = new JLabel("Users");
        userLogolbl.setFont(new Font("Calibri", Font.PLAIN, 50));
        userLogolbl.setBounds(183, 5, 400, 50);
        userLogolbl.setForeground(Color.orange);

        dataFromDatabase = userService.getAllusersforJtable(UserColumns.length);
         userDtm = new DefaultTableModel(dataFromDatabase, UserColumns);
         userTable = new JTable(userDtm);

         userSp = new JScrollPane(userTable);
        userSp.setBounds(10, 60, 580, 500);

        JPanel optionPanel = new JPanel();
        optionPanel.setBackground(Color.GRAY);
        optionPanel.setBounds(440, 575, 430, 100);
        optionPanel.setBorder(BorderFactory.createLineBorder(Color.black, 10));
        optionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 10));

        JButton addUserBtn = new JButton("Add User");
        addUserBtn.setBounds(50, 10, 80, 80);

        JButton deleteUserBtn = new JButton("Delete User");
        deleteUserBtn.setBounds(70, 10, 80, 80);


        JButton editUserBtn = new JButton("Edit User");
        deleteUserBtn.setBounds(70, 10, 80, 80);

        deleteUserBtn.addActionListener(e -> {
            int index = userTable.getSelectedRow();
            if (index < 0) {
                JOptionPane.showMessageDialog(userFrame, "Please select Any User to Delete");
                return;
            }
            try {
                String userId = userTable.getValueAt(index, 0).toString();

                if (userService.deleteUser(userId)) {
                    JOptionPane.showMessageDialog(userFrame, "Deletion of the user is not allowed because it is being used by other transaction so we are deactivating !!");
                    DefaultTableModel afterDeleteDtm = new DefaultTableModel(userService.getAllusersforJtable(UserColumns.length), UserColumns);
                    userTable.setModel(afterDeleteDtm);
                }
                userDtm.removeRow(index);

            } catch (ArrayIndexOutOfBoundsException exc) {
            }

        });

        addUserBtn.addActionListener(e->{
            addUser(null,null,null,null,null);
            userFrame.dispose();
        });
        editUserBtn.addActionListener(e->{
            int index = userTable.getSelectedRow();

            if (index<0){
                JOptionPane.showMessageDialog(userFrame, "Please select Any row to update");
                return;
            }
            Object userId = userTable.getValueAt(index, 0);
            Object userName = userTable.getValueAt(index, 1);
            Object userEmail=userTable.getValueAt(index,3).toString();
            Object userpass = userService.getUserPassword(userId.toString());
            addUser(userId.toString(),userName.toString(),userpass.toString(),Designations,userEmail.toString());
            userFrame.dispose();
        });

        optionPanel.add(deleteUserBtn);
        optionPanel.add(addUserBtn);
        optionPanel.add(editUserBtn);

        userAreaPanel.add(userSp);
        userAreaPanel.add(userLogolbl);
        userAreaPanel.setBounds(350, 10, 600, 680);
        userAreaPanel.setBackground(Color.GRAY);
        userAreaPanel.setBorder(BorderFactory.createLineBorder(Color.black, 10));
        userAreaPanel.setLayout(null);


        userFrame.add(optionPanel);
        userFrame.add(back);
        userFrame.add(userAreaPanel);
        userFrame.setSize(1500, 730);
        userFrame.setLocationRelativeTo(null);
        userFrame.setLayout(null);
        userFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        userFrame.setVisible(true);

    }
    public void addUser(String userId,String userName,String userPassword,String designations[],String userEmail) {

        JFrame frame = new JFrame("POS System");
        JButton back = new JButton(new ImageIcon("src/Assets/back_8.png"));
        back.setBackground(new Color(1, 176, 222));
        back.setBounds(10, 2, 100, 50);
        back.addActionListener(e -> {
            new AddUserUI();
            frame.dispose();
        });

        if (userId != null) {

            JLabel userIdlbl1 = new JLabel(" User ID : ");
            userIdlbl1.setBounds(135, 110, 150, 20);

             userIdlbl = new JLabel(userId);
            userIdlbl.setBounds(250, 110, 200, 30);
            userIdlbl.setFont(new Font("Calibri", Font.PLAIN, 30));

            passwordLbl = new JLabel("Edit User Password : ");
            passwordLbl.setBounds(90, 150, 170, 20);

            confirmPasswordlbl= new JLabel("Confirm Password : ");
            confirmPasswordlbl.setBounds(100, 190, 170, 20);

            usernameLbl = new JLabel("Edit Name : ");
            usernameLbl.setBounds(145, 230, 150, 20);

            userEmailLbl = new JLabel("Edit Email : ");
            userEmailLbl.setBounds(150, 280, 150, 20);

            userDesignationLbl = new JLabel("Edit Designation : ");
            userDesignationLbl.setBounds(100, 330, 150, 20);


            userpassWordTf = new JTextField(userPassword);
            userpassWordTf.setBounds(250, 152, 150, 20);

            confirmPasswordTf= new JTextField();
            confirmPasswordTf.setBounds(250, 190, 150, 20);


            userNameTf = new JTextField(userName);
            userNameTf.setBounds(250, 232, 150, 20);

            useremailTf = new JTextField(userEmail);
            useremailTf.setBounds(250, 282, 150, 20);

             userDesignationCb=new JComboBox(designations);
            userDesignationCb.setBounds(250, 332, 150, 20);
            frame.add(userIdlbl1);
            frame.add(confirmPasswordTf);
            flag=1;
        }
        else{
            userIdlbl = new JLabel("Enter User ID : ");
            userIdlbl.setBounds(135, 110, 150, 20);

            passwordLbl = new JLabel("Enter User Password : ");
            passwordLbl.setBounds(80, 150, 170, 20);

            confirmPasswordlbl= new JLabel("Confirm Password : ");
            confirmPasswordlbl.setBounds(100, 190, 170, 20);

            usernameLbl = new JLabel("Enter Name : ");
            usernameLbl.setBounds(145, 230, 150, 20);

            userEmailLbl = new JLabel("Enter Email : ");
            userEmailLbl.setBounds(150, 280, 150, 20);

            userDesignationLbl = new JLabel("Enter Designation : ");
            userDesignationLbl.setBounds(100, 330, 150, 20);

             userIdTF = new JTextField();
            userIdTF.setBounds(250, 112, 150, 20);

             userpassWordTf = new JTextField();
            userpassWordTf.setBounds(250, 152, 150, 20);

            confirmPasswordTf= new JTextField();
            confirmPasswordTf.setBounds(250, 190, 150, 20);



             userNameTf = new JTextField();
            userNameTf.setBounds(250, 232, 150, 20);

             useremailTf = new JTextField();
            useremailTf.setBounds(250, 282, 150, 20);

             userDesignationCb=new JComboBox(Designations);
            userDesignationCb.setBounds(250, 332, 150, 20);

            frame.add(userIdTF);

        }

        JButton addUser = new JButton("Add User");
        addUser.setBounds(200, 382, 100, 20);

        addUser.addActionListener(e -> {
            if (userpassWordTf.getText().equals(confirmPasswordTf.getText())){
                if(flag==0){
                    Users user = new Users(userIdTF.getText(), userpassWordTf.getText(), userNameTf.getText(),userDesignationCb.getSelectedItem().toString(), useremailTf.getText());
                    if (userService.checkUserAvailibility(userIdTF.getText())) {
                        JOptionPane.showMessageDialog(frame, "User is already available !!");
                        return;
                    }

                    if (userService.addUser(user)) {
                        JOptionPane.showMessageDialog(frame, "User added Successfully");
                        dataFromDatabase = userService.getAllusersforJtable(UserColumns.length);
                        userDtm = new DefaultTableModel(dataFromDatabase, UserColumns);
                        userTable = new JTable(userDtm);
                        userSp = new JScrollPane(userTable);
                        new AddUserUI();
                    } else {
                        JOptionPane.showMessageDialog(frame, "User Cannot be added");
                        frame.dispose();
                    }
                }else {
                    int index=userTable.getSelectedRow();
                    if (userService.updateUser(userTable.getValueAt(index,0).toString(),userpassWordTf.getText(),userNameTf.getText(),useremailTf.getText(),userDesignationCb.getSelectedItem().toString())){
                        JOptionPane.showMessageDialog(frame, "User updated Successfully");
                        dataFromDatabase = userService.getAllusersforJtable(UserColumns.length);
                        userDtm = new DefaultTableModel(dataFromDatabase, UserColumns);
                        userTable = new JTable(userDtm);
                        userSp = new JScrollPane(userTable);
                        flag=0;
                        new AddUserUI();
                    }
                    else {
                        JOptionPane.showMessageDialog(frame, "User not updated Successfully");
                    }
                }

                dataFromDatabase = userService.getAllusersforJtable(UserColumns.length);
                userDtm = new DefaultTableModel(dataFromDatabase, UserColumns);
                userTable = new JTable(userDtm);
                userSp = new JScrollPane(userTable);

            }
            else {
                JOptionPane.showMessageDialog(frame, "Passwords dosn't match");
            }

        });


        frame.add(confirmPasswordlbl);
        frame.add(confirmPasswordTf);
        frame.add(back);
        frame.add(userIdlbl);
        frame.add(passwordLbl);
        frame.add(usernameLbl);
        frame.add(userEmailLbl);
        frame.add(userpassWordTf);
        frame.add(userNameTf);
        frame.add(useremailTf);
        frame.add(userDesignationLbl);
        frame.add(userDesignationCb);
        frame.add(addUser);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}


