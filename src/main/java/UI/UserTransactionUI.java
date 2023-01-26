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


public class UserTransactionUI {

    CartService cartService = new CartService();
    AllProductService allProductService = new AllProductService();

    int barcount = 0;
    public static void main(String[] args) {
        new UserTransactionUI("hasd");
    }
    public UserTransactionUI( String userId){
        //Creating Frame
        JFrame frame = new JFrame("POS System");
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);

        // Creating Search area
        JPanel searchAreaPnl = new JPanel();
        searchAreaPnl.setBounds(40,20,500,640);
        searchAreaPnl.setBackground(Color.GRAY);
        searchAreaPnl.setBorder(BorderFactory.createLineBorder(Color.black,10));
        searchAreaPnl.setLayout(null);

        JLabel logoLbl = new JLabel("POS System");
        logoLbl.setFont(new Font("Calibri", Font.PLAIN, 50));
        logoLbl.setBounds(130,20,300,50);
        logoLbl.setForeground(Color.orange);

        JTextField searchBarTf = new JTextField();
        searchBarTf.setBounds(30,100,350,20);

        JTextField searchByBarcode = new JTextField();
        searchByBarcode.setBounds(30,125,350,20);

        JButton searchBtn = new JButton("Search");
        searchBtn.setBounds(390,98,75,25);


        String[] productColumn = {"*","PId","Product Name","VId","Variant","Category","Price","Stock"};
        String[][] dataFromDatabase = allProductService.getAllValuesForJTable(productColumn.length);
        DefaultTableModel productDtm = new DefaultTableModel(dataFromDatabase,productColumn);
        JTable productTable = new JTable(productDtm);
        JScrollPane productSp = new JScrollPane(productTable);
        productSp.setBounds(22,155,455,455);
        productTable.getColumnModel().getColumn(0).setPreferredWidth(0);
        productTable.getColumnModel().getColumn(1).setPreferredWidth(25);
        productTable.getColumnModel().getColumn(3).setPreferredWidth(25);


        searchAreaPnl.add(logoLbl);
        searchAreaPnl.add(searchBtn);
        searchAreaPnl.add(searchBarTf);
        searchAreaPnl.add(searchByBarcode);
        searchAreaPnl.add(productSp);

        //Cart
        JPanel cartPnl = new JPanel();
        cartPnl.setBackground(Color.GRAY);
        cartPnl.setBounds(550,20,430,530);
        cartPnl.setBorder(BorderFactory.createLineBorder(Color.black,10));
        cartPnl.setLayout(null);

        JLabel cartLbl = new JLabel("Your Cart");
        cartLbl.setFont(new Font("Serif", Font.PLAIN, 30));
        cartLbl.setBounds(160,10,300,50);

        String[][] cartData = CartRepository.getAllCartDataForJTable(6);
        String[] cartColumn = {"Product Name","Product Variant","Product Category","Unit Price","Quantity","Price"};
        DefaultTableModel cartDtm = new DefaultTableModel(cartData, cartColumn);
        JTable cartTable = new JTable(cartDtm);
        JScrollPane cartSp = new JScrollPane(cartTable);
        cartSp.setBounds(18,70,395,380);

        JLabel totalBillLbl = new JLabel("Total Bill : ");
        totalBillLbl.setBounds(30,473,150,20);
        totalBillLbl.setFont(new Font("Serif", Font.PLAIN, 20));

        JLabel amountLbl = new JLabel(cartService.getTotalBill().toString());
        amountLbl.setBounds(130,470,100,30);
        amountLbl.setFont(new Font("Serif", Font.PLAIN, 20));
        amountLbl.setBorder(BorderFactory.createLineBorder(Color.black,2));
        amountLbl.setBackground(Color.WHITE);
        amountLbl.setOpaque(true);

        JButton addProductBtn = new JButton("Add");
        addProductBtn.setBounds(250,470,70,30);

        JButton deleteProductBtn = new JButton("Delete");
        deleteProductBtn.setBounds(340,470,70,30);


