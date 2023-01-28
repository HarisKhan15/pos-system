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
import java.awt.event.MouseEvent;

public class AddProductUI {
    public static void main(String[] args) {
        new AddProductUI();
    }

    AllProductService allProductService = new AllProductService();
    VarientServices varientServices= new VarientServices();
    CategoryServices categoryServices = new CategoryServices();
    private int flag =0;
    String[] productColumn = {"Product Id","Product Name","VarientId","Variant","Category","Price","Stock"};
  private   String[][] dataFromDatabase;
  private   DefaultTableModel productDtm;
  private   JTable productTable;
   private JScrollPane productSp;
    public AddProductUI() {
        JFrame frame = new JFrame("POS System");
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);


        JLabel logoLbl = new JLabel("All Products");
        logoLbl.setFont(new Font("Calibri", Font.PLAIN, 50));
        logoLbl.setBounds(120,20,300,50);
        logoLbl.setForeground(Color.ORANGE);

        JLabel logoLbl2 = new JLabel("Add Products");
        logoLbl2.setFont(new Font("Calibri", Font.PLAIN, 50));
        logoLbl2.setBounds(60,20,350,50);
        logoLbl2.setForeground(Color.ORANGE);


        JTextField searchBarTf = new JTextField();
        searchBarTf.setText("Search By name");
        searchBarTf.setBounds(30,100,350,20);

        JTextField searchByBarcode = new JTextField();
        searchByBarcode.setText("Search BY Barcode");

        searchByBarcode.setBounds(30,125,350,20);

        JButton searchBtn = new JButton("Search");
        searchBtn.setBounds(390,98,95,25);


        JPanel ProductAddPanel = new JPanel();
        ProductAddPanel.setBackground(Color.gray);
        ProductAddPanel.setBounds(550,20,430,530);
        ProductAddPanel.setBorder(BorderFactory.createLineBorder(Color.black,10));
        ProductAddPanel.setLayout(null);


        JPanel productAddPanel2 = new JPanel();
        productAddPanel2.setBounds(60,100,300,350);
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
        varientCb.setFont(new Font("Calibri", Font.PLAIN, 15));

        JLabel productCategorylbl = new JLabel("Category");
        productCategorylbl.setBounds(30,130,100,25);
        productCategorylbl.setFont(new Font("Calibri", Font.PLAIN, 20));

        String catList[]=categoryServices.getAllCategoryforDropDown();
        JComboBox categoryCb = new JComboBox(catList);
        categoryCb.setBounds(130,133,100,25);
        categoryCb.setFont(new Font("Calibri", Font.PLAIN, 15));


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

        JPanel allProductsPanel = new JPanel();
        allProductsPanel.setBounds(40,22,500,640);
        allProductsPanel.setBackground(Color.GRAY);
        allProductsPanel.setBorder(BorderFactory.createLineBorder(Color.black,10));
        allProductsPanel.setLayout(null);
        allProductsPanel.add(logoLbl);


        JPanel productsOptionPnl = new JPanel();
        productsOptionPnl.setBackground(Color.GRAY);
        productsOptionPnl.setBounds(550,560,430,100);
        productsOptionPnl.setBorder(BorderFactory.createLineBorder(Color.black,10));
        productsOptionPnl.setLayout(new FlowLayout(FlowLayout.CENTER,25,10));

        JButton DeleteButton = new JButton("Delete Product");
        JButton backBtn = new JButton("Back");

         dataFromDatabase = allProductService.getValuesForJTable(productColumn.length);
         productDtm = new DefaultTableModel(dataFromDatabase,productColumn);
        productTable = new JTable(productDtm);
        productSp = new JScrollPane(productTable);
       productSp.setBounds(10,155,478,473);



