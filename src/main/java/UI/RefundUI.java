package UI;

import domain.AllProducts;
import domain.Cart;
import repository.CartRepository;
import service.AllProductService;
import service.CartService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;

public class RefundUI {
    CartService cartService = new CartService();
    AllProductService allProductService = new AllProductService();
    String[][] newCartData;
    JTable cartTable;
    String[] cartColumn = {"Product Name","Product Variant","Product Category","Unit Price","Quantity","Price"};
    JLabel updatedBillAmountLbl;
    Double oldBill;
    Integer transactionId;
    int barcount = 0;

    public static void main(String[] args) {
        new RefundUI("asd");
    }
    public RefundUI(String userId){

        JFrame frame = new JFrame("POS System");
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);

        //Main panel
        JPanel refundPnl = new JPanel();
        refundPnl.setBounds(40,20,940,640);
        refundPnl.setBackground(Color.GRAY);
        refundPnl.setBorder(BorderFactory.createLineBorder(Color.black,10));
        refundPnl.setLayout(null);

        JLabel logoLbl = new JLabel("REFUND");
        logoLbl.setFont(new Font("Calibri", Font.PLAIN, 50));
        logoLbl.setBounds(390,20,300,50);
        logoLbl.setForeground(Color.orange);

        JButton backBtn = new JButton("Back");
        backBtn.setBounds(50,30,100,30);

        JLabel transactionIdLbl = new JLabel("Enter Transaction Id : ");
        transactionIdLbl.setBounds(40,150,250,30);
        transactionIdLbl.setFont(new Font("Serif", Font.PLAIN, 20));

        JTextField searchBarTf = new JTextField();
        searchBarTf.setBounds(230,155,230,25);

        JButton searchTransactionBtn = new JButton("Search Transaction");
        searchTransactionBtn.setBounds(160,200,150,30);

        //Cart inside the Main Panel
        JPanel cartPnl = new JPanel();
        cartPnl.setBackground(Color.lightGray);
        cartPnl.setBounds(480,85,430,530);
        cartPnl.setBorder(BorderFactory.createLineBorder(Color.black,5));
        cartPnl.setLayout(null);

        JLabel cartLbl = new JLabel("Your Cart");
        cartLbl.setFont(new Font("Serif", Font.PLAIN, 30));
        cartLbl.setBounds(160,10,300,50);

        String[][] cartData = CartRepository.getAllCartDataForJTable(cartColumn.length);
        DefaultTableModel cartDtm = new DefaultTableModel(cartData, cartColumn);
        cartTable = new JTable(cartDtm);
        JScrollPane cartSp = new JScrollPane(cartTable);
        cartSp.setBounds(18,70,395,380);

        JButton addNewProductBtn = new JButton("Add New Product");
        addNewProductBtn.setBounds(30,470,150,30);

        JButton addProductBtn = new JButton("Add");
        addProductBtn.setBounds(250,470,70,30);

        JButton deleteProductBtn = new JButton("Delete");
        deleteProductBtn.setBounds(340,470,70,30);


        cartPnl.add(cartLbl);
        cartPnl.add(cartSp);
        cartPnl.add(addNewProductBtn);
        cartPnl.add(addProductBtn);
        cartPnl.add(deleteProductBtn);
        //Cart Panel UI ends

        JLabel totalBillLbl = new JLabel("Old Bill : ");
        totalBillLbl.setBounds(50,283,150,20);
        totalBillLbl.setFont(new Font("Serif", Font.PLAIN, 20));

        oldBill = cartService.getTotalBill();
        JLabel totalBillAmountLbl = new JLabel(oldBill.toString());
        totalBillAmountLbl.setBounds(160,280,100,30);
        totalBillAmountLbl.setFont(new Font("Serif", Font.PLAIN, 20));
        totalBillAmountLbl.setBorder(BorderFactory.createLineBorder(Color.black,2));
        totalBillAmountLbl.setBackground(Color.WHITE);
        totalBillAmountLbl.setOpaque(true);

        JLabel updatedBillLbl = new JLabel("Updated Bill : ");
        updatedBillLbl.setBounds(40,348,150,30);
        updatedBillLbl.setFont(new Font("Serif", Font.PLAIN, 20));

        updatedBillAmountLbl = new JLabel(cartService.getTotalBill().toString());
        updatedBillAmountLbl.setBounds(160,350,100,30);
        updatedBillAmountLbl.setFont(new Font("Serif", Font.PLAIN, 20));
        updatedBillAmountLbl.setBorder(BorderFactory.createLineBorder(Color.black,2));
        updatedBillAmountLbl.setBackground(Color.WHITE);
        updatedBillAmountLbl.setOpaque(true);

