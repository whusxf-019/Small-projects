public class Shop {
    private String productID;
    private String productName;
    private Integer productPrice;
    public double price;
    Integer amount;
    public String getProductID() {
        return productID;
    }
    public void setProductID(String productID) {
        this.productID = productID;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public Integer getProductPrice() {
        return productPrice;
    }
    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }
    public double price(){
        price = productPrice * amount;
        return price;
    }

}
