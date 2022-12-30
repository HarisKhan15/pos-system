package domain;

public class ViewTransaction {
    private String productName;
    private String variantName;
    private String categoryName;
    private Integer productQuantity;
    private Double amount;

    public ViewTransaction(String productName, String variantName, String categoryName, Integer productQuantity, Double amount) {
        this.productName = productName;
        this.variantName = variantName;
        this.categoryName = categoryName;
        this.productQuantity = productQuantity;
        this.amount = amount;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getVariantName() {
        return variantName;
    }

    public void setVariantName(String variantName) {
        this.variantName = variantName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