        JButton completeRefundBtn = new JButton("Complete Refund");
        completeRefundBtn.setBounds(50,450,150,30);

        JButton cancelRefundBtn = new JButton("Cancel Refund");
        cancelRefundBtn.setBounds(250,450,150,30);

        refundPnl.add(backBtn);
        refundPnl.add(transactionIdLbl);
        refundPnl.add(searchBarTf);
        refundPnl.add(searchTransactionBtn);
        refundPnl.add(totalBillLbl);
        refundPnl.add(totalBillAmountLbl);
        refundPnl.add(updatedBillLbl);
        refundPnl.add(updatedBillAmountLbl);
        refundPnl.add(completeRefundBtn);
        refundPnl.add(cancelRefundBtn);
        refundPnl.add(cartPnl);
        refundPnl.add(logoLbl);


        //Button Config
        backBtn.addActionListener(e->{
            cartService.clearList();
            frame.dispose();
            new UserTransactionUI(userId);
        });
        searchTransactionBtn.addActionListener(e ->{
            if(searchBarTf.getText().isEmpty()){
                JOptionPane.showMessageDialog(frame,"Please Enter Transaction Id !!");
                return;
            }
            CartRepository.clearList();
            String[][] refundedData = cartService.getDataForRefund(Integer.parseInt(searchBarTf.getText()), cartColumn.length);
            DefaultTableModel dtm2 = new DefaultTableModel(refundedData,cartColumn);
            cartTable.setModel(dtm2);
            transactionId = Integer.valueOf(searchBarTf.getText());
            searchBarTf.setText(null);
            oldBill = cartService.getTotalBill();
            totalBillAmountLbl.setText(oldBill.toString());
            updatedBillAmountLbl.setText(cartService.getTotalBill().toString());

        });

        deleteProductBtn.addActionListener(e ->{
            if(cartTable.getSelectedRow()<0){
                JOptionPane.showMessageDialog(frame,"Please Select any Item to delete!");
                return;
            }
            cartService.deleteItemRefund(cartTable.getSelectedRow(),transactionId);
            String[][] newCartData = CartRepository.getAllCartDataForJTable(cartColumn.length);
            DefaultTableModel dtm2 = new DefaultTableModel(newCartData,cartColumn);
            cartTable.setModel(dtm2);
            updatedBillAmountLbl.setText(cartService.getTotalBill().toString());
        });

        addProductBtn.addActionListener(e->{
            if(cartTable.getSelectedRow()<0){
                JOptionPane.showMessageDialog(frame,"Please Select any Item to Add!");
                return;
            }
            if(!cartService.addItemRefund(cartTable.getSelectedRow())){
                JOptionPane.showMessageDialog(frame,"Max Quantity!");
                return;
            }
            newCartData = CartRepository.getAllCartDataForJTable(cartColumn.length);
            DefaultTableModel dtm2 = new DefaultTableModel(newCartData,cartColumn);
            cartTable.setModel(dtm2);
            updatedBillAmountLbl.setText(cartService.getTotalBill().toString());
        });
        addNewProductBtn.addActionListener(e->{
            if (oldBill==0){
                JOptionPane.showMessageDialog(frame,"Select Transaction for Refund!");
                return;
            }
            addNewProductUI();
        });
        completeRefundBtn.addActionListener(e->{
            if(CartRepository.getAll().isEmpty()){
                JOptionPane.showMessageDialog(frame,"Cart is Empty !!");
                return;
            }
            completeRefund(userId);
            frame.dispose();
        });
        cancelRefundBtn.addActionListener(e->{
            CartRepository.removeAllData();
            frame.dispose();
            new RefundUI(userId);
        });
        // Adding panel
        frame.add(refundPnl);
        frame.setSize(1035,730);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public void addNewProductUI(){

        JFrame frame = new JFrame("POS System");
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);

        // All product panel
        JPanel allProductPnl = new JPanel();
        allProductPnl.setBackground(Color.GRAY);
        allProductPnl.setBounds(20,20,650,480);
        allProductPnl.setBorder(BorderFactory.createLineBorder(Color.black,5));
        allProductPnl.setLayout(null);

        JLabel allProductLbl = new JLabel("All Product");
        allProductLbl.setFont(new Font("Serif", Font.PLAIN, 30));
        allProductLbl.setBounds(250,10,300,50);

        JTextField searchBarTf = new JTextField();
        searchBarTf.setBounds(150,70,200,20);

        JButton searchBtn = new JButton("Search");
        searchBtn.setBounds(370,71,100,20);

        JTextField searchByBarcode = new JTextField();
        searchByBarcode.setBounds(550,70,50,20);

