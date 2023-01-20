package UI;

import domain.Users;
import repository.UserRepository;
import service.UserService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AddUserUI {

    public static void main(String[] args) {
        new AddUserUI();
    }

    UserService userService = new UserService();
    UserRepository userRepository = new UserRepository();
    public AddUserUI() {
        JFrame frame = new JFrame("POS System");
        JLabel userIdlbl=new JLabel("Enter User ID : ");
        userIdlbl.setBounds(135,130,150,20);

        JButton back = new JButton(new ImageIcon("src/Assets/back_8.png"));
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


        JButton addUser = new JButton("Add User");
        addUser.setBounds(200,382,100,20);

        JButton deleteUser = new JButton("Delete User");
        deleteUser.setBounds(320,382,120,20);



        addUser.addActionListener(e ->{

            Users user =new Users(userIdTF.getText(),userpassWordTf.getText(),userNameTf.getText(),userDesignationCb.getSelectedItem().toString(),UseremailTf.getText());
            if(userService.addUser(user)){
                JOptionPane.showMessageDialog(frame, "User added Successfully");
            }
            else{
                JOptionPane.showMessageDialog(frame, "User Cannot be added");
            }
        });

        deleteUser.addActionListener(e->{
            deleteUser();
            frame.dispose();

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
        frame.add(addUser);
        frame.add(deleteUser);
        frame.setSize(600,600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public void deleteUser() {
        JFrame deleteUserFrame = new JFrame("Delete User");

        JPanel userAreaPanel = new JPanel();

        JButton back = new JButton(new ImageIcon("src/Assets/back_8.png"));
        back.setBackground(new Color(1, 176, 222));
        back.setBounds(10, 2, 100, 50);
        back.addActionListener(e -> {
            new AddUserUI();
            deleteUserFrame.dispose();
        });

        JLabel deleteUserlogoLbl = new JLabel("Delete User");
        deleteUserlogoLbl.setFont(new Font("Calibri", Font.PLAIN, 50));
        deleteUserlogoLbl.setBounds(163, 5, 400, 50);
        deleteUserlogoLbl.setForeground(Color.orange);

        String[] UserColumns={"UserId","UserPass","UserName","UserDesignation","UserEmail","availabilty"};

        String[][]  dataFromDatabase = userService.getAllusersforJtable(UserColumns.length);

        DefaultTableModel UserDtm = new DefaultTableModel(dataFromDatabase,UserColumns);
       JTable userTable = new JTable(UserDtm);

        JScrollPane userSp = new JScrollPane(userTable);
        userSp.setBounds(10,60,580,500);

        JPanel optionPanel = new JPanel();
        optionPanel.setBackground(Color.GRAY);
        optionPanel.setBounds(440, 575, 430, 100);
        optionPanel.setBorder(BorderFactory.createLineBorder(Color.black, 10));
        optionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 10));

        JButton deleteUserBtn = new JButton("Delete");
        deleteUserBtn.setBounds(50,10,80,80);

        deleteUserBtn.addActionListener(e->{
            int index = userTable.getSelectedRow();
            if(index<0){
                JOptionPane.showMessageDialog(deleteUserFrame, "Please select Any product to Delete");
                return;
            }
            try{
                String userName= userTable.getValueAt(index, 2).toString();

                if(!userService.deleteUser(userName)){
                    JOptionPane.showMessageDialog(deleteUserFrame,"Deletion of the user is not allowed because it is being used by other transaction so we are deactivating !!");
                    DefaultTableModel afterDeleteDtm = new DefaultTableModel( userService.getAllusersforJtable(UserColumns.length),UserColumns);
                    userTable.setModel(afterDeleteDtm);
                }
                UserDtm.removeRow(index);

            } catch (ArrayIndexOutOfBoundsException exc){
            }

        });


        optionPanel.add(deleteUserBtn);

        userAreaPanel.add(userSp);
        userAreaPanel.add(deleteUserlogoLbl);
        userAreaPanel.setBounds(350,10,600,680);
        userAreaPanel.setBackground(Color.GRAY);
        userAreaPanel.setBorder(BorderFactory.createLineBorder(Color.black,10));
        userAreaPanel.setLayout(null);



        deleteUserFrame.add(optionPanel);
        deleteUserFrame.add(back);
        deleteUserFrame.add(userAreaPanel);
        deleteUserFrame.setSize(1500,730);
        deleteUserFrame.setLocationRelativeTo(null);
        deleteUserFrame.setLayout(null);
        deleteUserFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        deleteUserFrame.setVisible(true);
    }

    }