        addBtn.addActionListener(e->{
            if((flag==0)){
                AllProducts allProducts = new AllProducts(productNameTf.getText(),varientCb.getSelectedItem().toString()
                        ,categoryCb.getSelectedItem().toString(),Double.valueOf(priceTf.getText())
                        ,Integer.valueOf(quantityTf.getText()));

                if(allProductService.checkProductAvailibility(productNameTf.getText(),categoryCb.getSelectedItem().toString(),varientCb.getSelectedItem().toString())){
                    JOptionPane.showMessageDialog(frame, "Product is already available  we have activated!!");
                    dataFromDatabase = allProductService.getValuesForJTable(productColumn.length);
                    productDtm = new DefaultTableModel(dataFromDatabase,productColumn);
                    productTable.setModel(productDtm);
                }
                else{
                    if (allProductService.addProductService(allProducts,categoryCb.getSelectedItem().toString(),varientCb.getSelectedItem().toString(),barCodeTf.getText(),Double.valueOf(priceTf.getText()),Double.valueOf(quantityTf.getText()))){
                        JOptionPane.showMessageDialog(frame, "Product saved Successfully");
                        dataFromDatabase = allProductService.getValuesForJTable(productColumn.length);
                        productDtm = new DefaultTableModel(dataFromDatabase,productColumn);
                        productTable.setModel(productDtm);


                    } else {
                        JOptionPane.showMessageDialog(frame, "Product not saved Successfully");
                    }

                }
                productNameTf.setText("");
                barCodeTf.setText("");
                quantityTf.setText("");
                priceTf.setText("");


            }

            else {
                int index=productTable.getSelectedRow();
                if (allProductService.updateProductService(Integer.valueOf(productDtm.getValueAt(index,0).toString()),productNameTf.getText(),categoryCb.getSelectedItem().toString(),varientCb.getSelectedItem().toString(),barCodeTf.getText(),Double.valueOf(priceTf.getText()),Double.valueOf(quantityTf.getText()))){
                    JOptionPane.showMessageDialog(frame, "Product updated Successfully");
                    dataFromDatabase = allProductService.getValuesForJTable(productColumn.length);
                    productDtm = new DefaultTableModel(dataFromDatabase,productColumn);
                    productTable.setModel(productDtm);
                    flag=0;

                }
                else {
                    JOptionPane.showMessageDialog(frame, "Product not updated Successfully");
                }
            }
            productNameTf.setText("");
            barCodeTf.setText("");
            quantityTf.setText("");
            priceTf.setText("");
            dataFromDatabase = allProductService.getValuesForJTable(productColumn.length);
            productDtm = new DefaultTableModel(dataFromDatabase,productColumn);
            productTable.setModel(productDtm);

        });

        editButton.addActionListener(e->{
            int index=productTable.getSelectedRow();
            flag=1;

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

        DeleteButton.addActionListener(e->{
            int index = productTable.getSelectedRow();
            if(index<0){
                JOptionPane.showMessageDialog(frame, "Please select Any product to Delete");
                return;
            }
            try{
                String productName = productTable.getValueAt(index, 1).toString();
                String varientName = productTable.getValueAt(index, 3).toString();
                String categoryName = productTable.getValueAt(index, 4).toString();

                if(!allProductService.deleteProduct(productName,varientName,categoryName)){
                    JOptionPane.showMessageDialog(frame,"Deletion of the product is not allowed because it is being used by other categories so we are deactivating it!!");
                }
                 productDtm = new DefaultTableModel(allProductService.getValuesForJTable(productColumn.length),productColumn);
                productTable.setModel(productDtm);
            } catch (ArrayIndexOutOfBoundsException exc){
                exc.printStackTrace();
            }

        });
        backBtn.addActionListener(e->{
            new AdminUI();
            frame.dispose();
        });

        searchBtn.addActionListener(e->{
                String[][] searchRecords = allProductService.getBySearch(productColumn.length,searchBarTf.getText());
                DefaultTableModel dtm2 = new DefaultTableModel(searchRecords,productColumn);
                productTable.setModel(dtm2);
                productTable.getColumnModel().getColumn(0).setPreferredWidth(0);
                productTable.getColumnModel().getColumn(1).setPreferredWidth(25);
                productTable.getColumnModel().getColumn(3).setPreferredWidth(25);

        });
        allProductsPanel.add(productSp);

        productsOptionPnl.add(DeleteButton);
        productsOptionPnl.add(backBtn);


        allProductsPanel.add(searchBarTf);
        allProductsPanel.add(searchByBarcode);
        allProductsPanel.add(searchBtn);

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




        ProductAddPanel.add(logoLbl2);
        ProductAddPanel.add(productAddPanel2);
        frame.add(allProductsPanel);
        frame.add(ProductAddPanel);
        frame.add(productsOptionPnl);



        frame.setSize(1035,730);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
