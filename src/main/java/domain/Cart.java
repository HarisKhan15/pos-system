package domain;

import java.util.Objects;

public class Cart {
    private Integer productId;
    private String productName;
    private Integer variantId;
    private String variantName;
    private String productCategory;
    private Double unitPrice;
    private Integer quantity;
    private Double amount;

    public Cart(Integer productId, String productName, Integer variantId, String variantName, String productCategory, Double unitPrice, Integer quantity, Double amount) {
        this.productId = productId;
        this.productName = productName;
        this.variantId = variantId;
        this.variantName = variantName;
        this.productCategory = productCategory;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.amount = amount;
    }

    public Cart(String productId,String productName,String variantId, String variantName, String productCategory, String unitPrice) {
        this.productId = Integer.parseInt(productId);
        this.productName = productName;
        this.variantId = Integer.parseInt(variantId);
        this.variantName = variantName;
        this.productCategory = productCategory;
        this.unitPrice = Double.parseDouble(unitPrice);
        this.quantity = 1;
        this.amount = this.unitPrice;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getVariantId() {
        return variantId;
    }

    public void setVariantId(Integer variantId) {
        this.variantId = variantId;
    }

    public String getVariantName() {
        return variantName;
    }

    public void setVariantName(String variantName) {
        this.variantName = variantName;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        Cart c = (Cart) o;
        if(this.productId==c.getProductId()&&this.variantId==c.getVariantId()){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Cart{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", variantId=" + variantId +
                ", variantName='" + variantName + '\'' +
                ", productCategory='" + productCategory + '\'' +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                ", amount=" + amount +
                '}';
    }

    public void increaseQuantity() {
        this.quantity++;
        this.amount+=this.unitPrice;
    }
}
