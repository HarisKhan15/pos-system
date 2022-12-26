package domain;

public class Cart {
    private String productName;
    private String variantName;
    private String productCategory;
    private Double unitPrice;
    private Integer quantity;
    private Double amount;

    public Cart(String productName, String variantName, String productCategory, Double unitPrice, Integer quantity, Double amount) {
        this.productName = productName;
        this.variantName = variantName;
        this.productCategory = productCategory;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.amount = amount;
    }
    public Cart(String productName, String variantName, String productCategory, String unitPrice, String quantity, String amount) {
        this.productName = productName;
        this.variantName = variantName;
        this.productCategory = productCategory;
        this.unitPrice = Double.parseDouble(unitPrice);
        this.quantity = Integer.parseInt(quantity);
        this.amount = Double.parseDouble(amount);
    }

}
