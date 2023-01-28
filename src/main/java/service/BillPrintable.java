package service;

import repository.CartRepository;
import repository.TransactionRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class BillPrintable extends CartService implements Printable {


    private ImageObserver rootPane;
    TransactionRepository transactionRepository=new TransactionRepository();

    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
            throws PrinterException {
        LocalDate myDateObj = LocalDate.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        String formattedDate = myDateObj.format(dateFormat);
        DateFormat datesFormat = new SimpleDateFormat("hh:mm aa");
        String dateString = datesFormat.format(new Date()).toString();

        int r = CartRepository.getAll().size();
        ImageIcon icon = new ImageIcon("src/Assets/back_8.png");
        int result = NO_SUCH_PAGE;
        if (pageIndex == 0) {

            Graphics2D g2d = (Graphics2D) graphics;
            double width = pageFormat.getImageableWidth();
            g2d.translate((int) pageFormat.getImageableX(), (int) pageFormat.getImageableY());


            //  FontMetrics metrics=g2d.getFontMetrics(new Font("Arial",Font.BOLD,7));

            try {
                int y = 20;
                int yShift = 10;
                int headerRectHeight = 15;
                // int headerRectHeighta=40;


                g2d.setFont(new Font("Monospaced", Font.PLAIN, 9));
                g2d.drawImage(icon.getImage(), 50, 20, 90, 30, rootPane);
                y += yShift + 30;
                g2d.drawString("            XYS Store        ", 12, y);
                y += yShift;
                g2d.drawString("-------------------------------------", 12, y);
                y += yShift;
                g2d.drawString("      No 00000 Address Line One ", 12, y);
                y += yShift;
                g2d.drawString("       Address Line 02 KARACHI ", 12, y);
                y += yShift;
                g2d.drawString("          www.xyzstore.com   ", 12, y);
                y += yShift;
                g2d.drawString("           +923214569874      ", 12, y);
                y += yShift;
                g2d.drawString("-------------------------------------", 12, y);
                y += headerRectHeight;
                g2d.drawString("Bill No : "+transactionRepository.getTransactionId(), 12, y);
                y += yShift;
                g2d.drawString("Cashier Name : "+transactionRepository.getCashierName(), 12, y);
                y += yShift;
                g2d.drawString("Date : "+formattedDate, 12, y);
                g2d.drawString("   Time : "+dateString, 110, y);
                y += yShift;
                g2d.drawString("-------------------------------------", 12, y);
                y += headerRectHeight;

                g2d.drawString(" Item Name                  Price   ", 10, y);
                y += yShift;
                g2d.drawString("-------------------------------------", 10, y);
                y += headerRectHeight;

                for (int s = 0; s < r; s++) {
                    g2d.drawString(" " + CartRepository.getAll().get(s).getProductName() + "     ", 10, y);
                    y += yShift;
                    g2d.drawString(" " + CartRepository.getAll().get(s).getVariantName() + "     ", 10, y);
                    y += yShift;
                    g2d.drawString("      " + CartRepository.getAll().get(s).getQuantity() + " * " + CartRepository.getAll().get(s).getUnitPrice(), 10, y);
                    g2d.drawString(String.valueOf(CartRepository.getAll().get(s).getAmount()), 160, y);
                    y += yShift;

                }

                g2d.drawString("-------------------------------------", 10, y);
                y += yShift;
                g2d.drawString(" Total amount:               " + getTotalBill().toString() + "   ", 10, y);
                y += yShift;
                g2d.drawString("-------------------------------------", 10, y);
                y += yShift;
                g2d.drawString(" Cash      :                 " + getCash().toString() + "   ", 10, y);
                y += yShift;
                g2d.drawString("-------------------------------------", 10, y);
                y += yShift;
                String balance = String.valueOf(getCash()-getTotalBill());
                g2d.drawString(" Balance   :                 " + balance + "   ", 10, y);
                y += yShift;

                g2d.drawString("*************************************", 10, y);
                y += yShift;
                g2d.drawString("       THANK YOU COME AGAIN            ", 10, y);
                y += yShift;
                g2d.drawString("*************************************", 10, y);
                y += yShift;
                g2d.drawString("       Powered by StepWay         ", 10, y);
                y += yShift;
                g2d.drawString("   CONTACT: stepway@gmail.com       ", 10, y);
                y += yShift;


            } catch (Exception e) {
                e.printStackTrace();
            }

            result = PAGE_EXISTS;
        }
        return result;
    }
}
