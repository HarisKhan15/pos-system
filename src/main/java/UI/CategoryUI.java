package UI;

import domain.Category;
import repository.CategoryRepository;
import repository.VarientRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CategoryUI {
    public static void main(String[] args) {
        new CategoryUI();
    }

    public CategoryUI() {
        CategoryRepository categoryRepository = new CategoryRepository();
        JFrame frame = new JFrame("Category UI");

        JPanel categoryAreaPanel = new JPanel();

        JButton back = new JButton(new ImageIcon("src/Assets/back_8.png"));
        back.setBackground(new Color(1, 176, 222));
        back.setBounds(10, 2, 100, 50);
        back.addActionListener(e -> {
            new AdminUI();
            frame.dispose();
        });

        JLabel categorylogolbl = new JLabel("Categories");
        categorylogolbl.setFont(new Font("Calibri", Font.PLAIN, 50));
        categorylogolbl.setBounds(163, 5, 400, 50);
        categorylogolbl.setForeground(Color.orange);

        String[] column = {"ID", "NAME"};

        String[][] data = categoryRepository.getAllValueForJtabel(column.length);
        DefaultTableModel CategoryDtm = new DefaultTableModel(data, column);
        JTable categoryTable = new JTable(CategoryDtm);
        JScrollPane categorySp = new JScrollPane(categoryTable);
        categorySp.setBounds(10, 60, 580, 500);

        JPanel optionPanel = new JPanel();
        optionPanel.setBackground(Color.GRAY);
        optionPanel.setBounds(440, 575, 430, 100);
        optionPanel.setBorder(BorderFactory.createLineBorder(Color.black, 10));
        optionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 10));

        JButton addCategory = new JButton("Add Category");
        JButton deleteCategory= new JButton("Delete Category");
        JButton editCategory = new JButton("Edit Category");

        addCategory.addActionListener(e -> {
            new AddCategoryUI(null);
            frame.dispose();
        });

        editCategory.addActionListener(e -> {
            int index = categoryTable.getSelectedRow();

            if (index<0){
                JOptionPane.showMessageDialog(frame, "Please select Any category name to edit");
                return;
            }
            Object value = CategoryDtm.getValueAt(index, 1);
            new AddCategoryUI(value);
            frame.dispose();

        });

        deleteCategory.addActionListener(e->{

            int index = categoryTable.getSelectedRow();
            if(index<0){
                JOptionPane.showMessageDialog(frame, "Please select Any category name to Delete");
                return;
            }

            Object toDeletValue = CategoryDtm.getValueAt(index, 1);
            if(!categoryRepository.deleteCategoryByName(toDeletValue)){
                JOptionPane.showMessageDialog(frame,"Deletion of the item is not allowed because it is being used by other products so we are deactivating it!!");
                DefaultTableModel dtm2 = new DefaultTableModel(categoryRepository.getAllValueForJtabel(column.length),column);
                categoryTable.setModel(dtm2);
                return;
            }
            CategoryDtm.removeRow(index);
        });


        optionPanel.add(addCategory);
        optionPanel.add(deleteCategory);
        optionPanel.add(editCategory);

        categoryAreaPanel.add(categorySp);
        categoryAreaPanel.add(categorylogolbl);
        categoryAreaPanel.setBounds(350,10,600,680);
        categoryAreaPanel.setBackground(Color.GRAY);
        categoryAreaPanel.setBorder(BorderFactory.createLineBorder(Color.black,10));
        categoryAreaPanel.setLayout(null);



        frame.add(optionPanel);
        frame.add(back);
        frame.add(categoryAreaPanel);
        frame.setSize(1500,730);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