        String[] allProductColumn = {"*","PId","Product Name","VId","Variant","Category","Price","Stock"};
        String[][] allProductData = allProductService.getAllValuesForJTable(allProductColumn.length);
        DefaultTableModel allProductDtm = new DefaultTableModel(allProductData, allProductColumn);
        JTable allProductTable = new JTable(allProductDtm);
        JScrollPane allProductSp = new JScrollPane(allProductTable);
        allProductSp.setBounds(20,100,610,300);
        allProductTable.getColumnModel().getColumn(0).setPreferredWidth(0);
        allProductTable.getColumnModel().getColumn(1).setPreferredWidth(25);
        allProductTable.getColumnModel().getColumn(3).setPreferredWidth(25);

        JButton addNewProductBtn = new JButton("Add New Product");
        addNewProductBtn.setBounds(30,420,150,30);


        allProductPnl.add(allProductLbl);
        allProductPnl.add(searchBarTf);
        allProductPnl.add(searchBtn);
        allProductPnl.add(searchByBarcode);
        allProductPnl.add(allProductSp);
        allProductPnl.add(addNewProductBtn);

        //Button Config
        searchBtn.addActionListener(e->{
            String[][] searchRecords = allProductService.getBySearch(allProductColumn.length,searchBarTf.getText());
            DefaultTableModel dtm2 = new DefaultTableModel(searchRecords,allProductColumn);
            allProductTable.setModel(dtm2);
            allProductTable.getColumnModel().getColumn(0).setPreferredWidth(0);
            allProductTable.getColumnModel().getColumn(1).setPreferredWidth(25);
            allProductTable.getColumnModel().getColumn(3).setPreferredWidth(25);
        });
        addNewProductBtn.addActionListener(e->{
            if(allProductTable.getSelectedRow()<0){
                JOptionPane.showMessageDialog(frame,"Please Select Any Product!");
            }
            String productVariantId = allProductTable.getValueAt(allProductTable.getSelectedRow(), 0).toString();
            String productId = allProductTable.getValueAt(allProductTable.getSelectedRow(), 1).toString();
            String productName = allProductTable.getValueAt(allProductTable.getSelectedRow(), 2).toString();
            String variantId = allProductTable.getValueAt(allProductTable.getSelectedRow(), 3).toString();
            String variantName = allProductTable.getValueAt(allProductTable.getSelectedRow(), 4).toString();
            String productCategory = allProductTable.getValueAt(allProductTable.getSelectedRow(), 5).toString();
            String unitPrice = allProductTable.getValueAt(allProductTable.getSelectedRow(), 6).toString();
            String maxQuantity = allProductTable.getValueAt(allProductTable.getSelectedRow(), 7).toString();

            Cart cart = new Cart(productVariantId,productId, productName, variantId, variantName, productCategory, unitPrice, maxQuantity);

            int x = cartService.checkCart(cart,true);
            if (x == -1) {
                CartRepository.addProductIntoCart(cart);
            } else if (x == -3) {
                JOptionPane.showMessageDialog(frame, "Max Quantity");
            }
            frame.dispose();
            newCartData = CartRepository.getAllCartDataForJTable(cartColumn.length);
            DefaultTableModel dtm2 = new DefaultTableModel(newCartData,cartColumn);
            cartTable.setModel(dtm2);
            updatedBillAmountLbl.setText(cartService.getTotalBill().toString());
        });

