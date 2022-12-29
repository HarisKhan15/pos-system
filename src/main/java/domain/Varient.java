package domain;

public class Varient {

    private  int variantID;
    private String varientName;

    public Varient(String varientName) {

        this.varientName = varientName;
    }

    public Varient(int variantID, String varientName) {
        this.variantID = variantID;
        this.varientName = varientName;
    }

    public String getVariantID() {
        return Integer.toString(variantID);
    }

    public void setVariantID(int variantID) {
        this.variantID = variantID;
    }

    public String getVarientName() {
        return varientName;
    }

    public void setVarientName(String varientName) {
        this.varientName = varientName;
    }
}