        cartPnl.add(cartLbl);
        cartPnl.add(cartSp);
        cartPnl.add(totalBillLbl);
        cartPnl.add(amountLbl);
        cartPnl.add(addProductBtn);
        cartPnl.add(deleteProductBtn);

        //Bill
        JPanel billPnl = new JPanel();
        billPnl.setBackground(Color.GRAY);
        billPnl.setBounds(550,560,430,100);
        billPnl.setBorder(BorderFactory.createLineBorder(Color.black,10));
        billPnl.setLayout(new FlowLayout(FlowLayout.CENTER,25,10));

        JButton completeTransactionBtn = new JButton("Complete Transaction");
        JButton cancelTransactionBtn = new JButton("Cancel Transaction");
        JButton logOutBtn = new JButton("LogOut");
        JButton refundBtn = new JButton("Refund");


        billPnl.add(completeTransactionBtn);
        billPnl.add(cancelTransactionBtn);
        billPnl.add(logOutBtn);
        billPnl.add(refundBtn);

        //Button config
        refundBtn.addActionListener(e->{
            CartRepository.clearList();
            frame.dispose();
            new RefundUI(userId);
        });
        searchBtn.addActionListener(e->{
            String[][] searchRecords = allProductService.getBySearch(productColumn.length,searchBarTf.getText());
            DefaultTableModel dtm2 = new DefaultTableModel(searchRecords,productColumn);
            productTable.setModel(dtm2);
            productTable.getColumnModel().getColumn(0).setPreferredWidth(0);
            productTable.getColumnModel().getColumn(1).setPreferredWidth(25);
            productTable.getColumnModel().getColumn(3).setPreferredWidth(25);
        });

        completeTransactionBtn.addActionListener(e->{
            if(CartRepository.getAll().isEmpty()){
                JOptionPane.showMessageDialog(frame,"Cart is Empty !!");
                return;
            }
            frame.dispose();
            new CompleteTransactionUI(Double.parseDouble(amountLbl.getText()),userId);
        });
        cancelTransactionBtn.addActionListener(e->{
            CartRepository.removeAllData();
            frame.dispose();
            new UserTransactionUI(userId);
        });
        logOutBtn.addActionListener(e->{
            CartRepository.removeAllData();
            frame.dispose();
            new LoginUI();
        });


