package UI;

import com.sun.scenario.effect.impl.sw.java.JSWBlend_SRC_OVERPeer;
import repository.VarientRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;

public class VariantsUI {
    public static void main(String[] args) {
        new VariantsUI();
    }
    public VariantsUI() {
        VarientRepository varientRepository = new VarientRepository();
        JFrame frame = new JFrame("Products");

        JPanel variantAreaPanel = new JPanel();

        JButton back = new JButton(new ImageIcon("src/Assets/back_8.png"));
        back.setBackground(new Color(1, 176, 222));
        back.setBounds(10, 2, 100, 50);
        back.addActionListener(e -> {
            new AdminUI();
            frame.dispose();
        });

        JLabel Varientlogolbl = new JLabel("Variants");
        Varientlogolbl.setFont(new Font("Calibri", Font.PLAIN, 50));
        Varientlogolbl.setBounds(163, 5, 400, 50);
        Varientlogolbl.setForeground(Color.orange);

        String[] column = {"ID", "NAME"};

        String[][] data = varientRepository.getAllValueForJtabel(column.length);
        DefaultTableModel transactionsDtm = new DefaultTableModel(data, column);
        JTable transactionsTable = new JTable(transactionsDtm);
        JScrollPane transactionsSp = new JScrollPane(transactionsTable);
        transactionsSp.setBounds(10, 60, 580, 500);

        JPanel optionPanel = new JPanel();
        optionPanel.setBackground(Color.GRAY);
        optionPanel.setBounds(440, 575, 430, 100);
        optionPanel.setBorder(BorderFactory.createLineBorder(Color.black, 10));
        optionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 10));

        JButton addVarient = new JButton("Add Varient");
        JButton deleteVarient = new JButton("Delete Varient");
        JButton editVarient = new JButton("Edit Varient");

        addVarient.addActionListener(e -> {
            new AddVarientUI(null);
            frame.dispose();
        });

    editVarient.addActionListener(e -> {
        int index = transactionsTable.getSelectedRow();

        if (index<0){
            JOptionPane.showMessageDialog(frame, "Please select Any item to edit");
            return;
        }
        Object value = transactionsDtm.getValueAt(index, 1);
        frame.dispose();
        new AddVarientUI(value);


    });

    deleteVarient.addActionListener(e->{

        int index = transactionsTable.getSelectedRow();
        if(index<0){
            JOptionPane.showMessageDialog(frame, "Please select Any item to Delete");
            return;
        }
        try{
            Object toDeletValue = transactionsTable.getValueAt(index, 1);

            if(!varientRepository.deleteVariantByName(toDeletValue)){
                JOptionPane.showMessageDialog(frame,"Deletion of the item is not allowed because it is being used by other products so we are deactivating it!!");
                DefaultTableModel dtm2 = new DefaultTableModel(varientRepository.getAllValueForJtabel(column.length),column);
                transactionsTable.setModel(dtm2);

            }
            transactionsDtm.removeRow(index);
        } catch (ArrayIndexOutOfBoundsException exc){

        }
;
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
