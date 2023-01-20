package UI;

import domain.AllProducts;
import domain.Users;
import repository.CartRepository;
import service.AllProductService;
import service.CategoryServices;
import service.VarientServices;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AddProductUI {
    public static void main(String[] args) {
        new AddProductUI();
    }

    AllProductService allProductService = new AllProductService();
    VarientServices varientServices= new VarientServices();
    CategoryServices categoryServices = new CategoryServices();
    private int flag =0;
    public AddProductUI() {
        JFrame frame = new JFrame("POS System");
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);


        JLabel logoLbl = new JLabel("Add Products");
        logoLbl.setFont(new Font("Calibri", Font.PLAIN, 50));
        logoLbl.setBounds(80,20,400,50);
        logoLbl.setForeground(Color.BLACK);



        JPanel productAddPanel = new JPanel();
        productAddPanel.setBounds(10,20,500,640);
        productAddPanel.setBackground(Color.lightGray);
        productAddPanel.setBorder(BorderFactory.createLineBorder(Color.black,10));
        productAddPanel.setLayout(null);
        productAddPanel.add(logoLbl);

        JPanel productAddPanel2 = new JPanel();
        productAddPanel2.setBounds(100,100,300,350);
        productAddPanel2.setBackground(Color.lightGray);
        productAddPanel2.setBorder(BorderFactory.createLineBorder(Color.black,10));
        productAddPanel2.setLayout(null);

        JLabel ProductNamelbl = new JLabel("Name");
        ProductNamelbl.setBounds(60,50,100,25);
        ProductNamelbl.setFont(new Font("Calibri", Font.PLAIN, 20));
        JTextField productNameTf = new JTextField();
        productNameTf.setBounds(130,50,100,25);


        JLabel productVarientCblbl = new JLabel("Varient");
        productVarientCblbl.setBounds(45,90,100,25);
        productVarientCblbl.setFont(new Font("Calibri", Font.PLAIN, 20));

        String varientList[]= varientServices.getAllVarientforDropDown();
        JComboBox varientCb = new JComboBox(varientList);
        varientCb.setBounds(130,90,100,25);
        varientCb.setFont(new Font("Calibri", Font.PLAIN, 20));

        JLabel productCategorylbl = new JLabel("Category");
        productCategorylbl.setBounds(30,130,100,25);
        productCategorylbl.setFont(new Font("Calibri", Font.PLAIN, 20));

        String catList[]=categoryServices.getAllCategoryforDropDown();
        JComboBox categoryCb = new JComboBox(catList);
        categoryCb.setBounds(130,133,100,25);
        categoryCb.setFont(new Font("Calibri", Font.PLAIN, 20));


        JLabel barCodeLbl = new JLabel("Bar Code");
        barCodeLbl.setBounds(30,173,100,25);
        barCodeLbl.setFont(new Font("Calibri", Font.PLAIN, 20));

        JTextField barCodeTf = new JTextField();
        barCodeTf.setBounds(130,173,100,25);

        JLabel quantitylbl = new JLabel("Quantity");
        quantitylbl.setBounds(30,207,100,25);
        quantitylbl.setFont(new Font("Calibri", Font.PLAIN, 20));

        JTextField quantityTf = new JTextField();
        quantityTf.setBounds(130,210,100,25);


        JLabel priceLbl = new JLabel("Price");
        priceLbl.setBounds(70,243,100,25);
        priceLbl.setFont(new Font("Calibri", Font.PLAIN, 20));

        JTextField priceTf = new JTextField();
        priceTf.setBounds(130,243,100,25);

        JButton addBtn = new JButton("Add");
        addBtn.setBounds(60,273,70,30);

        JButton editButton = new JButton("Edit");
        editButton.setBounds(160,273,70,30);

        productAddPanel.add(productAddPanel2);

        JPanel allProductsPanel = new JPanel();
        allProductsPanel.setBackground(Color.GRAY);
        allProductsPanel.setBounds(520,20,500,530);
        allProductsPanel.setBorder(BorderFactory.createLineBorder(Color.black,10));
        allProductsPanel.setLayout(null);

        JLabel allProductslbl = new JLabel("All Products");
        allProductslbl.setFont(new Font("Serif", Font.PLAIN, 30));
        allProductslbl.setBounds(160,5,300,50);



        String[] productColumn = {"Product Id","Product Name","VarientId","Variant","Category","Price","Stock"};
        String[][] dataFromDatabase = allProductService.getDataForTable(productColumn.length);
        DefaultTableModel productDtm = new DefaultTableModel(dataFromDatabase,productColumn);
       JTable productTable = new JTable(productDtm);
       JScrollPane productSp = new JScrollPane(productTable);
       productSp.setBounds(10,50,480,475);

        allProductsPanel.add(productSp);

        addBtn.addActionListener(e->{
            if((flag==0)){
                AllProducts allProducts = new AllProducts(productNameTf.getText(),varientCb.getSelectedItem().toString(),categoryCb.getSelectedItem().toString(),Double.valueOf(priceTf.getText()),Integer.valueOf(quantityTf.getText()));

                if(allProductService.checkProductAvailibility(productNameTf.getText())){
                    JOptionPane.showMessageDialog(frame, "Product is already available  we have activated!!");
                }
                else{
                    if (allProductService.addProductService(allProducts,categoryCb.getSelectedItem().toString(),varientCb.getSelectedItem().toString(),barCodeTf.getText(),Double.valueOf(priceTf.getText()),Double.valueOf(quantityTf.getText()))){
                        JOptionPane.showMessageDialog(frame, "Product saved Successfully");
                        String[][] dataFromDatabase1 = allProductService.getDataForTable(productColumn.length);
                        DefaultTableModel productDtm2 = new DefaultTableModel(dataFromDatabase1,productColumn);
                        productTable.setModel(productDtm2);


                    } else {
                        JOptionPane.showMessageDialog(frame, "Product not saved Successfully");
                    }

                }
            }

            else {
                int index=productTable.getSelectedRow();
                if (allProductService.updateProductService(Integer.valueOf(productDtm.getValueAt(index,0).toString()),productNameTf.getText(),categoryCb.getSelectedItem().toString(),varientCb.getSelectedItem().toString(),barCodeTf.getText(),Double.valueOf(priceTf.getText()),Double.valueOf(quantityTf.getText()))){
                    JOptionPane.showMessageDialog(frame, "Product updated Successfully");
                    String[][] dataFromDatabase1 = allProductService.getDataForTable(productColumn.length);
                    DefaultTableModel productDtm2 = new DefaultTableModel(dataFromDatabase1,productColumn);
                    productTable.setModel(productDtm2);
                    flag=0;

                }
                else {
                    JOptionPane.showMessageDialog(frame, "Product not updated Successfully");
                }
            }


        });

        editButton.addActionListener(e->{
            flag=1;
            int index=productTable.getSelectedRow();
            if(index<0){
                JOptionPane.showMessageDialog(frame, "Please select Any product to Edit");
                return;
            }
            String productName = productDtm.getValueAt(index,1).toString();
            productNameTf.setText(productName);
            String varientName = productDtm.getValueAt(index,3).toString();
            varientCb.setSelectedItem(varientName);
            String categoryName = productDtm.getValueAt(index,4).toString();
            categoryCb.setSelectedItem(categoryName);
            String barcode=allProductService.getBarcodeService(Integer.valueOf(productDtm.getValueAt(index,0).toString()));
            barCodeTf.setText(barcode);
            String price=productDtm.getValueAt(index,5).toString();
            priceTf.setText(price);
            String quantity=productDtm.getValueAt(index,6).toString();
            quantityTf.setText(quantity);
        });




        allProductsPanel.add(allProductslbl);

        //Bill
        JPanel productsOptionPnl = new JPanel();
        productsOptionPnl.setBackground(Color.GRAY);
        productsOptionPnl.setBounds(520,560,500,100);
        productsOptionPnl.setBorder(BorderFactory.createLineBorder(Color.black,10));
        productsOptionPnl.setLayout(new FlowLayout(FlowLayout.CENTER,25,10));

        JButton DeleteButton = new JButton("Delete Product");
        DeleteButton.addActionListener(e->{
            int index = productTable.getSelectedRow();
            if(index<0){
                JOptionPane.showMessageDialog(frame, "Please select Any product to Delete");
                return;
            }
            try{
                String toDeletValue = productTable.getValueAt(index, 1).toString();

                if(!allProductService.deleteProduct(toDeletValue)){
                    JOptionPane.showMessageDialog(frame,"Deletion of the product is not allowed because it is being used by other categories so we are deactivating it!!");
                    DefaultTableModel dtm3 = new DefaultTableModel(allProductService.getDataForTable(productColumn.length),productColumn);
                    productTable.setModel(dtm3);

                }
                productDtm.removeRow(index);
            } catch (ArrayIndexOutOfBoundsException exc){

            }

        });


        productsOptionPnl.add(DeleteButton);



        productAddPanel2.add(ProductNamelbl);
        productAddPanel2.add(productNameTf);
        productAddPanel2.add(productVarientCblbl);
        productAddPanel2.add(varientCb);
        productAddPanel2.add(productCategorylbl);
        productAddPanel2.add(categoryCb);
        productAddPanel2.add(barCodeLbl);
        productAddPanel2.add(barCodeTf);
        productAddPanel2.add(priceLbl);
        productAddPanel2.add(priceTf);
        productAddPanel2.add(quantitylbl);
        productAddPanel2.add(quantityTf);
        productAddPanel2.add(addBtn);
        productAddPanel2.add(editButton);



        frame.add(allProductsPanel);
        frame.add(productAddPanel);
        frame.add(productsOptionPnl);

        frame.setSize(1035,730);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