        //BarCode Code
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {


                if (e.getKeyCode() == KeyEvent.VK_TAB) {
                    barcount++;
                    if (barcount == 1) {
                        System.out.println(barcount);
                        AllProducts allProducts = allProductService.getDataByBarcode(searchByBarcode.getText());
                        if (allProducts != null) {


                            String productId = allProducts.getProductId();
                            String productName = allProducts.getProdctName();
                            String variantId = allProducts.getVariantId();
                            String variantName = allProducts.getVariantName();
                            String productCategory = allProducts.getCategoryName();
                            String unitPrice = allProducts.getPrice();
                            String maxQuantity = allProducts.getQuantity();

                            Cart cart = new Cart(productId, productName, variantId, variantName, productCategory, unitPrice, maxQuantity);
                            int x = cartService.checkCart(cart, true);
                            if (x == -1) {
                                CartRepository.addProductIntoCart(cart);
                                cartDtm.addRow(cartService.lastValue());
                            } else if (x == -3) {
                                JOptionPane.showMessageDialog(frame, "Max Quantity");
                            } else {
                                cartDtm.setValueAt(cartService.getUpdatedQuantity(x), x, 4);
                                cartDtm.setValueAt(cartService.getUpdatedAmount(x), x, 5);
                            }
                            amountLbl.setText(cartService.getTotalBill().toString());
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

        searchByBarcode.addActionListener(e->{
            AllProducts allProducts = allProductService.getDataByBarcode(searchByBarcode.getText());

            String productId = allProducts.getProductId();
            String productName = allProducts.getProdctName();
            String variantId = allProducts.getVariantId();
            String variantName = allProducts.getVariantName();
            String productCategory = allProducts.getCategoryName();
            String unitPrice = allProducts.getPrice();
            String maxQuantity = allProducts.getQuantity();

            Cart cart = new Cart(productId, productName, variantId, variantName, productCategory, unitPrice, maxQuantity);
            int x = cartService.checkCart(cart,true);
            if (x == -1) {
                CartRepository.addProductIntoCart(cart);
                cartDtm.addRow(cartService.lastValue());
            } else if (x == -3) {
                JOptionPane.showMessageDialog(frame, "Max Quantity");
            } else {
                cartDtm.setValueAt(cartService.getUpdatedQuantity(x), x, 4);
                cartDtm.setValueAt(cartService.getUpdatedAmount(x), x, 5);
            }
            amountLbl.setText(cartService.getTotalBill().toString());
        });

        addProductBtn.addActionListener(e-> {

            String productId = null;
            String productName = null;
            String variantId = null;
            String variantName = null;
            String productCategory = null;
            String unitPrice = null;
            String maxQuantity = null;
            if (productTable.getSelectedRow() > -1) {
                productId = productTable.getValueAt(productTable.getSelectedRow(), 1).toString();
                productName = productTable.getValueAt(productTable.getSelectedRow(), 2).toString();
                variantId = productTable.getValueAt(productTable.getSelectedRow(), 3).toString();
                variantName = productTable.getValueAt(productTable.getSelectedRow(), 4).toString();
                productCategory = productTable.getValueAt(productTable.getSelectedRow(), 5).toString();
                unitPrice = productTable.getValueAt(productTable.getSelectedRow(), 6).toString();
                maxQuantity = productTable.getValueAt(productTable.getSelectedRow(), 7).toString();
            } else if (cartTable.getSelectedRow() > -1) {
                Cart cart = CartRepository.getAll().get(cartTable.getSelectedRow());
                productId = cart.getProductId().toString();
                productName = cart.getProductName();
                variantId = cart.getVariantId().toString();
                variantName = cart.getVariantName();
                productCategory = cart.getProductCategory();
                unitPrice = cart.getUnitPrice().toString();
                maxQuantity = cart.getMaxQuantity().toString();

            }else{
                JOptionPane.showMessageDialog(frame,"Please select any product!!");
                return;
            }
            Cart cart = new Cart(productId, productName, variantId, variantName, productCategory, unitPrice, maxQuantity);
            int x = cartService.checkCart(cart,true);
            if (x == -1) {
                CartRepository.addProductIntoCart(cart);
                cartDtm.addRow(cartService.lastValue());
            } else if (x == -3) {
                JOptionPane.showMessageDialog(frame, "Max Quantity");
            } else {
                cartDtm.setValueAt(cartService.getUpdatedQuantity(x), x, 4);
                cartDtm.setValueAt(cartService.getUpdatedAmount(x), x, 5);
            }
            amountLbl.setText(cartService.getTotalBill().toString());
            String[][] searchRecords = allProductService.getBySearch(productColumn.length,searchBarTf.getText());
            DefaultTableModel dtm2 = new DefaultTableModel(searchRecords,productColumn);
            productTable.setModel(dtm2);
            productTable.getColumnModel().getColumn(0).setPreferredWidth(0);
            productTable.getColumnModel().getColumn(1).setPreferredWidth(25);
            productTable.getColumnModel().getColumn(3).setPreferredWidth(25);

        });
        deleteProductBtn.addActionListener(e->{
            if(cartTable.getSelectedRow()<0){
                return;
            }
            Cart cart = CartRepository.getByIndex(cartTable.getSelectedRow());

            int x = cartService.checkCart(cart,false);
            if (x == -2) {
                CartRepository.removeData(cartTable.getSelectedRow());
                cartDtm.removeRow(cartTable.getSelectedRow());
            } else {
                cartDtm.setValueAt(cartService.getUpdatedQuantity(cartTable.getSelectedRow()), cartTable.getSelectedRow(), 4);
                cartDtm.setValueAt(cartService.getUpdatedAmount(cartTable.getSelectedRow()), cartTable.getSelectedRow(), 5);
            }
            amountLbl.setText(cartService.getTotalBill().toString());
        });
        frame.add(cartPnl);
        frame.add(searchAreaPnl);
        frame.add(billPnl);

        frame.setSize(1035,730);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
