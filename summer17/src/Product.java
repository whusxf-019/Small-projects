//•	产品库存管理——创建一个管理产品库存的应用。建立一个产品类，包含价格、id、库存数量。然后建立一个库存类，记录各种产品并能计算库存的总价值。
public class Product {
    private String productID;
    private String productName;
    private Integer productPrice;
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
    public static void main(String s[]){
        Product p1 = new Product();
        p1.amount = 111;
        p1.setProductID("1001");
        p1.setProductName("name1");
        p1.setProductPrice(100);

        Product p2 = new Product();
        p2.amount = 222;
        p2.setProductID("1002");
        p2.setProductName("name2");
        p2.setProductPrice(200);

        Shop p01 = new Shop();
        p01.amount = 111;
        p01.setProductID("1001");
        p01.setProductName("name1");
        p01.setProductPrice(100);

        Shop p02 = new Shop();
        p02.amount = 222;
        p02.setProductID("1002");
        p02.setProductName("name2");
        p02.setProductPrice(200);

        double price = p01.price()+p02.price();

        System.out.println("p1.ID = "+p1.getProductID()+" ' p1.name = "+p1.getProductName()+" , p1.price = "+p1.getProductPrice());
        System.out.println("p2.ID = "+p2.getProductID()+" ' p1.name = "+p2.getProductName()+" , p1.price = "+p2.getProductPrice());
        System.out.println("all price = "+ price);
    }
}

