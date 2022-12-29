package domain;

public class Category {

    private Integer categoryID;
    private  String categoryName;

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryID() {
        return Integer.toString(categoryID);
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category(Integer categoryID, String categoryName) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
    }
}