        //Barcode Work
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_TAB) {
                    barcount++;
                    if (barcount == 1) {
                        System.out.println(barcount);
                        AllProducts allProducts = allProductService.getDataByBarcode(searchByBarcode.getText());
                        if (allProducts != null) {

                            String productVariantID = allProducts.getProdVariantId();
                            String productId = allProducts.getProductId();
                            String productName = allProducts.getProdctName();
                            String variantId = allProducts.getVariantId();
                            String variantName = allProducts.getVariantName();
                            String productCategory = allProducts.getCategoryName();
                            String unitPrice = allProducts.getPrice();
                            String maxQuantity = allProducts.getQuantity();

                            Cart cart = new Cart(productVariantID,productId, productName, variantId, variantName, productCategory, unitPrice, maxQuantity);
                            int x = cartService.checkCart(cart,true);
                            if (x == -1) {
                                CartRepository.addProductIntoCart(cart);
                            } else if (x == -3) {
                                JOptionPane.showMessageDialog(frame, "Max Quantity");
                            }
                            frame.dispose();
                            newCartData = CartRepository.getAllCartDataForJTable(cartColumn.length);
                            DefaultTableModel dtm2 = new DefaultTableModel(newCartData,cartColumn);
                            cartTable.setModel(dtm2);
                            updatedBillAmountLbl.setText(cartService.getTotalBill().toString());
                        } else {
                            searchByBarcode.setText("");
                            barcount = 0;
                            searchByBarcode.requestFocus();
                        }
                    }
                }
                return false;
            }
        });


        frame.add(allProductPnl);
        frame.setSize(700,550);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
    public void completeRefund(String userId){
        JFrame frame = new JFrame("POS System");
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);

        JLabel totalBillLbl = new JLabel("Old Bill : ");
        totalBillLbl.setBounds(130,83,150,20);
        totalBillLbl.setFont(new Font("Serif", Font.PLAIN, 20));

        JLabel totalBillAmountLbl = new JLabel(oldBill.toString());
        totalBillAmountLbl.setBounds(250,80,100,30);
        totalBillAmountLbl.setFont(new Font("Serif", Font.PLAIN, 20));
        totalBillAmountLbl.setBorder(BorderFactory.createLineBorder(Color.black,2));
        totalBillAmountLbl.setBackground(Color.WHITE);
        totalBillAmountLbl.setOpaque(true);

        JLabel updatedBillLbl = new JLabel("Updated Bill : ");
        updatedBillLbl.setBounds(120,133,150,30);
        updatedBillLbl.setFont(new Font("Serif", Font.PLAIN, 20));

        updatedBillAmountLbl = new JLabel(cartService.getTotalBill().toString());
        updatedBillAmountLbl.setBounds(250,136,100,30);
        updatedBillAmountLbl.setFont(new Font("Serif", Font.PLAIN, 20));
        updatedBillAmountLbl.setBorder(BorderFactory.createLineBorder(Color.black,2));
        updatedBillAmountLbl.setBackground(Color.WHITE);
        updatedBillAmountLbl.setOpaque(true);

        JLabel differenceBillLbl = new JLabel("Difference : ");
        differenceBillLbl.setBounds(120,183,150,30);
        differenceBillLbl.setFont(new Font("Serif", Font.PLAIN, 20));

        Double dif = cartService.getTotalBill()-oldBill;
        JLabel differenceAmountLbl = new JLabel(dif.toString());
        differenceAmountLbl.setBounds(250,186,100,30);
        differenceAmountLbl.setFont(new Font("Serif", Font.PLAIN, 20));
        differenceAmountLbl.setBorder(BorderFactory.createLineBorder(Color.black,2));
        differenceAmountLbl.setBackground(Color.WHITE);
        differenceAmountLbl.setOpaque(true);

        JLabel receivedAmountLbl=new JLabel("Received amount : ");
        receivedAmountLbl.setBounds(100,250,150,25);

        JTextField receivedAmountTf = new JTextField();
        receivedAmountTf.setBounds(230,252,150,25);

        JButton completeBtn = new JButton("Complete Refund");
        completeBtn.setBounds(140,300,200,25);

        //Button Config

        completeBtn.addActionListener(e->{
            Double newBill = null;
            if(oldBill<cartService.getTotalBill()){
                if(receivedAmountTf.getText().isEmpty()){
                    JOptionPane.showMessageDialog(frame,"Please Enter received amount!!");
                    return;
                }
                if(Double.parseDouble(receivedAmountTf.getText())<dif){
                    JOptionPane.showMessageDialog(frame,"Received amount is less than the Difference");
                    return;
                }
                newBill = (Double.parseDouble(receivedAmountTf.getText())-dif);
                JOptionPane.showMessageDialog(frame,"Transaction Completed \n Returned Amount : "+newBill+" Rs");
                
            }
            else if(oldBill> cartService.getTotalBill()){
                if(!receivedAmountTf.getText().isEmpty()){
                    JOptionPane.showMessageDialog(frame,"No required to Enter received amount!!");
                    return;
                }
                newBill = (oldBill-cartService.getTotalBill());
                JOptionPane.showMessageDialog(frame,"Transaction Complete \nReturned Amount : "+newBill+" Rs");
            }
            frame.dispose();
            
            cartService.addRefundItemIntoDatabase(transactionId,userId,cartService.getTotalBill());
            CartRepository.removeAllData();
            new RefundUI(userId);
        });

        frame.add(totalBillAmountLbl);
        frame.add(totalBillLbl);
        frame.add(updatedBillLbl);
        frame.add(updatedBillAmountLbl);
        frame.add(differenceBillLbl);
        frame.add(differenceAmountLbl);
        frame.add(receivedAmountLbl);
        frame.add(receivedAmountTf);
        frame.add(completeBtn);
        frame.setSize(500,500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}
