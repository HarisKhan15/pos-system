package UI;

import repository.CategoryRepository;
import repository.VarientRepository;
import service.AllProductService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Arrays;

public class InventoryUI {
    public static void main(String[] args) {
        new InventoryUI();
    }
    AllProductService allProductService = new AllProductService();
    JTable productTable;
    String[] productColumn = {"Product Id","Product Name","Variant Id","Variant","Category","Price","Stock"};

    String[][] dataFromDatabase;
    public InventoryUI() {

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

        JLabel categorylogolbl = new JLabel("Inventory");
        categorylogolbl.setFont(new Font("Calibri", Font.PLAIN, 50));
        categorylogolbl.setBounds(163, 5, 400, 50);
        categorylogolbl.setForeground(Color.orange);



        dataFromDatabase = allProductService.getDataForTable(productColumn.length);

        DefaultTableModel productDtm = new DefaultTableModel(dataFromDatabase,productColumn);
        productTable = new JTable(productDtm);
        JScrollPane productSp = new JScrollPane(productTable);
        productSp.setBounds(10,60,580,500);

        JPanel optionPanel = new JPanel();
        optionPanel.setBackground(Color.GRAY);
        optionPanel.setBounds(440, 575, 430, 100);
        optionPanel.setBorder(BorderFactory.createLineBorder(Color.black, 10));
        optionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 10));

        JButton edit = new JButton("Edit price and quantity");

        edit.addActionListener(e -> {
            int index = productTable.getSelectedRow();

            if (index<0){
                JOptionPane.showMessageDialog(frame, "Please select Any row to update");
                return;
            }
            Object productId = productDtm.getValueAt(index, 0);
            Object price = productDtm.getValueAt(index, 5);
            Object quantity = productDtm.getValueAt(index, 6);

            updatePriceAndQuantity(price.toString(),productId.toString());


        });



        optionPanel.add(edit);

        categoryAreaPanel.add(productSp);
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

    public void updatePriceAndQuantity(String price,String productId){
        JFrame updateFrame = new JFrame("Make Changes");

        JLabel priceLbl = new JLabel("Price");
        priceLbl.setBounds(50,30,100,30);
        JTextField priceTf = new JTextField(price);
        priceTf.setBounds(150,30,80,30);

        JLabel amountlbl = new JLabel("Add Quantity");
        amountlbl.setBounds(40,60,100,30);
        JTextField quantityTf = new JTextField();
        quantityTf.setBounds(150,60,80,30);


        JButton update = new JButton("Update");
        update.setBounds(80,110,110,30);
        update.addActionListener(e->{
            String priceUpdated = priceTf.getText();
            String quantityUpdates = quantityTf.getText();
            if (allProductService.updateInventory(priceUpdated,quantityUpdates,productId)){

                JOptionPane.showMessageDialog(updateFrame, "Inventory updated succesfully");

                dataFromDatabase = allProductService.getDataForTable(productColumn.length);
                DefaultTableModel updateddtm = new DefaultTableModel(dataFromDatabase,productColumn);
                productTable.setModel(updateddtm);
                updateFrame.dispose();

            } else {
                JOptionPane.showMessageDialog(updateFrame, "Inventory not updated succesfully");
            }
        });


        updateFrame.add(update);
        updateFrame.add(priceTf);
        updateFrame.add(priceLbl);
        updateFrame.add(amountlbl);
        updateFrame.add(quantityTf);
        updateFrame.setSize(300,300);
        updateFrame.setLocationRelativeTo(null);
        updateFrame.setLayout(null);
        updateFrame.setVisible(true);
    }
}
