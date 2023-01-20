package domain;

public class AllProducts {
    private Integer prodVariantId;
    private Integer productId;



    private String prodctName;
    private Integer variantId;
    private String variantName;
    private String categoryName;
    private Double price;
    private Integer quantity;

    public AllProducts(String productId, String prodctName, String variantId, String variantName, String categoryName, String price, String quantity) {
        this.productId = Integer.parseInt(productId);
        this.prodctName = prodctName;
        this.variantId = Integer.parseInt(variantId);
        this.variantName = variantName;
        this.categoryName = categoryName;
        this.price = Double.parseDouble(price);
        this.quantity = Integer.parseInt(quantity);
    }


    public AllProducts(Integer productId, String prodctName, Integer variantId, String variantName, String categoryName, Double price, Integer quantity) {
        this.productId = productId;
        this.prodctName = prodctName;
        this.variantId = variantId;
        this.variantName = variantName;
        this.categoryName = categoryName;
        this.price = price;
        this.quantity = quantity;
    }
    public AllProducts(Integer prodVariantId,Integer productId, String prodctName, Integer variantId, String variantName, String categoryName, Double price, Integer quantity) {
        this.prodVariantId = prodVariantId;
        this.productId = productId;
        this.prodctName = prodctName;
        this.variantId = variantId;
        this.variantName = variantName;
        this.categoryName = categoryName;
        this.price = price;
        this.quantity = quantity;
    }

    public String getProductId() {
        return String.valueOf(productId);
    }
    public String getProdVariantId() {
        return String.valueOf(prodVariantId);
    }
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProdctName() {
        return prodctName;
    }

    public void setProdctName(String prodctName) {
        this.prodctName = prodctName;
    }

    public String getVariantId() {
        return String.valueOf(variantId);
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getPrice() {
        return String.valueOf(price);
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getQuantity() {
        return String.valueOf(quantity);
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
