package UI;

import repository.VarientRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VariantsUI {
    public static void main(String[] args) {
        new VariantsUI();

    }
    Integer flag=null;
    public VariantsUI() {
        VarientRepository varientRepository = new VarientRepository();
       JFrame frame = new JFrame("Products");

        JPanel variantAreaPanel = new JPanel();

        JButton back = new JButton(new ImageIcon("/home/murtaza/Desktop/POS PROJ/pos-system/src/Assets/back_8.png"));
        back.setBackground(new Color(1, 176, 222));
        back.setBounds(10,2,100,50);
        back.addActionListener(e->{
            new AdminUI();
            frame.dispose();
        });

        JLabel Varientlogolbl= new JLabel("Variants");
        Varientlogolbl.setFont(new Font("Calibri", Font.PLAIN, 50));
        Varientlogolbl.setBounds(163,5,400,50);
        Varientlogolbl.setForeground(Color.orange);

        String[] column ={"ID","NAME"};

        String[][] data= varientRepository.getAllValueForJtabel(column.length);
        DefaultTableModel transactionsDtm = new DefaultTableModel(data,column);
        JTable transactionsTable = new JTable(transactionsDtm);
        JScrollPane transactionsSp = new JScrollPane(transactionsTable);
        transactionsSp.setBounds(10,60,580,500);

        JPanel optionPanel = new JPanel();
        optionPanel.setBackground(Color.GRAY);
        optionPanel.setBounds(440,575,430,100);
        optionPanel.setBorder(BorderFactory.createLineBorder(Color.black,10));
        optionPanel.setLayout(new FlowLayout(FlowLayout.CENTER,25,10));

        JButton addVarient = new JButton("Add Varient");
        JButton deleteVarient = new JButton("Delete Varient");
        JButton editVarient= new JButton("Edit Varient");

        addVarient.addActionListener(e->{
            new AddVarientUI(null);
            frame.dispose();
        });

        editVarient.addActionListener(e->{
            int index=transactionsTable.getSelectedRow();
            Object value=transactionsDtm.getValueAt(index,1);
            new AddVarientUI(value);
            frame.dispose();

        });

        optionPanel.add(addVarient);
        optionPanel.add(deleteVarient);
        optionPanel.add(editVarient);

        variantAreaPanel.add(transactionsSp);
        variantAreaPanel.add(Varientlogolbl);
        variantAreaPanel.setBounds(350,10,600,680);
        variantAreaPanel.setBackground(Color.GRAY);
        variantAreaPanel.setBorder(BorderFactory.createLineBorder(Color.black,10));
        variantAreaPanel.setLayout(null);



        frame.add(optionPanel);
        frame.add(back);
        frame.add(variantAreaPanel);
        frame.setSize(1500,730);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
